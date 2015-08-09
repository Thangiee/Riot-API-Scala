package thangiee.riotapi.game.rawstats

import play.api.libs.json._

case class Data3(
  objectivePlayerScore: Int = 0,
  pentaKills: Int = 0,
  physicalDamageDealtPlayer: Int = 0,
  physicalDamageDealtToChampions: Int = 0,
  physicalDamageTaken: Int = 0,
  quadraKills: Int = 0,
  sightWardsBought: Int = 0,
  spell1Cast: Int = 0,
  spell2Cast: Int = 0,
  spell3Cast: Int = 0,
  spell4Cast: Int = 0,
  summonSpell1Cast: Int = 0,
  summonSpell2Cast: Int = 0,
  superMonsterKilled: Int = 0,
  team: Int = 0,
  teamObjective: Int = 0,
  timePlayed: Int = 0,
  totalDamageDealt: Int = 0,
  totalDamageDealtToChampions: Int = 0,
  totalDamageTaken: Int = 0,
  totalHeal: Int = 0
  )

object Data3 {
  implicit val data3Fmt = Json.format[Data3]
}