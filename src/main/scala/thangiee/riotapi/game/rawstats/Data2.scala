package thangiee.riotapi.game.rawstats

import play.api.libs.json._

case class Data2
(killingSprees: Option[Int] = None,
 largestCriticalStrike: Option[Int] = None,
 largestKillingSpree: Option[Int] = None,
 largestMultiKill: Option[Int] = None,
 legendaryItemsCreated: Option[Int] = None,
 level: Option[Int] = None,
 magicDamageDealtPlayer: Option[Int] = None,
 magicDamageDealtToChampions: Option[Int] = None,
 magicDamageTaken: Option[Int] = None,
 minionsDenied: Option[Int] = None,
 minionsKilled: Option[Int] = None,
 neutralMinionsKilled: Option[Int] = None,
 neutralMinionsKilledEnemyJungle: Option[Int] = None,
 neutralMinionsKilledYourJungle: Option[Int] = None,
 nexusKilled: Option[Boolean] = None,
 nodeCapture: Option[Int] = None,
 nodeCaptureAssist: Option[Int] = None,
 nodeNeutralize: Option[Int] = None,
 nodeNeutralizeAssist: Option[Int] = None,
 numDeaths: Option[Int] = None,
 numItemsBought: Option[Int] = None
  )

object Data2 {
  implicit val data2Fmt = Json.format[Data2]
}