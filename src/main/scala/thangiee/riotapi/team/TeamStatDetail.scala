package thangiee.riotapi.team

import play.api.libs.json._

case class TeamStatDetail(averageGamesPlayed: Int = 0, losses: Int = 0, teamStatType:String = "", wins: Int = 0)

object TeamStatDetail {
  implicit val teamStatDetailFmt = Json.reads[TeamStatDetail]
}
