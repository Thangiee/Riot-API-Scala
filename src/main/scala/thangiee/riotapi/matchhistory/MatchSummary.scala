package thangiee.riotapi.matchhistory

case class MatchSummary(
  mapId: Int = 0,
  matchCreation: Long = 0,
  matchDuration: Long = 0,
  matchId: Long = 0,
  matchMode: String = "",
  matchType: String = "",
  matchVersion: String = "",
  participantIdentities: List[ParticipantIdentity] = Nil,
  participants: List[Participant] = Nil,
  platformId: String = "",
  queueType: String = "",
  region: String = "",
  season: String = ""
  )

