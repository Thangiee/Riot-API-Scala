package thangiee.riotapi.summoner

import play.api.libs.json._

case class Summoner(
  id: Long = 0,
  name: String = "",
  profileIconId: Int = 0,
  revisionDate: Long = 0,
  summonerLevel: Int = 0
  )

object Summoner {
  implicit val summonerFmt = Json.reads[Summoner]
}