package thangiee.riotapi.stats

import play.api.libs.json._

case class PlayerStatsSummaryList(
  playerStatSummaries: List[PlayerStatsSummary] = Nil,
  summonerId: Long = 0
  )

object PlayerStatsSummaryList {
  implicit val playerStatsSummaryListFmt = Json.reads[PlayerStatsSummaryList]
}
