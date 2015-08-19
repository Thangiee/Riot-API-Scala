package thangiee.riotapi.matchlist

import play.api.libs.json.Json

case class MatchList(
  endIndex: Int,
  matches: List[MatchReference],
  startIndex: Int,
  totalGames: Int
  )

object MatchList {
  implicit val matchListFmt = Json.reads[MatchList]
}
