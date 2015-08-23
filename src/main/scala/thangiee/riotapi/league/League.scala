package thangiee.riotapi.league

case class League(
  entries: List[LeagueEntry],
  name: String,
  participantId: Option[String],
  queue: String,
  tier: String
  )
