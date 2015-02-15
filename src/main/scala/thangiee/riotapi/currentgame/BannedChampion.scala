package thangiee.riotapi.currentgame

import play.api.libs.json._

case class BannedChampion(championId: Long, pickTurn: Int, teamId: Long)

object BannedChampion {
  implicit val bannedChampionFmt = Json.reads[BannedChampion]
}
