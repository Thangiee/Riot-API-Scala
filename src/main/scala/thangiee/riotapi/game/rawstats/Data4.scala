package thangiee.riotapi.game.rawstats

import play.api.libs.json._

case class Data4(
  totalPlayerScore: Int = 0,
  totalScoreRank: Int = 0,
  totalTimeCrowdControlDealt: Int = 0,
  totalUnitsHealed: Int = 0,
  tripleKills: Int = 0,
  trueDamageDealtPlayer: Int = 0,
  trueDamageDealtToChampions: Int = 0,
  trueDamageTaken: Int = 0,
  turretsKilled: Int = 0,
  unrealKills: Int = 0,
  victoryPointTotal: Int = 0,
  visionWardsBought: Int = 0,
  wardKilled: Int = 0,
  wardPlaced: Int = 0,
  win: Boolean = false
  )

object Data4 {
  implicit val data4Fmt = Json.format[Data4]
}
