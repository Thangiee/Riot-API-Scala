package thangiee.riotapi.summoner

import play.api.libs.json._

case class MasteryPage(current: Boolean, id: Long, masteries: List[Mastery], name: String)

object MasteryPage {
  implicit val masteryPageFmt = Json.reads[MasteryPage]
}
