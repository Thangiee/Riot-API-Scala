package thangiee.riotapi.currentgame

import play.api.libs.json._

case class CurrentGameInfo(
  bannedChampions: List[BannedChampion],
  gameId: Long,
  gameLength: Long,
  gameMode: String,
  gameQueueConfigId: Long,
  gameStartTime: Long,
  gameType: String,
  mapId: Long,
  observers: Option[Observer],
  participants: List[Participant],
  platformId: String
  )

object CurrentGameInfo {
  implicit val currentGameInfoFmt = Json.reads[CurrentGameInfo]
}
