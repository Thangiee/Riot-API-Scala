package thangiee.riotapi

import java.net.SocketTimeoutException

import org.scalactic.{Or, Good, Bad}

import scala.util.{Failure, Success, Try}
import scalaj.http.Http

trait SimpleApiCaller extends ApiCaller {
  def call(url: String): JsonString Or RiotError = {
    Try(Http(url).asString) match {
      case Success(response) =>
        response.code match {
          case 200 => Good(response.body)
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

object SimpleApiCaller extends SimpleApiCaller
