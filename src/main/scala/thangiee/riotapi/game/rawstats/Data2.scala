package thangiee.riotapi.game.rawstats

import play.api.libs.json._

case class Data2(
  killingSprees: Int = 0,
  largestCriticalStrike: Int = 0,
  largestKillingSpree: Int = 0,
  largestMultiKill: Int = 0,
  legendaryItemsCreated: Int = 0,
  level: Int = 0,
  magicDamageDealtPlayer: Int = 0,
  magicDamageDealtToChampions: Int = 0,
  magicDamageTaken: Int = 0,
  minionsDenied: Int = 0,
  minionsKilled: Int = 0,
  neutralMinionsKilled: Int = 0,
  neutralMinionsKilledEnemyJungle: Int = 0,
  neutralMinionsKilledYourJungle: Int = 0,
  nexusKilled: Boolean = false,
  nodeCapture: Int = 0,
  nodeCaptureAssist: Int = 0,
  nodeNeutralize: Int = 0,
  nodeNeutralizeAssist: Int = 0,
  numDeaths: Int = 0,
  numItemsBought: Int =0
  )

object Data2 {
  implicit val data2Fmt = Json.format[Data2]
}