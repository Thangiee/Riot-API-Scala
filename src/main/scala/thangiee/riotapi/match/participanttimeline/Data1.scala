package thangiee.riotapi.`match`.participanttimeline

import play.api.libs.json.Json
import thangiee.riotapi.matchhistory.ParticipantTimelineData

case class Data1(
  ancientGolemAssistsPerMinCounts: ParticipantTimelineData = ParticipantTimelineData(),
  ancientGolemKillsPerMinCounts: ParticipantTimelineData = ParticipantTimelineData(),
  assistedLaneDeathsPerMinDeltas: ParticipantTimelineData = ParticipantTimelineData(),
  assistedLaneKillsPerMinDeltas: ParticipantTimelineData = ParticipantTimelineData(),
  baronAssistsPerMinCounts: ParticipantTimelineData = ParticipantTimelineData(),
  baronKillsPerMinCounts: ParticipantTimelineData = ParticipantTimelineData(),
  creepsPerMinDeltas: ParticipantTimelineData = ParticipantTimelineData(),
  csDiffPerMinDeltas: ParticipantTimelineData = ParticipantTimelineData(),
  damageTakenDiffPerMinDeltas: ParticipantTimelineData = ParticipantTimelineData(),
  damageTakenPerMinDeltas: ParticipantTimelineData = ParticipantTimelineData(),
  dragonAssistsPerMinCounts: ParticipantTimelineData = ParticipantTimelineData(),
  dragonKillsPerMinCounts: ParticipantTimelineData = ParticipantTimelineData(),
  elderLizardAssistsPerMinCounts: ParticipantTimelineData = ParticipantTimelineData(),
  elderLizardKillsPerMinCounts: ParticipantTimelineData = ParticipantTimelineData(),
  goldPerMinDeltas: ParticipantTimelineData = ParticipantTimelineData(),
  inhibitorAssistsPerMinCounts: ParticipantTimelineData = ParticipantTimelineData(),
  inhibitorKillsPerMinCounts: ParticipantTimelineData = ParticipantTimelineData(),
  lane: String = "",
  role: String = "",
  towerAssistsPerMinCounts: ParticipantTimelineData = ParticipantTimelineData(),
  towerKillsPerMinCounts: ParticipantTimelineData = ParticipantTimelineData(),
  towerKillsPerMinDeltas: ParticipantTimelineData = ParticipantTimelineData()
  )

object Data1 {
  implicit val data1Fmt = Json.reads[Data1]
}
