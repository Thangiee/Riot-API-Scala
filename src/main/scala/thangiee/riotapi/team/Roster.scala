package thangiee.riotapi.team

import play.api.libs.json._

case class Roster(memberList: List[TeamMemberInfo] = Nil, ownerId: Long = 0)

object Roster {
  implicit val rosterFmt = Json.reads[Roster]
}

