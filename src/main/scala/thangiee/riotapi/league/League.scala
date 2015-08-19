package thangiee.riotapi.league

case class League(
  entries: List[LeagueEntry],
  name: String,
  participantId: String,
  queue: String,
  tier: String
  )
