package thangiee.riotapi.summoner

case class RunePage(current: Boolean = false, id: Long = 0, name: String = "", slots: Set[RuneSlot] = Set())

