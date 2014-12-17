package thangiee.riotapi.game.rawstats

import play.api.libs.json._

case class Data3
(objectivePlayerScore: Option[Int],
 pentaKills: Option[Int],
 physicalDamageDealtPlayer: Option[Int],
 physicalDamageDealtToChampions: Option[Int],
 physicalDamageTaken: Option[Int],
 quadraKills: Option[Int],
 sightWardsBought: Option[Int],
 spell1Cast: Option[Int],
 spell2Cast: Option[Int],
 spell3Cast: Option[Int],
 spell4Cast: Option[Int],
 summonSpell1Cast: Option[Int],
 summonSpell2Cast: Option[Int],
 superMonsterKilled: Option[Int],
 team: Option[Int],
 teamObjective: Option[Int],
 timePlayed: Option[Int],
 totalDamageDealt: Option[Int],
 totalDamageDealtToChampions: Option[Int],
 totalDamageTaken: Option[Int],
 totalHeal: Option[Int]
  )

object Data3 {
  implicit val data3Fmt = Json.format[Data3]
}