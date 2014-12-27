package thangiee.riotapi.stats

import play.api.libs.json._

case class RankedStats(champions: List[ChampionStats], modifyDate: Long, summonerId: Long)

object RankedStats {
  implicit val rankedStatsFmt = Json.reads[RankedStats]
}
