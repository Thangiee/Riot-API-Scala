package thangiee.riotapi.game.rawstats

import play.api.libs.json._

case class Data4
(totalPlayerScore: Option[Int] = None,
 totalScoreRank: Option[Int] = None,
 totalTimeCrowdControlDealt: Option[Int] = None,
 totalUnitsHealed: Option[Int] = None,
 tripleKills: Option[Int] = None,
 trueDamageDealtPlayer: Option[Int] = None,
 trueDamageDealtToChampions: Option[Int] = None,
 trueDamageTaken: Option[Int] = None,
 turretsKilled: Option[Int] = None,
 unrealKills: Option[Int] = None,
 victoryPointTotal: Option[Int] = None,
 visionWardsBought: Option[Int] = None,
 wardKilled: Option[Int] = None,
 wardPlaced: Option[Int] = None,
 win: Option[Boolean] = None
  )

object Data4 {
  implicit val data4Fmt = Json.format[Data4]
}
