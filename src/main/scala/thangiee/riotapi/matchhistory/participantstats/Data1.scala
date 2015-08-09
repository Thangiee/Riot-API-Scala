package thangiee.riotapi.matchhistory.participantstats

import play.api.libs.json.Json

case class Data1(
  assists: Long = 0,
  champLevel: Long = 0,
  combatPlayerScore: Long = 0,
  deaths: Long = 0,
  doubleKills: Long = 0,
  firstBloodAssist: Boolean = false,
  firstBloodKill: Boolean = false,
  firstInhibitorAssist: Boolean = false,
  firstInhibitorKill: Boolean = false,
  firstTowerAssist: Boolean = false,
  firstTowerKill: Boolean = false,
  goldEarned: Long = 0,
  goldSpent: Long = 0,
  inhibitorKills: Long = 0,
  item0: Long = 0,
  item1: Long = 0,
  item2: Long = 0,
  item3: Long = 0,
  item4: Long = 0,
  item5: Long = 0,
  item6: Long = 0,
  killingSprees: Long = 0
  )

object Data1 {
  implicit val data1Fmt = Json.reads[Data1]
}