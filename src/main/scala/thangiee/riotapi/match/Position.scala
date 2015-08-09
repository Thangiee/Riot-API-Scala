package thangiee.riotapi.`match`

import play.api.libs.json.Json

case class Position(x: Int = 0, y: Int = 0)

object Position {
  implicit val positionFmt = Json.reads[Position]
}