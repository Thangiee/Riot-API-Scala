package thangiee.riotapi.`match`

import play.api.libs.json.Json

case class Mastery(masteryId: Long = 0, rank: Long = 0)

object Mastery {
  implicit val masteryFmt = Json.reads[Mastery]
}
