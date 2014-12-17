package thangiee.riotapi.team

import play.api.libs.json._

case class TeamStatDetail(averageGamesPlayed: Int, losses: Int, teamStatType:String, wins: Int)

object TeamStatDetail {
  implicit val teamStatDetailFmt = Json.format[TeamStatDetail]
}
