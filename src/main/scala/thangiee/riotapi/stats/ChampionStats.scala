package thangiee.riotapi.stats

import play.api.libs.json._
import thangiee.riotapi.stats.AggregatedStats.AggregatedStats

case class ChampionStats(id: Int, stats: AggregatedStats)

object ChampionStats {
  implicit val championStatsFmt = Json.reads[ChampionStats]
}