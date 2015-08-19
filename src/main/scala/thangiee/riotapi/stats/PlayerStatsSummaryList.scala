package thangiee.riotapi.stats

import play.api.libs.json._

case class PlayerStatsSummaryList(
  playerStatSummaries: List[PlayerStatsSummary],
  summonerId: Long
  )

object PlayerStatsSummaryList {
  implicit val playerStatsSummaryListFmt = Json.reads[PlayerStatsSummaryList]
}
