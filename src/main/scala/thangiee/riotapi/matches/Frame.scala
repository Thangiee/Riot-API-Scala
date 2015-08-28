package thangiee.riotapi.matches

case class Frame(event: Event = Event(), participantFrames: Map[String, ParticipantFrame] = Map.empty, timestamp: Long = 0)

