package thangiee.riotapi

import java.net.SocketTimeoutException

import com.typesafe.scalalogging.LazyLogging
import org.scalactic.{Bad, Good, Or}

import scala.concurrent.duration.Duration
import scala.util.{Failure, Success, Try}
import scalacache.guava.GuavaCache
import scalaj.http.Http

trait ApiCaller {
  def call(url: String, ttl: Duration): JsonString Or RiotError
}

object ApiCaller {

  implicit object DefaultApiCaller extends ApiCaller with LazyLogging {
    import scalacache._
    implicit val scalaCache = ScalaCache(GuavaCache())

    def call(url: String, ttl: Duration): JsonString Or RiotError = {
      val key = url.takeWhile(_ != '=') // strip the api key from the url and resulting url as our cache key

      getSync[String](key) match { // blocking
        case Some(cacheHit) => Good(cacheHit)
        case None => // cache missed
          Try(Http(url).asString) match { // use the url to the restful api
            case Success(response) =>
              logger.debug(s"API call response code: ${response.code} ($url)")
              response.code match {
                case 200 =>
                  put(key)(response.body, Some(ttl)) // cache the response's content
                  Good(response.body)                // and return it
                case 400 => Bad(BadRequest)
                case 401 => Bad(Unauthorized)
                case 404 => Bad(DataNotFound)
                case 422 => Bad(DataNotFound)
                case 429 => Bad(RateLimit)
                case 500 => Bad(ServerError)
                case 503 => Bad(ServiceUnavailable)
              }
            case Failure(e: SocketTimeoutException) => Bad(TimeOut)
            case Failure(e: Throwable) => e.printStackTrace(); Bad(ServiceUnavailable)
          }
      }
    }
  }

}
