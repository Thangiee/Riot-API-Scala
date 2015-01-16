package thangiee.riotapi.summoner

import play.api.libs.json._

case class RunePage(current: Boolean = false, id: Long = 0, name: String = "", slots: Set[RuneSlot] = Set())

object RunePage {
  implicit val runePageFmt = Json.reads[RunePage]
}
