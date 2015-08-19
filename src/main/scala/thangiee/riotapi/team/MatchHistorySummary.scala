package thangiee.riotapi.team

case class MatchHistorySummary(
  assists: Int = 0,
  date: Long = 0,
  deaths: Int = 0,
  gameId: Long = 0,
  gameMode: String = "",
  invalid: Boolean = true,
  kills: Int = 0,
  mapId: Int = 0,
  opposingTeamKills: Int = 0,
  opposingTeamName: String = "",
  win: Boolean = false
  )
