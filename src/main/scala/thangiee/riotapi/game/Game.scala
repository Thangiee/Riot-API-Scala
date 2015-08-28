package thangiee.riotapi.game

case class Game(
  championId: Int = 0,
  createDate: Long = 0,
  fellowPlayers: List[Player] = Nil,
  gameId: Long = 0,
  gameMode: String = "",
  gameType: String = "",
  invalid: Boolean = true,
  ipEarned: Int = 0,
  level: Int = 0,
  mapId: Int = 0,
  spell1: Int = 0,
  spell2: Int = 0,
  stats: RawStats = RawStats(),
  subType: String = "",
  teamId: Int = 0
  )
