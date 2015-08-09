package thangiee.riotapi.stats

import play.api.libs.json._

case class RankedStats(
  champions: List[ChampionStats] = Nil,
  modifyDate: Long = 0,
  summonerId: Long = 0
  )

object RankedStats {
  implicit val rankedStatsFmt = Json.reads[RankedStats]
}
