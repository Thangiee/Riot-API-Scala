package thangiee.riotapi.team

import play.api.libs.json._

case class TeamMemberInfo(inviteDate: Long = 0, joinDate: Long = 0, playerId: Long = 0, status: String = "")

object TeamMemberInfo {
  implicit val teamMemberInfoFmt = Json.reads[TeamMemberInfo]
}
