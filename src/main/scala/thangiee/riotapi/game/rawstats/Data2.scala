package thangiee.riotapi.game.rawstats

import play.api.libs.json._

case class Data2
(killingSprees: Option[Int],
 largestCriticalStrike: Option[Int],
 largestKillingSpree: Option[Int],
 largestMultiKill: Option[Int],
 legendaryItemsCreated: Option[Int],
 level: Option[Int],
 magicDamageDealtPlayer: Option[Int],
 magicDamageDealtToChampions: Option[Int],
 magicDamageTaken: Option[Int],
 minionsDenied: Option[Int],
 minionsKilled: Option[Int],
 neutralMinionsKilled: Option[Int],
 neutralMinionsKilledEnemyJungle: Option[Int],
 neutralMinionsKilledYourJungle: Option[Int],
 nexusKilled: Option[Boolean],
 nodeCapture: Option[Int],
 nodeCaptureAssist: Option[Int],
 nodeNeutralize: Option[Int],
 nodeNeutralizeAssist: Option[Int],
 numDeaths: Option[Int],
 numItemsBought: Option[Int]
  )

object Data2 {
  implicit val data2Fmt = Json.format[Data2]
}