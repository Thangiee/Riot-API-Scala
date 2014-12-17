package thangiee.riotapi.game.rawstats

import play.api.libs.json._

case class Data4
(totalPlayerScore: Option[Int],
 totalScoreRank: Option[Int],
 totalTimeCrowdControlDealt: Option[Int],
 totalUnitsHealed: Option[Int],
 tripleKills: Option[Int],
 trueDamageDealtPlayer: Option[Int],
 trueDamageDealtToChampions: Option[Int],
 trueDamageTaken: Option[Int],
 turretsKilled: Option[Int],
 unrealKills: Option[Int],
 victoryPointTotal: Option[Int],
 visionWardsBought: Option[Int],
 wardKilled: Option[Int],
 wardPlaced: Option[Int],
 win: Option[Boolean]
  )

object Data4 {
  implicit val data4Fmt = Json.format[Data4]
}
