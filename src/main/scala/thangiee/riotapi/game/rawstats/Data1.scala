package thangiee.riotapi.game.rawstats

import play.api.libs.json._

case class Data1(
  assists: Int = 0,
  barracksKilled: Int = 0,
  championsKilled: Int = 0,
  combatPlayerScore: Int = 0,
  consumablesPurchased: Int = 0,
  damageDealtPlayer: Int = 0,
  doubleKills: Int = 0,
  firstBlood: Int = 0,
  gold: Int = 0,
  goldEarned: Int = 0,
  goldSpent: Int = 0,
  item0: Int = 0,
  item1: Int = 0,
  item2: Int = 0,
  item3: Int = 0,
  item4: Int = 0,
  item5: Int = 0,
  item6: Int = 0,
  itemsPurchased: Int = 0
  )

object Data1 {
  implicit val data1Fmt = Json.reads[Data1]
}
