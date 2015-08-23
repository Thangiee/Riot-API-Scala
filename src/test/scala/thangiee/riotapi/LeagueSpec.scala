package thangiee.riotapi

import org.scalactic.{Bad, Good}
import thangiee.riotapi.league.League

class LeagueSpec extends BaseSpec {

  "RiotApi.leagueEntryById" should "Returns all league entries for specified summoners and summoners' teams." in new MockScope {
    def json: String = "{\"20132258\":[{\"name\":\"Riven's Cutthroats\",\"tier\":\"CHALLENGER\",\"queue\":\"RANKED_SOLO_5x5\",\"entries\":[{\"playerOrTeamId\":\"20132258\",\"playerOrTeamName\":\"Doublelift\",\"division\":\"I\",\"leaguePoints\":722,\"wins\":124,\"losses\":61,\"isHotStreak\":false,\"isVeteran\":true,\"isFreshBlood\":false,\"isInactive\":false}]},{\"name\":\"Lee Sin's Soldiers\",\"tier\":\"PLATINUM\",\"queue\":\"RANKED_TEAM_5x5\",\"entries\":[{\"playerOrTeamId\":\"TEAM-1e965160-c7a0-11e4-8e3e-c81f66ddabda\",\"playerOrTeamName\":\"Acolyte of Peng\",\"division\":\"I\",\"leaguePoints\":5,\"wins\":19,\"losses\":0,\"isHotStreak\":true,\"isVeteran\":false,\"isFreshBlood\":false,\"isInactive\":false}]}]}"
    RiotApi.leagueEntryById(20132258) match {
      case Good(leagues) =>
        leagues shouldNot be ('empty)
        leagues.head shouldBe a[League]
      case Bad(err)      =>
        fail("Excepted to be Good")
    }
  }
}
