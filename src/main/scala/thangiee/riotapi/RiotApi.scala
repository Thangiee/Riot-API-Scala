package thangiee.riotapi

import play.api.libs.json._
import thangiee.riotapi.game.RecentGames
import thangiee.riotapi.league.League
import thangiee.riotapi.static_data.{Champion, SummonerSpell}
import thangiee.riotapi.stats.{PlayerStatsSummaryList, RankedStats}
import thangiee.riotapi.summoner.{MasteryPages, RunePages, Summoner}
import thangiee.riotapi.team.Team
import thangiee.riotapi.utils._

object RiotApi {
  private implicit var key_ = ApiKey("")
  private var reg_ = "na"
  val gameVer = "v1.3"
  val summVer = "v1.4"
  val teamVer = "v2.4"
  val staticDataVer = "v1.2"
  val statsVer = "v1.3"
  val leagueVer = "v2.5"

  def region(region: String) = reg_ = region.toLowerCase

  def baseUrl(region: String = reg_) = s"https://$region.api.pvp.net/api/lol/$region"

  def key(key: String) = key_ = ApiKey(key)

  // =====================
  //    Games api calls
  // =====================

  def recentGamesById(id: Long, reg: String = reg_)(implicit caller: ApiCaller): Either[RiotException, RecentGames] = {
    implicit val url = s"${baseUrl(reg)}/$gameVer/game/by-summoner/$id/recent?api_key="
    jsonTo[RecentGames]
  }

  // =====================
  //    league api calls
  // =====================

  def leagueEntryByIds(ids: List[Long], reg: String = reg_)(implicit caller: ApiCaller): Either[RiotException, Map[Long, List[League]]] = {
    implicit val url = s"${baseUrl(reg)}/$leagueVer/league/by-summoner/${ids.mkString(",")}/entry?api_key="
    jsonToMap[Long, List[League]](ids)
  }

  def leagueEntryById(id: Long, reg: String = reg_)(implicit caller: ApiCaller): Either[RiotException, List[League]] = {
    leagueEntryByIds(List(id)).right.map(_.getOrElse(id, Nil))
  }

  // =====================
  //    Stats api calls
  // =====================

  def rankedStatsById(id: Long, season: Int, reg: String = reg_)(implicit caller: ApiCaller): Either[RiotException, RankedStats] = {
    implicit val url = s"${baseUrl(reg)}/$statsVer/stats/by-summoner/$id/ranked?season=SEASON$season&api_key="
    jsonTo[RankedStats]
  }

  def summaryStatsById(id: Long, season: Int, reg: String = reg_)(implicit caller: ApiCaller): Either[RiotException, PlayerStatsSummaryList] = {
    implicit val url = s"${baseUrl(reg)}/$statsVer/stats/by-summoner/$id/summary?season=SEASON$season&api_key="
    jsonTo[PlayerStatsSummaryList]
  }

  // =====================
  //  Summoner api calls
  // =====================

  def summonerByNames(names: List[String], reg: String = reg_)(implicit caller: ApiCaller): Either[RiotException, Map[String, Summoner]] = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/by-name/${names.mkString(",")}?api_key="
    jsonToMap[String, Summoner](names)
  }

  def summonerByName(name: String, reg: String = reg_)(implicit caller: ApiCaller): Either[RiotException, Summoner] = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/by-name/$name?api_key="
    jsonTo[String, Summoner](name)
  }

  def summonerByIds(ids: List[Long], reg: String = reg_)(implicit caller: ApiCaller): Either[RiotException, Map[Long, Summoner]] = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/${ids.mkString(",")}?api_key="
    jsonToMap[Long, Summoner](ids)
  }

  def summonerById(id: Long, reg: String = reg_)(implicit caller: ApiCaller): Either[RiotException, Summoner] = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/$id?api_key="
    jsonTo[Long, Summoner](id)
  }

  def masteryPagesByIds(ids: List[Long], reg: String = reg_)(implicit caller: ApiCaller): Either[RiotException, Map[Long, MasteryPages]] = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/${ids.mkString(",")}/masteries?api_key="
    jsonToMap[Long, MasteryPages](ids)
  }

  def summonerNameById(id: Long, reg: String = reg_)(implicit caller: ApiCaller): Either[RiotException, String] = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/$id/name?api_key="
    jsonTo[Long, String](id)
  }

  def runePagesByIds(ids: List[Long], reg: String = reg_)(implicit caller: ApiCaller): Either[RiotException, Map[Long, RunePages]] = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/${ids.mkString(",")}/runes?api_key="
    jsonToMap[Long, RunePages](ids)
  }

  // =====================
  //    Team api calls
  // =====================

  def teamBySummonerIds(ids: List[Long], reg: String = reg_)(implicit caller: ApiCaller): Either[RiotException, Map[Long, List[Team]]] = {
    implicit val url = s"${baseUrl(reg)}/$teamVer/team/by-summoner/${ids.mkString(",")}?api_key="
    jsonToMap[Long, List[Team]](ids)
  }

  def teamByTeamIds(ids: List[String], reg: String = reg_)(implicit caller: ApiCaller): Either[RiotException, Map[String, Team]] = {
    implicit val url = s"${baseUrl(reg)}/$teamVer/team/${ids.mkString(",")}?api_key="
    jsonToMap[String, Team](ids)
  }

  // =====================
  // Static-data api calls
  // =====================

  def champStaticDataById(id: Int)(implicit caller: ApiCaller): Either[RiotException, Champion] = {
    implicit val url = s"https://na.api.pvp.net/api/lol/static-data/na/v1.2/champion/$id?&api_key="
    jsonTo[Champion]
  }

  def spellStaticDataById(id: Int)(implicit caller: ApiCaller): Either[RiotException, SummonerSpell] = {
    implicit val url = s"https://na.api.pvp.net/api/lol/static-data/na/$staticDataVer/summoner-spell/$id?api_key="
    jsonTo[SummonerSpell]
  }

  private def jsonToMap[A, B: Reads](keys: List[A])(implicit caller: ApiCaller, url: String): Either[RiotException, Map[A, B]] = {
    def asMap(jsonResponse: String) = {
      (for {
        key ← keys
        value ← (Json.parse(jsonResponse) \ key.toString).asOpt[B]
      } yield key → value).toMap
    }

    caller.call(url).right.map(asMap)
  }

  private def jsonTo[A, B: Reads](key: A)(implicit caller: ApiCaller, url: String): Either[RiotException, B] = {
    caller.call(url).right.map(json => { println(json); (Json.parse(json) \ key.toString).as[B] })
  }

  private def jsonTo[A: Reads]()(implicit caller: ApiCaller, url: String): Either[RiotException, A] = {
    caller.call(url).right.map(json => Json.parse(json).as[A])
  }
}

