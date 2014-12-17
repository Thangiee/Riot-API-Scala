package thangiee.riotapi.game

import play.api.libs.json._
import thangiee.riotapi.game.rawstats.RawStats

case class Game
(championId: Int,
 createDate: Long,
 fellowPlayers: Option[List[Player]], // Parameter is not in json when a game is played alone
 gameId: Long,
 gameMode: String,
 gameType: String,
 invalid: Boolean,
 ipEarned: Int,
 level: Int,
 mapId: Int,
 spell1: Int,
 spell2: Int,
 stats: RawStats,
 subType: String,
 teamId: Int
  )

object Game {
  implicit val gameFmt = Json.reads[Game]
}
