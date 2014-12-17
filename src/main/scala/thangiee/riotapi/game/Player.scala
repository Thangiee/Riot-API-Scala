package thangiee.riotapi.game

import play.api.libs.json._

case class Player(championId: Int, summonerId: Long, teamId: Int)

object Player {
  implicit val playerFmt = Json.reads[Player]
}
