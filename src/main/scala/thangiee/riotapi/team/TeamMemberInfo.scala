package thangiee.riotapi.team

import play.api.libs.json._

case class TeamMemberInfo(inviteDate: Long, joinDate: Long, playerId: Long, status: String)

object TeamMemberInfo {
  implicit val teamMemberInfoFmt = Json.format[TeamMemberInfo]
}
