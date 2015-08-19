package thangiee.riotapi.team

case class Team(
  createDate: Long,
  fullId: String,
  lastGameDate: Long,
  lastJoinDate: Long,
  lastJoinedRankedTeamQueueDate: Long,
  matchHistory: List[MatchHistorySummary],
  modifyDate: Long,
  name: String,
  roster: Roster,
  secondLastJoinDate: Long,
  status: String,
  tag: String,
  teamStatDetails: List[TeamStatDetail],
  thirdLastJoinDate: Long
  )
