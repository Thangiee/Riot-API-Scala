package thangiee.riotapi.stats

import play.api.libs.json._
import thangiee.riotapi.stats.aggregatedstats.AggregatedStats

case class PlayerStatsSummary
(aggregatedStats: AggregatedStats,
 losses: Option[Int],
 modifyDate: Long,
 playerStatSummaryType: String,
 wins: Int
  )

object PlayerStatsSummary {
  implicit val playerStatsSummaryFmt = Json.reads[PlayerStatsSummary]
}
