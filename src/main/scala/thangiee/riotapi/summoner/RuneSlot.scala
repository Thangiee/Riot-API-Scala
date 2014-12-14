package thangiee.riotapi.summoner

import play.api.libs.json._

case class RuneSlot(runeId: Int, runeSlotId: Int)

object RuneSlot {
  implicit val runeSlotFmt = Json.format[RuneSlot]
}
