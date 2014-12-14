package thangiee.riotapi.summoner

import play.api.libs.json._

case class Mastery(id: Int, rank: Int)

object Mastery {
  implicit val masteryFmt = Json.format[Mastery]
}
