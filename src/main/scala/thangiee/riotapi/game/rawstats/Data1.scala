package thangiee.riotapi.game.rawstats

import play.api.libs.json._

case class Data1
(assists: Option[Int] = None,
 barracksKilled: Option[Int] = None,
 championsKilled: Option[Int] = None,
 combatPlayerScore: Option[Int] = None,
 consumablesPurchased: Option[Int] = None,
 damageDealtPlayer: Option[Int] = None,
 doubleKills: Option[Int] = None,
 firstBlood: Option[Int] = None,
 gold: Option[Int] = None,
 goldEarned: Option[Int] = None,
 goldSpent: Option[Int] = None,
 item0: Option[Int] = None,
 item1: Option[Int] = None,
 item2: Option[Int] = None,
 item3: Option[Int] = None,
 item4: Option[Int] = None,
 item5: Option[Int] = None,
 item6: Option[Int] = None,
 itemsPurchased: Option[Int] = None
  )

object Data1 {
  implicit val data1Fmt = Json.reads[Data1]
}
