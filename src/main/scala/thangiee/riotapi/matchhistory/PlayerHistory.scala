package thangiee.riotapi.matchhistory

import play.api.libs.json.Json

case class PlayerHistory(matches: List[MatchSummary])

object PlayerHistory {
  implicit val playerHistoryFmt = Json.reads[PlayerHistory]
}