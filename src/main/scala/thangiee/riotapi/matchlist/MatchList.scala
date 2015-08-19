package thangiee.riotapi.matchlist

case class MatchList(
  endIndex: Int,
  matches: List[MatchReference],
  startIndex: Int,
  totalGames: Int
  )