package thangiee.riotapi

import java.net.{UnknownHostException, SocketTimeoutException}

import com.typesafe.scalalogging.LazyLogging
import org.scalactic.{Bad, Good, Or}

import scala.concurrent.duration.Duration
import scala.util.{Failure, Success, Try}
import scalacache.guava.GuavaCache
import scalaj.http.Http

trait ApiCaller {

  /** Fetch JSON string from the Riot RESTful API
   *
   * @param url the URL to a specific API. This url also includes the default api key set by [[RiotApi.key]].
   * @param ttl (time to live) caching duration
   * @return content of the API response as a JSON string
   */
  def call(url: String, ttl: Duration): JsonString Or RiotError
}

object ApiCaller {

  /** Default ApiCaller implementation with caching support */
  implicit object DefaultApiCaller extends ApiCaller with LazyLogging {
    import scalacache._
    implicit val scalaCache = ScalaCache(GuavaCache())

    def call(url: String, ttl: Duration): JsonString Or RiotError = {
      val (cacheKey, apiKey) = url.splitAt(url.lastIndexOf('=') + 1) // strip the api key from the url and resulting url as our cache key

      getSync[String](cacheKey) match { // blocking
        case Some(cacheHit) => Good(cacheHit)
        case None => // cache missed
          Try(Http(url).asString) match { // do the api call using the url
            case Success(response) =>
              logger.debug(s"API call response code: ${response.code} ($url)")
              response.code match {
                case 200 =>
                  put(cacheKey)(response.body, Some(ttl)) // cache the response's content
                  Good(response.body)                // and return it
                case 400 => Bad(BadRequest(url))
                case 401 => Bad(Unauthorized(apiKey))
                case 404 => Bad(DataNotFound)
                case 422 => Bad(DataNotFound)
                case 429 => Bad(RateLimit(apiKey))
                case 500 => Bad(ServerError)
                case 503 => Bad(ServiceUnavailable)
              }
            case Failure(e: SocketTimeoutException) => Bad(TimeOut)
            case Failure(e: UnknownHostException)   => Bad(BadRequest(url))
            case Failure(e: Throwable) => e.printStackTrace(); Bad(ServiceUnavailable)
          }
      }
    }
  }

}
