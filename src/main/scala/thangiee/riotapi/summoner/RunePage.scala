package thangiee.riotapi.summoner

import play.api.libs.json._

case class RunePage(current: Boolean, id: Long, name: String, slots: Set[RuneSlot])

object RunePage {
  implicit val runePageFmt = Json.reads[RunePage]
}
