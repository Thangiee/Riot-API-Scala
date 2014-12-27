package thangiee.riotapi.league

import play.api.libs.json._

case class LeagueEntry
(division: String,
 isFreshBlood: Boolean,
 isHotStreak: Boolean,
 isInactive: Boolean,
 isVeteran: Boolean,
 leaguePoints: Int,
 miniSeries: Option[MiniSeries],
 playerOrTeamId: String,
 playerOrTeamName: String,
 wins: Int
  )

object LeagueEntry {
  implicit val leagueEntryFmt = Json.reads[LeagueEntry]
}
