package thangiee.riotapi.stats

case class RankedStats(
  champions: List[ChampionStats],
  modifyDate: Long,
  summonerId: Long
  )

