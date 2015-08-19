package thangiee.riotapi.`match`

case class Event(
  ascendedType: String = "",
  assistingParticipantIds: List[Int] = Nil,
  buildingType: String = "",
  creatorId: Int = 0,
  eventType: String = "",
  itemAfter: Int = 0,
  itemBefore: Int = 0,
  itemId: Int = 0,
  killerId: Int = 0,
  laneType: String = "",
  levelUpType: String = "",
  monsterType: String = "",
  participantId: Int = 0,
  pointCaptured: String = "",
  position: Position = Position(),
  skillSlot: Int = 0,
  teamId: Int = 0,
  timestamp: Long = 0,
  towerType: String = "",
  victimId: Int = 0,
  wardType: String = ""
  )