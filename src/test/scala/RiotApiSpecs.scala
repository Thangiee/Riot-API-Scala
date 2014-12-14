import org.specs2.mock._
import org.specs2.mutable._
import org.specs2.specification.Scope
import thangiee.riotapi.RiotApi
import thangiee.riotapi.utils.ApiCaller

class RiotApiSpecs extends Specification with Mockito {

  "The base URL string" should {
    "match https://euw.api.pvp.net/api/lol/euw" in {
      RiotApi.baseUrl("euw") must equalTo("https://euw.api.pvp.net/api/lol/euw")
    }

    "match https://br.api.pvp.net/api/lol/br" in {
      RiotApi.region("br")
      RiotApi.baseUrl() must equalTo("https://br.api.pvp.net/api/lol/br")
    }
  }

  "Calling summonerByNames" should {

    "return an empty map if the json is empty" in new SummonerScope {
      override def json: String = "{}"
      val summoners = RiotApi.summonerByNames(List("lolwithfriends"))
      summoners.size must_== 0
    }

    "return a map of sizes 1 when given 1 name" in new SummonerScope {
      def json = "{\"lolwithfriends\":{\"id\":58229565,\"name\":\"lolwithfriends\",\"profileIconId\":24,\"summonerLevel\":1,\"revisionDate\":1406570337000}}"
      val summoners = RiotApi.summonerByNames(List("lolwithfriends"))
      summoners.size must_== 1
    }

    "return a map of sizes 2 when given 2 names" in new SummonerScope {
      def json = "{\"lolwithfriends\":{\"id\":58229565,\"name\":\"lolwithfriends\",\"profileIconId\":24,\"summonerLevel\":1,\"revisionDate\":1406570337000},\"thangiee\":{\"id\":25011185,\"name\":\"Thangiee\",\"profileIconId\":673,\"summonerLevel\":30,\"revisionDate\":1413805785000}}"
      val summoners = RiotApi.summonerByNames(List("thangiee", "lolwithfriends"))
      summoners.size must_== 2
    }

    "return a map of sizes 1 when one of the two summoners can not be found" in new SummonerScope {
      def json = "{\"lolwithfriends\":{\"id\":58229565,\"name\":\"lolwithfriends\",\"profileIconId\":24,\"summonerLevel\":1,\"revisionDate\":1406570337000}}"
      val summoners = RiotApi.summonerByNames(List("lolwithfriends", "zzwrongName"))
      summoners.size must_== 1
    }

    "return a map of summoners whose keys correspond to the summoner's name" in new SummonerScope {
      def json = "{\"lolwithfriends\":{\"id\":58229565,\"name\":\"lolwithfriends\",\"profileIconId\":24,\"summonerLevel\":1,\"revisionDate\":1406570337000},\"thangiee\":{\"id\":25011185,\"name\":\"Thangiee\",\"profileIconId\":673,\"summonerLevel\":30,\"revisionDate\":1413805785000}}"
      val summoners = RiotApi.summonerByNames(List("thangiee", "lolwithfriends"))
      summoners.get("thangiee").get.name.toLowerCase must_== "thangiee"
      summoners.get("lolwithfriends").get.name.toLowerCase must_== "lolwithfriends"
    }
  }

  "Calling summonerByIds" should {
    "return a map of summoners whose keys correspond to the summoner's id" in new SummonerScope {
      def json = "{\"19291008\":{\"id\":19291008,\"name\":\"TOM\",\"profileIconId\":642,\"summonerLevel\":30,\"revisionDate\":1398435976000}}"
      val summoners = RiotApi.summonerByIds(List(19291008), "euw")
      summoners.get(19291008).get.id must_== 19291008
    }
  }

  abstract class SummonerScope extends Scope {
    def json: String
    protected implicit val m: ApiCaller = mock[ApiCaller]
    m.call(anyString) returns Some(json)
  }
}