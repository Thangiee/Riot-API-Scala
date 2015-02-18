package thangiee.riotapi.core

import thangiee.riotapi.core.RiotException._

import scala.util.{Failure, Success, Try}
import scalaj.http.Http

class SimpleApiCaller extends ApiCaller {

  override def call(url: String)(implicit apiKey: ApiKey): Try[String] = {
    Try(Http(url + apiKey.key).asString) match {
      case Success(response) =>
        response.code match {
          case 200 => Success(response.body)
          case 400 => Failure(RiotException("Bad Request", BadRequest))
          case 401 => Failure(RiotException("Invalid API key", Unauthorized))
          case 404 => Failure(RiotException("Requested data can not be found", DataNotFound))
          case 429 => Failure(RiotException("API key hit limit rate", RateLimit))
          case 500 => Failure(RiotException("Internal server error", ServerError))
          case 503 => Failure(RiotException("Service unavailable", ServiceUnavailable))
        }

      case Failure(e) => Failure(RiotException("Service unavailable", ServiceUnavailable))
    }
  }
}
