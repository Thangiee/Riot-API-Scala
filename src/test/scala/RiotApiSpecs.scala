import org.specs2.mock._
import org.specs2.mutable._
import org.specs2.specification.Scope
import thangiee.riotapi.core.{ApiKey, ApiCaller, RiotApi}

class RiotApiSpecs extends Specification with Mockito {

  "The base URL string" should {
    "match https://euw.api.pvp.net/api/lol/euw" in {
      RiotApi.baseUrl("euw") must equalTo("https://euw.api.pvp.net/api/lol/euw")
    }

    "match https://br.api.pvp.net/api/lol/br" in {
      RiotApi.baseUrl() must equalTo("https://na.api.pvp.net/api/lol/na")
    }
  }

  "Calling summonerByNames" should {

    "return an empty map if the json is empty" in new CustomScope {
      override def json: String = "{}"
      val summoners = RiotApi.summonerByNames(List("lolwithfriends")).right.get
      summoners.size must_== 0
    }

    "return a map of sizes 1 when given 1 name" in new CustomScope {
      def json = "{\"lolwithfriends\":{\"id\":58229565,\"name\":\"lolwithfriends\",\"profileIconId\":24,\"summonerLevel\":1,\"revisionDate\":1406570337000}}"
      val summoners = RiotApi.summonerByNames(List("lolwithfriends")).right.get
      summoners.size must_== 1
    }

    "return a map of sizes 2 when given 2 names" in new CustomScope {
      def json = "{\"lolwithfriends\":{\"id\":58229565,\"name\":\"lolwithfriends\",\"profileIconId\":24,\"summonerLevel\":1,\"revisionDate\":1406570337000},\"thangiee\":{\"id\":25011185,\"name\":\"Thangiee\",\"profileIconId\":673,\"summonerLevel\":30,\"revisionDate\":1413805785000}}"
      val summoners = RiotApi.summonerByNames(List("thangiee", "lolwithfriends")).right.get
      summoners.size must_== 2
    }

    "return a map of sizes 1 when one of the two summoners can not be found" in new CustomScope {
      def json = "{\"lolwithfriends\":{\"id\":58229565,\"name\":\"lolwithfriends\",\"profileIconId\":24,\"summonerLevel\":1,\"revisionDate\":1406570337000}}"
      val summoners = RiotApi.summonerByNames(List("lolwithfriends", "zzwrongName")).right.get
      summoners.size must_== 1
    }

    "return a map of summoners whose keys correspond to the summoner's name" in new CustomScope {
      def json = "{\"lolwithfriends\":{\"id\":58229565,\"name\":\"lolwithfriends\",\"profileIconId\":24,\"summonerLevel\":1,\"revisionDate\":1406570337000},\"thangiee\":{\"id\":25011185,\"name\":\"Thangiee\",\"profileIconId\":673,\"summonerLevel\":30,\"revisionDate\":1413805785000}}"
      val summoners = RiotApi.summonerByNames(List("thangiee", "lolwithfriends")).right.get
      summoners.get("thangiee").get.name.toLowerCase must_== "thangiee"
      summoners.get("lolwithfriends").get.name.toLowerCase must_== "lolwithfriends"
    }
  }

  "Calling summonerByIds" should {
    "return a map of summoners whose keys correspond to the summoner's id" in new CustomScope {
      def json = "{\"19291008\":{\"id\":19291008,\"name\":\"TOM\",\"profileIconId\":642,\"summonerLevel\":30,\"revisionDate\":1398435976000}}"
      val summoners = RiotApi.summonerByIds(List(19291008), "euw").right.get
      summoners.get(19291008).get.id must_== 19291008
    }

  }

  "Calling masteryPagesByIds" should {
    "return a map of mastery pages whose keys correspond to the summoner's id" in new CustomScope {
      def json: String = "{\"25011185\":{\"summonerId\":25011185,\"pages\":[{\"id\":38939900,\"name\":\"kayle mid\",\"current\":true,\"masteries\":[{\"id\":4121,\"rank\":1},{\"id\":4134,\"rank\":3},{\"id\":4154,\"rank\":1},{\"id\":4124,\"rank\":1},{\"id\":4114,\"rank\":1},{\"id\":4151,\"rank\":1},{\"id\":4112,\"rank\":4},{\"id\":4113,\"rank\":4},{\"id\":4144,\"rank\":1},{\"id\":4152,\"rank\":3},{\"id\":4123,\"rank\":3},{\"id\":4141,\"rank\":1},{\"id\":4133,\"rank\":1},{\"id\":4143,\"rank\":3},{\"id\":4162,\"rank\":1},{\"id\":4131,\"rank\":1}]},{\"id\":38939901,\"name\":\"kayle jungle\",\"current\":false,\"masteries\":[{\"id\":4121,\"rank\":1},{\"id\":4134,\"rank\":3},{\"id\":4124,\"rank\":1},{\"id\":4114,\"rank\":1},{\"id\":4112,\"rank\":4},{\"id\":4313,\"rank\":1},{\"id\":4152,\"rank\":3},{\"id\":4122,\"rank\":3},{\"id\":4141,\"rank\":1},{\"id\":4123,\"rank\":3},{\"id\":4142,\"rank\":1},{\"id\":4312,\"rank\":3},{\"id\":4111,\"rank\":1},{\"id\":4132,\"rank\":1},{\"id\":4133,\"rank\":1},{\"id\":4162,\"rank\":1},{\"id\":4131,\"rank\":1}]},{\"id\":38939902,\"name\":\"lux \",\"current\":false,\"masteries\":[{\"id\":4134,\"rank\":3},{\"id\":4322,\"rank\":3},{\"id\":4332,\"rank\":1},{\"id\":4154,\"rank\":1},{\"id\":4113,\"rank\":4},{\"id\":4144,\"rank\":1},{\"id\":4324,\"rank\":1},{\"id\":4312,\"rank\":1},{\"id\":4152,\"rank\":3},{\"id\":4123,\"rank\":3},{\"id\":4313,\"rank\":3},{\"id\":4111,\"rank\":1},{\"id\":4133,\"rank\":1},{\"id\":4143,\"rank\":3},{\"id\":4162,\"rank\":1}]},{\"id\":38939903,\"name\":\"lux support\",\"current\":false,\"masteries\":[{\"id\":4353,\"rank\":3},{\"id\":4121,\"rank\":1},{\"id\":4362,\"rank\":1},{\"id\":4322,\"rank\":3},{\"id\":4334,\"rank\":1},{\"id\":4344,\"rank\":2},{\"id\":4352,\"rank\":1},{\"id\":4113,\"rank\":4},{\"id\":4331,\"rank\":3},{\"id\":4312,\"rank\":1},{\"id\":4324,\"rank\":1},{\"id\":4123,\"rank\":3},{\"id\":4313,\"rank\":3},{\"id\":4133,\"rank\":1},{\"id\":4342,\"rank\":1},{\"id\":4341,\"rank\":1}]},{\"id\":38939904,\"name\":\"braum\",\"current\":false,\"masteries\":[{\"id\":4233,\"rank\":3},{\"id\":4211,\"rank\":2},{\"id\":4243,\"rank\":1},{\"id\":4213,\"rank\":2},{\"id\":4244,\"rank\":1},{\"id\":4234,\"rank\":3},{\"id\":4322,\"rank\":1},{\"id\":4252,\"rank\":2},{\"id\":4222,\"rank\":3},{\"id\":4221,\"rank\":1},{\"id\":4313,\"rank\":1},{\"id\":4262,\"rank\":1},{\"id\":4312,\"rank\":3},{\"id\":4241,\"rank\":3},{\"id\":4232,\"rank\":1}]},{\"id\":38939905,\"name\":\"ashe\",\"current\":false,\"masteries\":[{\"id\":4212,\"rank\":2},{\"id\":4211,\"rank\":2},{\"id\":4134,\"rank\":3},{\"id\":4124,\"rank\":1},{\"id\":4114,\"rank\":1},{\"id\":4222,\"rank\":3},{\"id\":4112,\"rank\":4},{\"id\":4144,\"rank\":1},{\"id\":4221,\"rank\":1},{\"id\":4122,\"rank\":3},{\"id\":4152,\"rank\":3},{\"id\":4132,\"rank\":1},{\"id\":4142,\"rank\":3},{\"id\":4232,\"rank\":1},{\"id\":4162,\"rank\":1}]},{\"id\":38939906,\"name\":\"vi\",\"current\":false,\"masteries\":[{\"id\":4211,\"rank\":2},{\"id\":4214,\"rank\":2},{\"id\":4121,\"rank\":1},{\"id\":4134,\"rank\":3},{\"id\":4114,\"rank\":1},{\"id\":4222,\"rank\":3},{\"id\":4221,\"rank\":1},{\"id\":4144,\"rank\":1},{\"id\":4122,\"rank\":3},{\"id\":4152,\"rank\":3},{\"id\":4112,\"rank\":2},{\"id\":4141,\"rank\":1},{\"id\":4113,\"rank\":2},{\"id\":4111,\"rank\":1},{\"id\":4132,\"rank\":1},{\"id\":4232,\"rank\":1},{\"id\":4162,\"rank\":1},{\"id\":4131,\"rank\":1}]},{\"id\":38939907,\"name\":\"yasuo mid\",\"current\":false,\"masteries\":[{\"id\":4212,\"rank\":2},{\"id\":4211,\"rank\":2},{\"id\":4134,\"rank\":3},{\"id\":4151,\"rank\":1},{\"id\":4222,\"rank\":3},{\"id\":4112,\"rank\":4},{\"id\":4221,\"rank\":1},{\"id\":4122,\"rank\":3},{\"id\":4152,\"rank\":3},{\"id\":4141,\"rank\":1},{\"id\":4111,\"rank\":1},{\"id\":4132,\"rank\":1},{\"id\":4232,\"rank\":1},{\"id\":4162,\"rank\":1},{\"id\":4142,\"rank\":2},{\"id\":4131,\"rank\":1}]},{\"id\":38939908,\"name\":\"yasuo top\",\"current\":false,\"masteries\":[{\"id\":4212,\"rank\":2},{\"id\":4211,\"rank\":2},{\"id\":4134,\"rank\":3},{\"id\":4114,\"rank\":1},{\"id\":4151,\"rank\":1},{\"id\":4112,\"rank\":4},{\"id\":4222,\"rank\":3},{\"id\":4221,\"rank\":1},{\"id\":4122,\"rank\":3},{\"id\":4152,\"rank\":3},{\"id\":4111,\"rank\":1},{\"id\":4132,\"rank\":1},{\"id\":4142,\"rank\":3},{\"id\":4232,\"rank\":1},{\"id\":4162,\"rank\":1}]},{\"id\":38939909,\"name\":\"yasuo new\",\"current\":false,\"masteries\":[{\"id\":4212,\"rank\":2},{\"id\":4211,\"rank\":2},{\"id\":4134,\"rank\":3},{\"id\":4124,\"rank\":1},{\"id\":4114,\"rank\":1},{\"id\":4151,\"rank\":1},{\"id\":4222,\"rank\":3},{\"id\":4221,\"rank\":1},{\"id\":4144,\"rank\":1},{\"id\":4122,\"rank\":3},{\"id\":4152,\"rank\":3},{\"id\":4112,\"rank\":2},{\"id\":4141,\"rank\":1},{\"id\":4142,\"rank\":1},{\"id\":4111,\"rank\":1},{\"id\":4132,\"rank\":1},{\"id\":4232,\"rank\":1},{\"id\":4162,\"rank\":1},{\"id\":4131,\"rank\":1}]},{\"id\":38939910,\"name\":\"Mastery Page 11\",\"current\":false,\"masteries\":[{\"id\":4212,\"rank\":2},{\"id\":4353,\"rank\":3},{\"id\":4211,\"rank\":2},{\"id\":4362,\"rank\":1},{\"id\":4322,\"rank\":3},{\"id\":4334,\"rank\":1},{\"id\":4352,\"rank\":1},{\"id\":4222,\"rank\":3},{\"id\":4221,\"rank\":1},{\"id\":4331,\"rank\":3},{\"id\":4324,\"rank\":1},{\"id\":4312,\"rank\":3},{\"id\":4313,\"rank\":3},{\"id\":4232,\"rank\":1},{\"id\":4342,\"rank\":1},{\"id\":4341,\"rank\":1}]}]}}"
      val masteryPages = RiotApi.masteryPagesByIds(List(25011185)).right.get
      masteryPages.get(25011185).isEmpty must_== false
    }
  }

  "Calling runePagesByIds" should {
    "return a map of rune pages whose keys correspond to the summoner's id" in new CustomScope {
      def json: String = "{\"25011185\":{\"summonerId\":25011185,\"pages\":[{\"id\":16683635,\"name\":\"Rune Page 1\",\"current\":true,\"slots\":[{\"runeSlotId\":1,\"runeId\":5273},{\"runeSlotId\":2,\"runeId\":5273},{\"runeSlotId\":3,\"runeId\":5273},{\"runeSlotId\":4,\"runeId\":5247},{\"runeSlotId\":5,\"runeId\":5247},{\"runeSlotId\":6,\"runeId\":5247},{\"runeSlotId\":7,\"runeId\":5247},{\"runeSlotId\":8,\"runeId\":5247},{\"runeSlotId\":9,\"runeId\":5247},{\"runeSlotId\":10,\"runeId\":5317},{\"runeSlotId\":11,\"runeId\":5317},{\"runeSlotId\":12,\"runeId\":5317},{\"runeSlotId\":13,\"runeId\":5317},{\"runeSlotId\":14,\"runeId\":5317},{\"runeSlotId\":15,\"runeId\":5317},{\"runeSlotId\":16,\"runeId\":5317},{\"runeSlotId\":17,\"runeId\":5317},{\"runeSlotId\":18,\"runeId\":5317},{\"runeSlotId\":19,\"runeId\":5289},{\"runeSlotId\":20,\"runeId\":5289},{\"runeSlotId\":21,\"runeId\":5289},{\"runeSlotId\":22,\"runeId\":5289},{\"runeSlotId\":23,\"runeId\":5289},{\"runeSlotId\":24,\"runeId\":5289},{\"runeSlotId\":25,\"runeId\":5289},{\"runeSlotId\":26,\"runeId\":5289},{\"runeSlotId\":27,\"runeId\":5289},{\"runeSlotId\":28,\"runeId\":5357},{\"runeSlotId\":29,\"runeId\":5337},{\"runeSlotId\":30,\"runeId\":5337}]},{\"id\":16683636,\"name\":\"Rune Page 2\",\"current\":false,\"slots\":[{\"runeSlotId\":1,\"runeId\":5251},{\"runeSlotId\":2,\"runeId\":5251},{\"runeSlotId\":3,\"runeId\":5251},{\"runeSlotId\":4,\"runeId\":5245},{\"runeSlotId\":5,\"runeId\":5251},{\"runeSlotId\":6,\"runeId\":5251},{\"runeSlotId\":7,\"runeId\":5245},{\"runeSlotId\":8,\"runeId\":5245},{\"runeSlotId\":9,\"runeId\":5245},{\"runeSlotId\":10,\"runeId\":5316},{\"runeSlotId\":11,\"runeId\":5311},{\"runeSlotId\":12,\"runeId\":5317},{\"runeSlotId\":13,\"runeId\":5317},{\"runeSlotId\":14,\"runeId\":5317},{\"runeSlotId\":15,\"runeId\":5317},{\"runeSlotId\":16,\"runeId\":5317},{\"runeSlotId\":17,\"runeId\":5317},{\"runeSlotId\":18,\"runeId\":5317},{\"runeSlotId\":19,\"runeId\":5289},{\"runeSlotId\":20,\"runeId\":5289},{\"runeSlotId\":21,\"runeId\":5289},{\"runeSlotId\":22,\"runeId\":5289},{\"runeSlotId\":23,\"runeId\":5289},{\"runeSlotId\":24,\"runeId\":5289},{\"runeSlotId\":25,\"runeId\":5289},{\"runeSlotId\":26,\"runeId\":5289},{\"runeSlotId\":27,\"runeId\":5289},{\"runeSlotId\":28,\"runeId\":5335},{\"runeSlotId\":29,\"runeId\":5335},{\"runeSlotId\":30,\"runeId\":5335}]}]}}"
      val runePages = RiotApi.runePagesByIds(List(25011185)).right.get
      runePages.get(25011185).isEmpty must_== false
    }
  }

  "Calling summonerNameById" should {
    "return the name of the summoner with the corresponding id" in new CustomScope {
      def json: String = "{\"25011185\":\"Thangiee\"}"
      RiotApi.summonerNameById(25011185).right.get must_== "Thangiee"
    }
  }

  "Calling teamBySummonerIds" should {
    "return a map of teams whose keys correspond to the summoner's id" in new CustomScope {
      def json: String = "{\"46311124\":[{\"fullId\":\"TEAM-ee6a1f40-75ff-11e4-8450-c81f66ddabda\",\"name\":\"PeterCopter\",\"tag\":\"vavbb\",\"status\":\"RANKED\",\"teamStatDetails\":[{\"teamStatType\":\"RANKED_TEAM_5x5\",\"wins\":0,\"losses\":0,\"averageGamesPlayed\":0},{\"teamStatType\":\"RANKED_TEAM_3x3\",\"wins\":5,\"losses\":0,\"averageGamesPlayed\":0}],\"roster\":{\"ownerId\":46311124,\"memberList\":[{\"playerId\":46311124,\"joinDate\":1418167009000,\"inviteDate\":1418167000000,\"status\":\"MEMBER\"},{\"playerId\":40288315,\"joinDate\":1418171394000,\"inviteDate\":1418167095000,\"status\":\"MEMBER\"}]},\"matchHistory\":[{\"kills\":27,\"deaths\":18,\"opposingTeamKills\":17,\"assists\":30,\"gameMode\":\"CLASSIC\",\"opposingTeamName\":\"Lorena takes it deep\",\"win\":true,\"invalid\":false,\"mapId\":10,\"gameId\":1646535615,\"date\":1417078767758},{\"kills\":13,\"deaths\":6,\"opposingTeamKills\":6,\"assists\":22,\"gameMode\":\"CLASSIC\",\"opposingTeamName\":\"Game Design\",\"win\":true,\"invalid\":false,\"mapId\":10,\"gameId\":1646516633,\"date\":1417077187809},{\"kills\":13,\"deaths\":8,\"opposingTeamKills\":8,\"assists\":20,\"gameMode\":\"CLASSIC\",\"opposingTeamName\":\"The Core Three\",\"win\":true,\"invalid\":false,\"mapId\":10,\"gameId\":1646522671,\"date\":1417075665425},{\"kills\":19,\"deaths\":14,\"opposingTeamKills\":14,\"assists\":21,\"gameMode\":\"CLASSIC\",\"opposingTeamName\":\"The Kawaii Squad\",\"win\":true,\"invalid\":false,\"mapId\":10,\"gameId\":1646496182,\"date\":1417074121019},{\"kills\":23,\"deaths\":10,\"opposingTeamKills\":10,\"assists\":29,\"gameMode\":\"CLASSIC\",\"opposingTeamName\":\"SkypeMeCutie\",\"win\":true,\"invalid\":false,\"mapId\":10,\"gameId\":1646428997,\"date\":1417072068508}],\"createDate\":1417070280000,\"modifyDate\":1418528783000,\"lastJoinDate\":1418171394000,\"secondLastJoinDate\":1418167162000,\"thirdLastJoinDate\":1418167009000,\"lastGameDate\":1417078767000,\"lastJoinedRankedTeamQueueDate\":1417078785000}]}"
      val teams = RiotApi.teamBySummonerIds(List(46311124))
      teams.right.get.get(46311124).isDefined must beTrue
    }
  }

  "Calling teamByTeamIds" should {
    "return a map of teams whose keys correspond to the team's id" in new CustomScope {
      def json: String = "{\"TEAM-ee6a1f40-75ff-11e4-8450-c81f66ddabda\":{\"fullId\":\"TEAM-ee6a1f40-75ff-11e4-8450-c81f66ddabda\",\"name\":\"PeterCopter\",\"tag\":\"vavbb\",\"status\":\"RANKED\",\"teamStatDetails\":[{\"teamStatType\":\"RANKED_TEAM_5x5\",\"wins\":0,\"losses\":0,\"averageGamesPlayed\":0},{\"teamStatType\":\"RANKED_TEAM_3x3\",\"wins\":5,\"losses\":0,\"averageGamesPlayed\":0}],\"roster\":{\"ownerId\":46311124,\"memberList\":[{\"playerId\":46311124,\"joinDate\":1418167009000,\"inviteDate\":1418167000000,\"status\":\"MEMBER\"},{\"playerId\":40288315,\"joinDate\":1418171394000,\"inviteDate\":1418167095000,\"status\":\"MEMBER\"}]},\"matchHistory\":[{\"kills\":27,\"deaths\":18,\"opposingTeamKills\":17,\"assists\":30,\"gameMode\":\"CLASSIC\",\"opposingTeamName\":\"Lorena takes it deep\",\"win\":true,\"invalid\":false,\"mapId\":10,\"gameId\":1646535615,\"date\":1417078767758},{\"kills\":13,\"deaths\":6,\"opposingTeamKills\":6,\"assists\":22,\"gameMode\":\"CLASSIC\",\"opposingTeamName\":\"Game Design\",\"win\":true,\"invalid\":false,\"mapId\":10,\"gameId\":1646516633,\"date\":1417077187809},{\"kills\":13,\"deaths\":8,\"opposingTeamKills\":8,\"assists\":20,\"gameMode\":\"CLASSIC\",\"opposingTeamName\":\"The Core Three\",\"win\":true,\"invalid\":false,\"mapId\":10,\"gameId\":1646522671,\"date\":1417075665425},{\"kills\":19,\"deaths\":14,\"opposingTeamKills\":14,\"assists\":21,\"gameMode\":\"CLASSIC\",\"opposingTeamName\":\"The Kawaii Squad\",\"win\":true,\"invalid\":false,\"mapId\":10,\"gameId\":1646496182,\"date\":1417074121019},{\"kills\":23,\"deaths\":10,\"opposingTeamKills\":10,\"assists\":29,\"gameMode\":\"CLASSIC\",\"opposingTeamName\":\"SkypeMeCutie\",\"win\":true,\"invalid\":false,\"mapId\":10,\"gameId\":1646428997,\"date\":1417072068508}],\"createDate\":1417070280000,\"modifyDate\":1418528783000,\"lastJoinDate\":1418171394000,\"secondLastJoinDate\":1418167162000,\"thirdLastJoinDate\":1418167009000,\"lastGameDate\":1417078767000,\"lastJoinedRankedTeamQueueDate\":1417078785000}}"
      val teams = RiotApi.teamByTeamIds(List("TEAM-ee6a1f40-75ff-11e4-8450-c81f66ddabda"))
      teams.right.get.get("TEAM-ee6a1f40-75ff-11e4-8450-c81f66ddabda").isDefined must beTrue
    }
  }

  abstract class CustomScope extends Scope {
    def json: String
    implicit val key = ApiKey("")
    implicit val m: ApiCaller = mock[ApiCaller]
    m.call(anyString)(any[ApiKey]) returns Right(json)
  }

}