package thangiee.riotapi.stats.aggregatedstats

import play.api.libs.json._

case class Data1
(botGamesPlayed: Option[Int],
 killingSpree: Option[Int],
 maxChampionsKilled: Option[Int],
 maxLargestCriticalStrike: Option[Int],
 maxLargestKillingSpree: Option[Int],
 maxNumDeaths: Option[Int],
 maxTimePlayed: Option[Int],
 maxTimeSpentLiving: Option[Int],
 mostChampionKillsPerSession: Option[Int],
 mostSpellsCast: Option[Int],
 normalGamesPlayed: Option[Int],
 rankedPremadeGamesPlayed: Option[Int],
 rankedSoloGamesPlayed: Option[Int]
  )

object Data1 {
  implicit val data2Fmt = Json.reads[Data1]
}
