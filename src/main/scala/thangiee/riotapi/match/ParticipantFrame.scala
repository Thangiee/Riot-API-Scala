package thangiee.riotapi.`match`

import play.api.libs.json.Json

case class ParticipantFrame(
  currentGold: Int = 0,
  dominionScore: Int = 0,
  jungleMinionsKilled: Int = 0,
  level: Int = 0,
  minionsKilled: Int = 0,
  participantId: Int = 0,
  position: Position,
  teamScore: Int = 0,
  totalGold: Int = 0,
  xp: Int = 0
  )

object ParticipantFrame {
  implicit val participantFrameFmt = Json.reads[ParticipantFrame]
}
