package thangiee.riotapi.game.rawstats

import play.api.libs.json._

case class Data3
(objectivePlayerScore: Option[Int] = None,
 pentaKills: Option[Int] = None,
 physicalDamageDealtPlayer: Option[Int] = None,
 physicalDamageDealtToChampions: Option[Int] = None,
 physicalDamageTaken: Option[Int] = None,
 quadraKills: Option[Int] = None,
 sightWardsBought: Option[Int] = None,
 spell1Cast: Option[Int] = None,
 spell2Cast: Option[Int] = None,
 spell3Cast: Option[Int] = None,
 spell4Cast: Option[Int] = None,
 summonSpell1Cast: Option[Int] = None,
 summonSpell2Cast: Option[Int] = None,
 superMonsterKilled: Option[Int] = None,
 team: Option[Int] = None,
 teamObjective: Option[Int] = None,
 timePlayed: Option[Int] = None,
 totalDamageDealt: Option[Int] = None,
 totalDamageDealtToChampions: Option[Int] = None,
 totalDamageTaken: Option[Int] = None,
 totalHeal: Option[Int] = None
  )

object Data3 {
  implicit val data3Fmt = Json.format[Data3]
}