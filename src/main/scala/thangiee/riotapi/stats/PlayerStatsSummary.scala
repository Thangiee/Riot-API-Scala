package thangiee.riotapi.stats

import play.api.libs.json._
import thangiee.riotapi.stats.aggregatedstats.AggregatedStats

case class PlayerStatsSummary(
  aggregatedStats: AggregatedStats = AggregatedStats(),
  losses: Int = 0,
  modifyDate: Long = 0,
  playerStatSummaryType: String = "",
  wins: Int = 0
  )

object PlayerStatsSummary {
  implicit val playerStatsSummaryFmt = Json.reads[PlayerStatsSummary]
}
