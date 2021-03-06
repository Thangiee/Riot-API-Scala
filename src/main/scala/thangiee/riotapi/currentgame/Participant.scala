package thangiee.riotapi.currentgame

case class Participant(
  bot: Boolean = false,
  championId: Long = 0,
  masteries: List[Mastery] = Nil,
  profileIconId: Long = 0,
  runes: List[Rune] = Nil,
  spell1Id: Long = 0,
  spell2Id: Long = 0,
  summonerId: Long = 0 ,
  summonerName: String = "",
  teamId: Long = 0
  )
