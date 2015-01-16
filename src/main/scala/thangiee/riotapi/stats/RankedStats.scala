package thangiee.riotapi.stats

import play.api.libs.json._

case class RankedStats(champions: Option[List[ChampionStats]], modifyDate: Option[Long], summonerId: Option[Long]) {
  def getChampions = champions.getOrElse(List(ChampionStats()))
}

object RankedStats {
  implicit val rankedStatsFmt = Json.reads[RankedStats]
}
