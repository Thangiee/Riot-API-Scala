package thangiee.riotapi.matchlist

import play.api.libs.json.Json

case class MatchReference(
  champion: Long = 0,
  lane: String = "",
  matchId: Long = 0,
  platformId: String = "",
  queue: String = "",
  role: String = "",
  season: String = "",
  timestamp: Long = 0
)

object MatchReference {
  implicit val matchReferenceFmt = Json.reads[MatchReference]
}
