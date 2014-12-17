package thangiee.riotapi.game.rawstats

import play.api.libs.json._

case class Data1
(assists: Option[Int],
 barracksKilled: Option[Int],
 championsKilled: Option[Int],
 combatPlayerScore: Option[Int],
 consumablesPurchased: Option[Int],
 damageDealtPlayer: Option[Int],
 doubleKills: Option[Int],
 firstBlood: Option[Int],
 gold: Option[Int],
 goldEarned: Option[Int],
 goldSpent: Option[Int],
 item0: Option[Int],
 item1: Option[Int],
 item2: Option[Int],
 item3: Option[Int],
 item4: Option[Int],
 item5: Option[Int],
 item6: Option[Int],
 itemsPurchased: Option[Int
])

object Data1 {
  implicit val data1Fmt = Json.reads[Data1]
}
