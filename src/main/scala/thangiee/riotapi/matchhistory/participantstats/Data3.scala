package thangiee.riotapi.matchhistory.participantstats

import play.api.libs.json.Json

case class Data3(
  teamObjective: Long = 0,
  totalDamageDealt: Long = 0,
  totalDamageDealtToChampions: Long = 0,
  totalDamageTaken: Long = 0,
  totalHeal: Long = 0,
  totalPlayerScore: Long = 0,
  totalScoreRank: Long = 0,
  totalTimeCrowdControlDealt: Long = 0,
  totalUnitsHealed: Long = 0,
  towerKills: Long = 0,
  tripleKills: Long = 0,
  trueDamageDealt: Long = 0,
  trueDamageDealtToChampions: Long = 0,
  trueDamageTaken: Long = 0,
  unrealKills: Long = 0,
  visionWardsBoughtInGame: Long = 0,
  wardsKilled: Long = 0,
  wardsPlaced: Long = 0,
  winner: Boolean = false
  )

object Data3 {
  implicit val data3Fmt = Json.reads[Data3]
}

