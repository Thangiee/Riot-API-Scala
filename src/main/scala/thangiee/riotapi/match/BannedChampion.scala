package thangiee.riotapi.`match`

import play.api.libs.json.Json

case class BannedChampion(championId: Int = 0, pickTurn: Int = 0)

object BannedChampion {
  implicit val bannedChampionFmt = Json.reads[BannedChampion]
}
