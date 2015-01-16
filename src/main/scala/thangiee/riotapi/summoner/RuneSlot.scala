package thangiee.riotapi.summoner

import play.api.libs.json._

case class RuneSlot(runeId: Int = 0, runeSlotId: Int = 0)

object RuneSlot {
  implicit val runeSlotFmt = Json.reads[RuneSlot]
}
