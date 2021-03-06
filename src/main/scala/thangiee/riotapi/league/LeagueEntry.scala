package thangiee.riotapi.league

case class LeagueEntry(
  division: String = "",
  isFreshBlood: Boolean = false,
  isHotStreak: Boolean = false,
  isInactive: Boolean = false,
  isVeteran: Boolean = false,
  leaguePoints: Int = 0,
  losses: Int = 0,
  miniSeries: Option[MiniSeries] = None,
  playerOrTeamId: String = "",
  playerOrTeamName: String = "",
  wins: Int = 0
  )
