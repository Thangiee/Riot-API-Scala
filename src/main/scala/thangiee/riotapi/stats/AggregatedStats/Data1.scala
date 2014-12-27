package thangiee.riotapi.stats.AggregatedStats

import play.api.libs.json._

case class Data1
(botGamesPlayed: Int,
 killingSpree: Int,
 maxChampionsKilled: Int,
 maxLargestCriticalStrike: Int,
 maxLargestKillingSpree: Int,
 maxNumDeaths: Option[Int],
 maxTimePlayed: Int,
 maxTimeSpentLiving: Int,
 mostChampionKillsPerSession: Int,
 mostSpellsCast: Int,
 normalGamesPlayed: Int,
 rankedPremadeGamesPlayed: Int,
 rankedSoloGamesPlayed: Int
  )

object Data1 {
  implicit val data2Fmt = Json.reads[Data1]
}
