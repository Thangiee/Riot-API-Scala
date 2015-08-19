package thangiee.riotapi.matchlist

case class MatchReference(
  champion: Long = 0,
  lane: String = "",
  matchId: Long = 0,
  platformId: String = "",
  queue: String = "",
  role: String = "",
  season: String = "",
  timestamp: Long = 0
)

