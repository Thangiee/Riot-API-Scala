package thangiee.riotapi.stats


case class AggregatedStats(
  botGamesPlayed: Int = 0,
  killingSpree: Int = 0,
  maxChampionsKilled: Int = 0,
  maxLargestCriticalStrike: Int = 0,
  maxLargestKillingSpree: Int = 0,
  maxNumDeaths: Int = 0,
  maxTimePlayed: Int = 0,
  maxTimeSpentLiving: Int = 0,
  mostChampionKillsPerSession: Int = 0,
  mostSpellsCast: Int = 0,
  normalGamesPlayed: Int = 0,
  rankedPremadeGamesPlayed: Int = 0,
  rankedSoloGamesPlayed: Int = 0,
  totalAssists: Int = 0,
  totalChampionKills: Int = 0,
  totalDamageDealt: Int = 0,
  totalDamageTaken: Int = 0,
  totalDeathsPerSession: Int = 0,
  totalDoubleKills: Int = 0,
  totalFirstBlood: Int = 0,
  totalGoldEarned: Int = 0,
  totalHeal: Int = 0,
  totalMagicDamageDealt: Int = 0,
  totalMinionKills: Int = 0,
  totalNeutralMinionsKilled: Int = 0,
  totalPentaKills: Int = 0,
  totalPhysicalDamageDealt: Int = 0,
  totalQuadraKills: Int = 0,
  totalSessionsLost: Int = 0,
  totalSessionsPlayed: Int = 0,
  totalSessionsWon: Int = 0,
  totalTripleKills: Int = 0,
  totalTurretsKilled: Int = 0,
  totalUnrealKills: Int = 0,
  averageAssists: Int = 0,
  averageChampionsKilled: Int = 0,
  averageCombatPlayerScore: Int = 0,
  averageNodeCapture: Int = 0,
  averageNodeCaptureAssist: Int = 0,
  averageNodeNeutralize: Int = 0,
  averageNodeNeutralizeAssist: Int = 0,
  averageNumDeaths: Int = 0,
  averageObjectivePlayerScore: Int = 0,
  averageTeamObjective: Int = 0,
  averageTotalPlayerScore: Int = 0,
  maxAssists: Int = 0,
  maxCombatPlayerScore: Int = 0,
  maxNodeCapture: Int = 0,
  maxNodeCaptureAssist: Int = 0,
  maxNodeNeutralize: Int = 0,
  maxNodeNeutralizeAssist: Int = 0,
  maxObjectivePlayerScore: Int = 0,
  maxTeamObjective: Int = 0,
  maxTotalPlayerScore: Int = 0,
  totalNodeCapture: Int = 0,
  totalNodeNeutralize: Int = 0
  )