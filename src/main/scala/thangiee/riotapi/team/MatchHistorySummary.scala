package thangiee.riotapi.team

import play.api.libs.json._

case class MatchHistorySummary
(assists: Int,
 date: Long,
 deaths: Int,
 gameId: Long,
 gameMode: String,
 invalid: Boolean,
 kills: Int,
 mapId: Int,
 opposingTeamKills: Int,
 opposingTeamName: String,
 win: Boolean)

object MatchHistorySummary {
  implicit val matchHistorySummaryFmt = Json.reads[MatchHistorySummary]
}
