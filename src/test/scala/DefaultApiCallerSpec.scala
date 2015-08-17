import thangiee.riotapi.{Unauthorized, RiotApi}

class DefaultApiCallerSpec extends BaseSpec {

  "Using an invalid api key" should "result in an Unauthorized error" in {
    RiotApi.key = "fake-key"

    RiotApi.summonerById(10).map { summ =>
      fail("Excepted Unauthorized Error")
    }.recover {
      case Unauthorized(apiKey) => apiKey shouldEqual RiotApi.key
      case _ => fail("Excepted Unauthorized Error")
    }
  }

}
