package thangiee.riotapi.stats.aggregatedstats

import play.api.libs.json._

case class Data2
(totalAssists: Int,
 totalChampionKills: Int,
 totalDamageDealt: Int,
 totalDamageTaken: Int,
 totalDeathsPerSession: Option[Int],
 totalDoubleKills: Int,
 totalFirstBlood: Int,
 totalGoldEarned: Int,
 totalHeal: Int,
 totalMagicDamageDealt: Int,
 totalMinionKills: Int,
 totalNeutralMinionsKilled: Int,
 totalPentaKills: Int,
 totalPhysicalDamageDealt: Int,
 totalQuadraKills: Int,
 totalSessionsLost: Int,
 totalSessionsPlayed: Int,
 totalSessionsWon: Int,
 totalTripleKills: Int,
 totalTurretsKilled: Int,
 totalUnrealKills: Int
  )

object Data2 {
  implicit val data2Fmt = Json.reads[Data2]
}
