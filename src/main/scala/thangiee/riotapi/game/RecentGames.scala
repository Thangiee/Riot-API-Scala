package thangiee.riotapi.game

import play.api.libs.json._

case class RecentGames(games: List[Game] = Nil, summonerId: Long = 0)

object RecentGames {
  implicit val recentGamesFmt = Json.reads[RecentGames]
}
