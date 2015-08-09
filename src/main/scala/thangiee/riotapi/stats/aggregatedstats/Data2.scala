package thangiee.riotapi.stats.aggregatedstats

import play.api.libs.json._

case class Data2(
  totalAssists: Int = 0,
  totalChampionKills: Int = 0,
  totalDamageDealt: Int = 0,
  totalDamageTaken: Int = 0,
  totalDeathsPerSession: Int = 0,
  totalDoubleKills: Int = 0,
  totalFirstBlood: Int = 0,
  totalGoldEarned: Int = 0,
  totalHeal: Int = 0,
  totalMagicDamageDealt: Int = 0,
  totalMinionKills: Int = 0,
  totalNeutralMinionsKilled: Int = 0,
  totalPentaKills: Int = 0,
  totalPhysicalDamageDealt: Int = 0,
  totalQuadraKills: Int = 0,
  totalSessionsLost: Int = 0,
  totalSessionsPlayed: Int = 0,
  totalSessionsWon: Int = 0,
  totalTripleKills: Int = 0,
  totalTurretsKilled: Int = 0,
  totalUnrealKills: Int = 0
  )

object Data2 {
  implicit val data2Fmt = Json.reads[Data2]
}
