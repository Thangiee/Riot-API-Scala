package thangiee.riotapi.`match`

import play.api.libs.json.Json

case class Frame(event: Event = Event(), participantFrames: Map[String, ParticipantFrame] = Map.empty, timestamp: Long = 0)

object Frame {
  implicit val frameFmt = Json.reads[Frame]
}
