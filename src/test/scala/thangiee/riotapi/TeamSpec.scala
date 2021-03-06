package thangiee.riotapi

import org.scalactic.{Bad, Good}

class TeamSpec extends BaseSpec {

  "RiotApi.teamBySummonerIds" should "return a map of teams whose keys correspond to the summoner's id" in new MockScope {
    def json: String = "{\"46311124\":[{\"fullId\":\"TEAM-ee6a1f40-75ff-11e4-8450-c81f66ddabda\",\"name\":\"PeterCopter\",\"tag\":\"vavbb\",\"status\":\"RANKED\",\"teamStatDetails\":[{\"teamStatType\":\"RANKED_TEAM_5x5\",\"wins\":0,\"losses\":0,\"averageGamesPlayed\":0},{\"teamStatType\":\"RANKED_TEAM_3x3\",\"wins\":5,\"losses\":0,\"averageGamesPlayed\":0}],\"roster\":{\"ownerId\":46311124,\"memberList\":[{\"playerId\":46311124,\"joinDate\":1418167009000,\"inviteDate\":1418167000000,\"status\":\"MEMBER\"},{\"playerId\":40288315,\"joinDate\":1418171394000,\"inviteDate\":1418167095000,\"status\":\"MEMBER\"}]},\"matchHistory\":[{\"kills\":27,\"deaths\":18,\"opposingTeamKills\":17,\"assists\":30,\"gameMode\":\"CLASSIC\",\"opposingTeamName\":\"Lorena takes it deep\",\"win\":true,\"invalid\":false,\"mapId\":10,\"gameId\":1646535615,\"date\":1417078767758},{\"kills\":13,\"deaths\":6,\"opposingTeamKills\":6,\"assists\":22,\"gameMode\":\"CLASSIC\",\"opposingTeamName\":\"Game Design\",\"win\":true,\"invalid\":false,\"mapId\":10,\"gameId\":1646516633,\"date\":1417077187809},{\"kills\":13,\"deaths\":8,\"opposingTeamKills\":8,\"assists\":20,\"gameMode\":\"CLASSIC\",\"opposingTeamName\":\"The Core Three\",\"win\":true,\"invalid\":false,\"mapId\":10,\"gameId\":1646522671,\"date\":1417075665425},{\"kills\":19,\"deaths\":14,\"opposingTeamKills\":14,\"assists\":21,\"gameMode\":\"CLASSIC\",\"opposingTeamName\":\"The Kawaii Squad\",\"win\":true,\"invalid\":false,\"mapId\":10,\"gameId\":1646496182,\"date\":1417074121019},{\"kills\":23,\"deaths\":10,\"opposingTeamKills\":10,\"assists\":29,\"gameMode\":\"CLASSIC\",\"opposingTeamName\":\"SkypeMeCutie\",\"win\":true,\"invalid\":false,\"mapId\":10,\"gameId\":1646428997,\"date\":1417072068508}],\"createDate\":1417070280000,\"modifyDate\":1418528783000,\"lastJoinDate\":1418171394000,\"secondLastJoinDate\":1418167162000,\"thirdLastJoinDate\":1418167009000,\"lastGameDate\":1417078767000,\"lastJoinedRankedTeamQueueDate\":1417078785000}]}"
    RiotApi.teamBySummonerIds(List(46311124)).map(_ should contain key 46311124)
  }

  "RiotApi.teamBySummonerId" should "be empty given an empty json" in new MockScope {
    def json: String = "{}"
    RiotApi.teamBySummonerId(0) match {
      case Good(xs) => xs shouldBe empty
      case Bad(_) => fail("Excepted to be Good")
    }
  }

  "RiotApi.teamByTeamIds" should "return a map of teams whose keys correspond to the team's id" in new MockScope {
    def json: String = "{\"TEAM-ee6a1f40-75ff-11e4-8450-c81f66ddabda\":{\"fullId\":\"TEAM-ee6a1f40-75ff-11e4-8450-c81f66ddabda\",\"name\":\"PeterCopter\",\"tag\":\"vavbb\",\"status\":\"RANKED\",\"teamStatDetails\":[{\"teamStatType\":\"RANKED_TEAM_5x5\",\"wins\":0,\"losses\":0,\"averageGamesPlayed\":0},{\"teamStatType\":\"RANKED_TEAM_3x3\",\"wins\":5,\"losses\":0,\"averageGamesPlayed\":0}],\"roster\":{\"ownerId\":46311124,\"memberList\":[{\"playerId\":46311124,\"joinDate\":1418167009000,\"inviteDate\":1418167000000,\"status\":\"MEMBER\"},{\"playerId\":40288315,\"joinDate\":1418171394000,\"inviteDate\":1418167095000,\"status\":\"MEMBER\"}]},\"matchHistory\":[{\"kills\":27,\"deaths\":18,\"opposingTeamKills\":17,\"assists\":30,\"gameMode\":\"CLASSIC\",\"opposingTeamName\":\"Lorena takes it deep\",\"win\":true,\"invalid\":false,\"mapId\":10,\"gameId\":1646535615,\"date\":1417078767758},{\"kills\":13,\"deaths\":6,\"opposingTeamKills\":6,\"assists\":22,\"gameMode\":\"CLASSIC\",\"opposingTeamName\":\"Game Design\",\"win\":true,\"invalid\":false,\"mapId\":10,\"gameId\":1646516633,\"date\":1417077187809},{\"kills\":13,\"deaths\":8,\"opposingTeamKills\":8,\"assists\":20,\"gameMode\":\"CLASSIC\",\"opposingTeamName\":\"The Core Three\",\"win\":true,\"invalid\":false,\"mapId\":10,\"gameId\":1646522671,\"date\":1417075665425},{\"kills\":19,\"deaths\":14,\"opposingTeamKills\":14,\"assists\":21,\"gameMode\":\"CLASSIC\",\"opposingTeamName\":\"The Kawaii Squad\",\"win\":true,\"invalid\":false,\"mapId\":10,\"gameId\":1646496182,\"date\":1417074121019},{\"kills\":23,\"deaths\":10,\"opposingTeamKills\":10,\"assists\":29,\"gameMode\":\"CLASSIC\",\"opposingTeamName\":\"SkypeMeCutie\",\"win\":true,\"invalid\":false,\"mapId\":10,\"gameId\":1646428997,\"date\":1417072068508}],\"createDate\":1417070280000,\"modifyDate\":1418528783000,\"lastJoinDate\":1418171394000,\"secondLastJoinDate\":1418167162000,\"thirdLastJoinDate\":1418167009000,\"lastGameDate\":1417078767000,\"lastJoinedRankedTeamQueueDate\":1417078785000}}"
    RiotApi.teamByTeamIds(List("TEAM-ee6a1f40-75ff-11e4-8450-c81f66ddabda")).map(_ should contain key "TEAM-ee6a1f40-75ff-11e4-8450-c81f66ddabda")
  }

  "RiotApi.teamBySummonerId" should "be a DataNotFound given an empty json" in new MockScope {
    def json: String = "{}"
    RiotApi.teamByTeamId("1") match {
      case Good(_)  => fail("Excepted to be Bad")
      case Bad(err) => err shouldBe DataNotFound
    }
  }
}
