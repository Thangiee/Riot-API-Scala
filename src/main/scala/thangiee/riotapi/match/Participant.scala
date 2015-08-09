package thangiee.riotapi.`match`

import play.api.libs.json.Json
import thangiee.riotapi.matchhistory.participantstats.ParticipantStats
import thangiee.riotapi.matchhistory.participanttimeline.ParticipantTimeline

case class Participant(
  championId: Int = 0,
  highestAchievedSeasonTier: String = "",
  masteries: List[Mastery] = Nil,
  participantId: Int = 0,
  runes: List[Rune] = Nil,
  spell1Id: Int = 0,
  spell2Id: Int = 0,
  stats: ParticipantStats = ParticipantStats(),
  teamId: Int = 0,
  timeline: ParticipantTimeline = ParticipantTimeline()
  )

object Participant {
  implicit val participantFmt = Json.reads[Participant]
}