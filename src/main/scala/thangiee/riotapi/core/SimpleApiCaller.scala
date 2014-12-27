package thangiee.riotapi.core

import thangiee.riotapi.core.RiotException._

import scala.util.{Failure, Success, Try}
import scalaj.http.Http

class SimpleApiCaller extends ApiCaller {

  override def call(url: String)(implicit apiKey: ApiKey): Either[RiotException, String] = {
    Try(Http(url + apiKey.key).asString) match {
      case Success(response) =>
        response.code match {
          case 200 => Right(response.body)
          case 400 => Left(RiotException("Bad Request", BadRequest))
          case 401 => Left(RiotException("Invalid API key", Unauthorized))
          case 404 => Left(RiotException("Requested data can not be found", DataNotFound))
          case 429 => Left(RiotException("API key hit limit rate", RateLimit))
          case 500 => Left(RiotException("Internal server error", ServerError))
          case 503 => Left(RiotException("Service unavailable", ServiceUnavailable))
        }

      case Failure(e) => Left(RiotException("Service unavailable", ServiceUnavailable))
    }
  }
}
