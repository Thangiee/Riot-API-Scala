package thangiee.riotapi.stats

case class PlayerStatsSummary(
  aggregatedStats: AggregatedStats = AggregatedStats(),
  losses: Int = 0,
  modifyDate: Long = 0,
  playerStatSummaryType: String = "",
  wins: Int = 0
  )

