package thangiee.riotapi.league

import play.api.libs.json._

case class LeagueEntry
(division: String = "",
 isFreshBlood: Boolean = false,
 isHotStreak: Boolean = false,
 isInactive: Boolean = false,
 isVeteran: Boolean = false,
 leaguePoints: Int = 0,
 miniSeries: Option[MiniSeries] = None,
 playerOrTeamId: String = "",
 playerOrTeamName: String = "",
 wins: Int = 0
  )

object LeagueEntry {
  implicit val leagueEntryFmt = Json.reads[LeagueEntry]
}
