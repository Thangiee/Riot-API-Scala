package thangiee.riotapi.matchlist

import play.api.libs.json.Json

case class MatchList(
  endIndex: Int = 0,
  matches: List[MatchReference] = Nil,
  startIndex: Int = 0,
  totalGames: Int = 0
  )

object MatchList {
  implicit val matchListFmt = Json.reads[MatchList]
}
