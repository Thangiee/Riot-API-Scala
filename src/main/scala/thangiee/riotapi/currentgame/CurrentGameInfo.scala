package thangiee.riotapi.currentgame

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
