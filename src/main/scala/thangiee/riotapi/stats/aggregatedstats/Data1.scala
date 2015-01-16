package thangiee.riotapi.stats.aggregatedstats

import play.api.libs.json._

case class Data1
(botGamesPlayed: Option[Int] = None,
 killingSpree: Option[Int] = None,
 maxChampionsKilled: Option[Int] = None,
 maxLargestCriticalStrike: Option[Int] = None,
 maxLargestKillingSpree: Option[Int] = None,
 maxNumDeaths: Option[Int] = None,
 maxTimePlayed: Option[Int] = None,
 maxTimeSpentLiving: Option[Int] = None,
 mostChampionKillsPerSession: Option[Int] = None,
 mostSpellsCast: Option[Int] = None,
 normalGamesPlayed: Option[Int] = None,
 rankedPremadeGamesPlayed: Option[Int] = None,
 rankedSoloGamesPlayed: Option[Int] = None
  )

object Data1 {
  implicit val data2Fmt = Json.reads[Data1]
}
