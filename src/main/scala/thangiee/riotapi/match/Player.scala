package thangiee.riotapi.`match`

import play.api.libs.json.Json

case class Player(
  matchHistoryUri: String = "",
  profileIcon: Int = 0,
  summonerId: Long = 0,
  summonerName: String = ""
  )

object Player {
  implicit val playerFmt = Json.reads[Player]
}
