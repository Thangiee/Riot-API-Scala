package thangiee.riotapi

import thangiee.riotapi.BaseSpec

class DefaultApiCallerSpec extends BaseSpec {

  "Using an invalid api key" should "result in an Unauthorized error" in {
    RiotApi.key = "fake-key"

    RiotApi.summonerById(0).map { summ =>
      fail("Excepted Unauthorized Error")
    }.recover {
      case Unauthorized(apiKey) => apiKey shouldEqual RiotApi.key
      case e: RiotError => fail(s"Excepted Unauthorized Error: $e")
    }
  }
}
