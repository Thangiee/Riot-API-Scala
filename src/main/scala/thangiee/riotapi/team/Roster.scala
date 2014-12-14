package thangiee.riotapi.team

import play.api.libs.json._

case class Roster(memberList: List[TeamMemberInfo], ownerId: Long)

object Roster {
  implicit val rosterFmt = Json.format[Roster]
}

