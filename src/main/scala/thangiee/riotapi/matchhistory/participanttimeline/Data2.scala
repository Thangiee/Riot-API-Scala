package thangiee.riotapi.matchhistory.participanttimeline

import play.api.libs.json.Json
import thangiee.riotapi.matchhistory.ParticipantTimelineData

case class Data2(
  vilemawAssistsPerMinCounts: ParticipantTimelineData = ParticipantTimelineData(),
  vilemawKillsPerMinCounts: ParticipantTimelineData = ParticipantTimelineData(),
  wardsPerMinDeltas: ParticipantTimelineData = ParticipantTimelineData(),
  xpDiffPerMinDeltas: ParticipantTimelineData = ParticipantTimelineData(),
  xpPerMinDeltas: ParticipantTimelineData = ParticipantTimelineData()
  )

object Data2 {
  implicit val data2Fmt = Json.reads[Data2]
}

