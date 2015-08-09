package thangiee.riotapi.stats.aggregatedstats

import play.api.libs.json._

case class Data1(
  botGamesPlayed: Int = 0,
  killingSpree: Int = 0,
  maxChampionsKilled: Int = 0,
  maxLargestCriticalStrike: Int = 0,
  maxLargestKillingSpree: Int = 0,
  maxNumDeaths: Int = 0,
  maxTimePlayed: Int = 0,
  maxTimeSpentLiving: Int = 0,
  mostChampionKillsPerSession: Int = 0,
  mostSpellsCast: Int = 0,
  normalGamesPlayed: Int = 0,
  rankedPremadeGamesPlayed: Int = 0,
  rankedSoloGamesPlayed: Int = 0
  )

object Data1 {
  implicit val data2Fmt = Json.reads[Data1]
}
