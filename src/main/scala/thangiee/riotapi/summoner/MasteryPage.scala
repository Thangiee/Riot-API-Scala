package thangiee.riotapi.summoner

import play.api.libs.json._

case class MasteryPage(current: Boolean = false, id: Long = 0, masteries: List[Mastery] = Nil, name: String = "")

object MasteryPage {
  implicit val masteryPageFmt = Json.reads[MasteryPage]
}
