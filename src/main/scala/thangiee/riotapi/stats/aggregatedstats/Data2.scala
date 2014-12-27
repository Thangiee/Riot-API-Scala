package thangiee.riotapi.stats.aggregatedstats

import play.api.libs.json._

case class Data2
(totalAssists: Option[Int],
 totalChampionKills: Option[Int],
 totalDamageDealt: Option[Int],
 totalDamageTaken: Option[Int],
 totalDeathsPerSession: Option[Int],
 totalDoubleKills: Option[Int],
 totalFirstBlood: Option[Int],
 totalGoldEarned: Option[Int],
 totalHeal: Option[Int],
 totalMagicDamageDealt: Option[Int],
 totalMinionKills: Option[Int],
 totalNeutralMinionsKilled: Option[Int],
 totalPentaKills: Option[Int],
 totalPhysicalDamageDealt: Option[Int],
 totalQuadraKills: Option[Int],
 totalSessionsLost: Option[Int],
 totalSessionsPlayed: Option[Int],
 totalSessionsWon: Option[Int],
 totalTripleKills: Option[Int],
 totalTurretsKilled: Option[Int],
 totalUnrealKills: Option[Int]
  )

object Data2 {
  implicit val data2Fmt = Json.reads[Data2]
}
