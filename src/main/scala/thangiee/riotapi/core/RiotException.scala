package thangiee.riotapi.core

case class RiotException(msg: String, errType: RiotException.Value) extends Exception(msg)

object RiotException extends Enumeration {
  type RiotException = Value
  val BadRequest         = Value(400)
  val Unauthorized       = Value(401)
  val DataNotFound       = Value(404)
  val RateLimit          = Value(429)
  val ServerError        = Value(500)
  val ServiceUnavailable = Value(503)
}
