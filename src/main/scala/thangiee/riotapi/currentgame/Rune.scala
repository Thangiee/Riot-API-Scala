package thangiee.riotapi.currentgame

import play.api.libs.json._

case class Rune(count: Int, runeId: Long)

object Rune {
  implicit val runeFmt = Json.reads[Rune]
}
