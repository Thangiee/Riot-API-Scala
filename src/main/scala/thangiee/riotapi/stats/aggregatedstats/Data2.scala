package thangiee.riotapi.stats.aggregatedstats

import play.api.libs.json._

case class Data2
(totalAssists: Option[Int] = None,
 totalChampionKills: Option[Int] = None,
 totalDamageDealt: Option[Int] = None,
 totalDamageTaken: Option[Int] = None,
 totalDeathsPerSession: Option[Int] = None,
 totalDoubleKills: Option[Int] = None,
 totalFirstBlood: Option[Int] = None,
 totalGoldEarned: Option[Int] = None,
 totalHeal: Option[Int] = None,
 totalMagicDamageDealt: Option[Int] = None,
 totalMinionKills: Option[Int] = None,
 totalNeutralMinionsKilled: Option[Int] = None,
 totalPentaKills: Option[Int] = None,
 totalPhysicalDamageDealt: Option[Int] = None,
 totalQuadraKills: Option[Int] = None,
 totalSessionsLost: Option[Int] = None,
 totalSessionsPlayed: Option[Int] = None,
 totalSessionsWon: Option[Int] = None,
 totalTripleKills: Option[Int] = None,
 totalTurretsKilled: Option[Int] = None,
 totalUnrealKills: Option[Int] = None
  )

object Data2 {
  implicit val data2Fmt = Json.reads[Data2]
}
