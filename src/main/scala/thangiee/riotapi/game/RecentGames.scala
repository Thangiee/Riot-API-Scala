package thangiee.riotapi.game

import play.api.libs.json._

case class RecentGames(games: List[Game], summonerId: Long)

object RecentGames {
  implicit val recentGamesFmt = Json.reads[RecentGames]
}
