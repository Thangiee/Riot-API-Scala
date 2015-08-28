package thangiee.riotapi.matches

case class MatchDetail(
  mapId: Int,
  matchCreation: Long,
  matchDuration: Long,
  matchId: Long,
  matchMode: String,
  matchType: String,
  matchVersion: String,
  participantIdentities: List[ParticipantIdentity],
  participants: List[Participant],
  platformId: String,
  queueType: String,
  region: String,
  season: String,
  teams: List[Team],
  timeline: Timeline
  )
