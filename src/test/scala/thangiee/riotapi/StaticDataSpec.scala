package thangiee.riotapi

import thangiee.riotapi.BaseSpec
import thangiee.riotapi.staticdata.{Champion, SummonerSpell}

class StaticDataSpec extends BaseSpec {

  "RiotApi.champStaticDataById" should "parse the json successfully" in new MockScope {
    def json: String = "{\"id\":1,\"key\":\"Annie\",\"name\":\"Annie\",\"title\":\"the Dark Child\"}"

    RiotApi.champStaticDataById(1).get shouldBe a [Champion]
  }

  "RiotApi.spellStaticDataById" should "parse the json successfully" in new MockScope {
    def json: String = "{\"name\":\"Cleanse\",\"description\":\"Removes all disables and summoner spell debuffs affecting your champion and lowers the duration of incoming disables by 65% for 3 seconds.\",\"summonerLevel\":6,\"id\":1,\"key\":\"SummonerBoost\"}"

    RiotApi.spellStaticDataById(1).get shouldBe a[SummonerSpell]
  }
}
