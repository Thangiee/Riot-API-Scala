package thangiee.riotapi.league

import play.api.libs.json._

case class League
(entries: List[LeagueEntry],
 name: String,
 participantId: Option[String],
 queue: String,
 tier: String
  )

object League {
  implicit val leagueFmt = Json.reads[League]
}
