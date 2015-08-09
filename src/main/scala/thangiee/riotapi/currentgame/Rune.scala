package thangiee.riotapi.currentgame

import play.api.libs.json._

case class Rune(count: Int = 0, runeId: Long = 0)

object Rune {
  implicit val runeFmt = Json.reads[Rune]
}
