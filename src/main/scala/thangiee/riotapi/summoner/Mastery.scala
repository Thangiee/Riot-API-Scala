package thangiee.riotapi.summoner

import play.api.libs.json._

case class Mastery(id: Int = 0, rank: Int = 0)

object Mastery {
  implicit val masteryFmt = Json.reads[Mastery]
}
