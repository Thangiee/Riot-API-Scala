package thangiee.riotapi.`match`

import play.api.libs.json.Json

case class ParticipantIdentity(participantId: Int = 0, player: Player = Player())

object ParticipantIdentity {
  implicit val participantIdentityFmt = Json.reads[ParticipantIdentity]
}