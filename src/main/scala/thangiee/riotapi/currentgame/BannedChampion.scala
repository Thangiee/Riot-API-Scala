package thangiee.riotapi.currentgame

import play.api.libs.json._

case class BannedChampion(championId: Long = 0, pickTurn: Int = 0, teamId: Long = 0)

object BannedChampion {
  implicit val bannedChampionFmt = Json.reads[BannedChampion]
}
