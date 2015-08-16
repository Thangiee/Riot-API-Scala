import thangiee.riotapi._
import thangiee.riotapi.summoner.Summoner

class RiotApiSpec extends BaseSpec {

  "The base URL string" should "match the correct region" in {
    RiotApi.baseUrl("euw") shouldBe "https://euw.api.pvp.net/api/lol/euw"

    RiotApi.regionId = "na"
    RiotApi.baseUrl() shouldBe s"https://${RiotApi.regionId}.api.pvp.net/api/lol/${RiotApi.regionId}"
  }

  "Using an invalid api key" should "return a Bad(Unauthorized)" in {
    RiotApi.key = ""

    RiotApi.summonerById(0).map { summ =>
      fail("Excepted Unauthorized Error")
    }.recover {
      case Unauthorized => // success
      case _ => fail("Excepted Unauthorized Error")
    }
  }

  "Parsing json" should "replace missing json field with default value" in new MockScope {
    def json: String = "{\"thangiee\":{\"id\":25011185,\"name\":\"Thangiee\",\"summonerLevel\":30,\"revisionDate\":1435215901000}}"

    RiotApi.summonerByName("thangiee").map(_.profileIconId shouldBe Summoner().profileIconId)
  }

}
