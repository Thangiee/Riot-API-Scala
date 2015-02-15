package thangiee.riotapi.currentgame

import play.api.libs.json._

case class Mastery(masteryId: Long, rank: Int)

object Mastery {
  implicit val masteryFmt = Json.reads[Mastery]
}
