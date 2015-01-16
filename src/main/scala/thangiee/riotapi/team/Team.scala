package thangiee.riotapi.team

import play.api.libs.json._

case class Team
(createDate: Long = 0,
 fullId: String = "",
 lastGameDate: Long = 0,
 lastJoinDate: Long = 0,
 lastJoinedRankedTeamQueueDate: Long = 0,
 matchHistory: List[MatchHistorySummary] = Nil,
 modifyDate: Long = 0,
 name: String = "",
 roster: Option[Roster] = None,
 secondLastJoinDate: Long = 0,
 status: String = "",
 tag: String = "",
 teamStatDetails: List[TeamStatDetail] = Nil,
 thirdLastJoinDate: Long = 0)

object Team {
  implicit val teamFmt = Json.reads[Team]
}
