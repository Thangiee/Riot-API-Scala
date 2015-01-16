package thangiee.riotapi.game

import play.api.libs.json._

case class Player(championId: Int = 0, summonerId: Long = 0, teamId: Int = 0)

object Player {
  implicit val playerFmt = Json.reads[Player]
}
