package thangiee.riotapi.summoner

import play.api.libs.json._

case class Summoner(
  id: Long,
  name: String,
  profileIconId: Int,
  revisionDate: Long,
  summonerLevel: Int
  )

object Summoner {
  implicit val summonerFmt = Json.reads[Summoner]
}