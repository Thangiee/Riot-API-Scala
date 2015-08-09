package thangiee.riotapi.`match`

import play.api.libs.json.Json

case class Team(
  bans: List[BannedChampion] = Nil,
  baronKills: Int = 0,
  dominionVictoryScore: Long = 0,
  dragonKills: Int = 0,
  firstBaron: Boolean = false,
  firstBlood: Boolean = false,
  firstDragon: Boolean = false,
  firstInhibitor: Boolean = false,
  firstTower: Boolean = false,
  inhibitorKills: Int = 0,
  teamId: Int = 0,
  towerKills: Int = 0,
  vilemawKills: Int = 0,
  winner: Boolean = false
  )

object Team {
  implicit val teamFmt = Json.reads[Team]
}
