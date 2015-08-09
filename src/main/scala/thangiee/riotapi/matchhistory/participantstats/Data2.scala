package thangiee.riotapi.matchhistory.participantstats

import play.api.libs.json.Json

case class Data2(
  kills: Long = 0,
  largestCriticalStrike: Long = 0,
  largestKillingSpree: Long = 0,
  largestMultiKill: Long = 0,
  magicDamageDealt: Long = 0,
  magicDamageDealtToChampions: Long = 0,
  magicDamageTaken: Long = 0,
  minionsKilled: Long = 0,
  neutralMinionsKilled: Long = 0,
  neutralMinionsKilledEnemyJungle: Long = 0,
  neutralMinionsKilledTeamJungle: Long = 0,
  nodeCapture: Long = 0,
  nodeCaptureAssist: Long = 0,
  nodeNeutralize: Long = 0,
  nodeNeutralizeAssist: Long = 0,
  objectivePlayerScore: Long = 0,
  pentaKills: Long = 0,
  physicalDamageDealt: Long = 0,
  physicalDamageDealtToChampions: Long = 0,
  physicalDamageTaken: Long = 0,
  quadraKills: Long = 0,
  sightWardsBoughtInGame: Long = 0
  )

object Data2 {
  implicit val data2Fmt = Json.reads[Data2]
}

