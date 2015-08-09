package thangiee.riotapi.matchhistory

import play.api.libs.json.Json

case class PlayerHistory(matches: List[MatchSummary] = Nil)

object PlayerHistory {
  implicit val playerHistoryFmt = Json.reads[PlayerHistory]
}