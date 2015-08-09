package thangiee.riotapi.currentgame

import play.api.libs.json._

case class Mastery(masteryId: Long = 0, rank: Int = 0)

object Mastery {
  implicit val masteryFmt = Json.reads[Mastery]
}
