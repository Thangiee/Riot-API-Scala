package thangiee.riotapi.`match`

case class Frame(event: Event = Event(), participantFrames: Map[String, ParticipantFrame] = Map.empty, timestamp: Long = 0)

