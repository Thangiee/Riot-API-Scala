package thangiee.riotapi.matchhistory

import play.api.libs.json.Json

case class ParticipantIdentity(participantId: Int = 0, player: Player)

object ParticipantIdentity {
  implicit val participantIdentityFmt = Json.reads[ParticipantIdentity]
}