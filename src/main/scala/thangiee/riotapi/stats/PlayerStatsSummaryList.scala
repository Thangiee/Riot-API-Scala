package thangiee.riotapi.stats

import play.api.libs.json._

case class PlayerStatsSummaryList(playerStatSummaries: Option[List[PlayerStatsSummary]], summonerId: Option[Long]) {
  def getPlayerStatSummaries = playerStatSummaries.getOrElse(List(PlayerStatsSummary()))
}

object PlayerStatsSummaryList {
  implicit val playerStatsSummaryListFmt = Json.reads[PlayerStatsSummaryList]
}
