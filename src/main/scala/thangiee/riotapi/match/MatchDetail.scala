package thangiee.riotapi.`match`

import play.api.libs.json.Json

case class MatchDetail(
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
  season: String = "",
  teams: List[Team] = Nil,
  timeline: Timeline = Timeline()
  )

object MatchDetail {
  implicit val matchDetailFmt = Json.reads[MatchDetail]
}
