package thangiee.riotapi.currentgame

import play.api.libs.json._

case class Participant
(bot: Boolean,
 championId: Long,
 masteries: List[Mastery],
 profileIconId: Long,
 runes: List[Rune],
 spell1Id: Long,
 spell2Id: Long,
 summonerId: Long,
 summonerName: String,
 teamId: Long
  )

object Participant {
  implicit val participantFmt = Json.reads[Participant]
}
