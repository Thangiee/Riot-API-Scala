package thangiee.riotapi.stats

import play.api.libs.json._
import thangiee.riotapi.stats.AggregatedStats.AggregatedStats

case class PlayerStatsSummary
(aggregatedStats: AggregatedStats,
 losses: Int,
 modifyDate: Long,
 playerStatSummaryType: String,
 wins: Int
  )

object PlayerStatsSummary {
  implicit val playerStatsSummaryFmt = Json.reads[PlayerStatsSummary]
}
