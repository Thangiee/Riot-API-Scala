package thangiee.riotapi.matchhistory

import play.api.libs.json.Json

case class Rune(rank: Long = 0, runeId: Long = 0)

object Rune {
  implicit val runeFmt = Json.reads[Rune]
}