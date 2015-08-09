package thangiee.riotapi.currentgame

import play.api.libs.json._

case class CurrentGameInfo(
  bannedChampions: List[BannedChampion] = Nil,
  gameId: Long = 0,
  gameLength: Long = 0,
  gameMode: String = "",
  gameQueueConfigId: Long = 0,
  gameStartTime: Long = 0,
  gameType: String = "",
  mapId: Long = 0,
  observers: Option[Observer] = None,
  participants: List[Participant] = Nil,
  platformId: String = ""
  )

object CurrentGameInfo {
  implicit val currentGameInfoFmt = Json.reads[CurrentGameInfo]
}
