package thangiee.riotapi.game

import play.api.libs.json._
import thangiee.riotapi.game.rawstats.RawStats

case class Game
(championId: Int = 0,
 createDate: Long = 0,
 fellowPlayers: Option[List[Player]] = None, // Parameter is not in json when a game is played alone
 gameId: Long = 0,
 gameMode: String = "",
 gameType: String = "",
 invalid: Boolean = true,
 ipEarned: Int = 0,
 level: Int = 0,
 mapId: Int = 0,
 spell1: Int = 0,
 spell2: Int = 0,
 stats: Option[RawStats] = None,
 subType: String = "",
 teamId: Int = 0
  )

object Game {
  implicit val gameFmt = Json.reads[Game]
}
