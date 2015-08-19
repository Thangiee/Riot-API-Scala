package thangiee.riotapi

import thangiee.riotapi.BaseSpec

class RiotApiSpec extends BaseSpec {

  "The base URL string" should "match the correct region" in {
    RiotApi.baseUrl("euw") shouldBe "https://euw.api.pvp.net/api/lol/euw"

    RiotApi.regionId = "na"
    RiotApi.baseUrl() shouldBe s"https://${RiotApi.regionId}.api.pvp.net/api/lol/${RiotApi.regionId}"
  }

  "Parsing json" should "replace missing json field with default value" in new MockScope {
    // removed page's name field
    def json: String = "{\"58229565\":{\"summonerId\":58229565,\"pages\":[{\"id\":52480578,\"current\":true}]}}"

    RiotApi.masteryPagesByIds(List(58229565)).get.head match {
      case (_, masteryPages) =>
        masteryPages.pages.head.name shouldEqual ""
    }
  }

}
