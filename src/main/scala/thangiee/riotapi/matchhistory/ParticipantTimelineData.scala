package thangiee.riotapi.matchhistory

import play.api.libs.json.Json

case class ParticipantTimelineData(
  tenToTwenty: Double = 0,
  thirtyToEnd: Double = 0,
  twentyToThirty: Double = 0,
  zeroToTen: Double = 0
  )

object ParticipantTimelineData {
  implicit val participantTimelineDataFmt = Json.reads[ParticipantTimelineData]
}
