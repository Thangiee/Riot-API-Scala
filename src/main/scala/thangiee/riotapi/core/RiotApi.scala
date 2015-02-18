package thangiee.riotapi.core

import play.api.libs.json._
import thangiee.riotapi.currentgame.CurrentGameInfo
import thangiee.riotapi.game.RecentGames
import thangiee.riotapi.league.League
import thangiee.riotapi.static_data.{Champion, SummonerSpell}
import thangiee.riotapi.stats.{PlayerStatsSummaryList, RankedStats}
import thangiee.riotapi.summoner.{MasteryPages, RunePages, Summoner}
import thangiee.riotapi.team.Team

import scala.util.Try

object RiotApi {
  private implicit var key_ = ApiKey("")
  private          var reg_ = "na"
  val gameVer       = "v1.3"
  val summVer       = "v1.4"
  val teamVer       = "v2.4"
  val staticDataVer = "v1.2"
  val statsVer      = "v1.3"
  val leagueVer     = "v2.5"

  def regionId_=(regionId: String) = reg_ = regionId.toLowerCase

  def regionId = reg_

  def baseUrl(region: String = reg_) = s"https://${region.toLowerCase}.api.pvp.net/api/lol/${region.toLowerCase}"

  def key_=(key: String) = key_ = ApiKey(key)

  def key = key_

  // =====================
  //    Games api calls
  // =====================

  def currentGameInfoById(id: Long, reg: String = reg_)(implicit caller: ApiCaller): Try[CurrentGameInfo] = {
    val platformId = reg.toLowerCase match {
      case "na"   => "NA1"
      case "euw"  => "EUW1"
      case "eune" => "EUN1"
      case "kr"   => "KR"
      case "oce"  => "OC1"
      case "br"   => "BR1"
      case "lan"  => "LA1"
      case "las"  => "LA2"
      case "ru"   => "RU"
      case "tr"   => "TR1"
      case _      => "PBE1"
    }
    implicit val url = s"https://$reg.api.pvp.net/observer-mode/rest/consumer/getSpectatorGameInfo/$platformId/$id?api_key="
    jsonTo[CurrentGameInfo]
  }

  // =====================
  //    Games api calls
  // =====================

  def recentGamesById(id: Long, reg: String = reg_)(implicit caller: ApiCaller): Try[RecentGames] = {
    implicit val url = s"${baseUrl(reg)}/$gameVer/game/by-summoner/$id/recent?api_key="
    jsonTo[RecentGames]
  }

  // =====================
  //    league api calls
  // =====================

  def leagueEntryByIds(ids: List[Long], reg: String = reg_)(implicit caller: ApiCaller): Try[Map[Long, List[League]]] = {
    implicit val url = s"${baseUrl(reg)}/$leagueVer/league/by-summoner/${ids.mkString(",")}/entry?api_key="
    jsonToMap[Long, List[League]](ids)
  }

  def leagueEntryById(id: Long, reg: String = reg_)(implicit caller: ApiCaller): Try[List[League]] = {
    leagueEntryByIds(List(id), reg).map(_.getOrElse(id, Nil))
  }

  // =====================
  //    Stats api calls
  // =====================

  def rankedStatsById(id: Long, season: Int, reg: String = reg_)(implicit caller: ApiCaller): Try[RankedStats] = {
    implicit val url = s"${baseUrl(reg)}/$statsVer/stats/by-summoner/$id/ranked?season=SEASON$season&api_key="
    jsonTo[RankedStats]
  }

  def summaryStatsById(id: Long, season: Int, reg: String = reg_)(implicit caller: ApiCaller): Try[PlayerStatsSummaryList] = {
    implicit val url = s"${baseUrl(reg)}/$statsVer/stats/by-summoner/$id/summary?season=SEASON$season&api_key="
    jsonTo[PlayerStatsSummaryList]
  }

  // =====================
  //  Summoner api calls
  // =====================

  def summonerByNames(names: List[String], reg: String = reg_)(implicit caller: ApiCaller): Try[Map[String, Summoner]] = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/by-name/${names.mkString(",")}?api_key="
    jsonToMap[String, Summoner](names)
  }

  def summonerByName(name: String, reg: String = reg_)(implicit caller: ApiCaller):Try[Summoner] = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/by-name/$name?api_key="
    jsonTo[String, Summoner](name)
  }

  def summonerByIds(ids: List[Long], reg: String = reg_)(implicit caller: ApiCaller): Try[Map[Long, Summoner]] = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/${ids.mkString(",")}?api_key="
    jsonToMap[Long, Summoner](ids)
  }

  def summonerById(id: Long, reg: String = reg_)(implicit caller: ApiCaller): Try[Summoner] = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/$id?api_key="
    jsonTo[Long, Summoner](id)
  }

  def masteryPagesByIds(ids: List[Long], reg: String = reg_)(implicit caller: ApiCaller): Try[Map[Long, MasteryPages]] = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/${ids.mkString(",")}/masteries?api_key="
    jsonToMap[Long, MasteryPages](ids)
  }

  def summonerNameById(id: Long, reg: String = reg_)(implicit caller: ApiCaller): Try[String] = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/$id/name?api_key="
    jsonTo[Long, String](id)
  }

  def runePagesByIds(ids: List[Long], reg: String = reg_)(implicit caller: ApiCaller): Try[Map[Long, RunePages]] = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/${ids.mkString(",")}/runes?api_key="
    jsonToMap[Long, RunePages](ids)
  }

  // =====================
  //    Team api calls
  // =====================

  def teamBySummonerIds(ids: List[Long], reg: String = reg_)(implicit caller: ApiCaller): Try[Map[Long, List[Team]]] = {
    implicit val url = s"${baseUrl(reg)}/$teamVer/team/by-summoner/${ids.mkString(",")}?api_key="
    jsonToMap[Long, List[Team]](ids)
  }

  def teamByTeamIds(ids: List[String], reg: String = reg_)(implicit caller: ApiCaller): Try[Map[String, Team]] = {
    implicit val url = s"${baseUrl(reg)}/$teamVer/team/${ids.mkString(",")}?api_key="
    jsonToMap[String, Team](ids)
  }

  // =====================
  // Static-data api calls
  // =====================

  def champStaticDataById(id: Int)(implicit caller: ApiCaller): Try[Champion] = {
    implicit val url = s"https://na.api.pvp.net/api/lol/static-data/na/v1.2/champion/$id?&api_key="
    jsonTo[Champion]
  }

  def spellStaticDataById(id: Int)(implicit caller: ApiCaller): Try[SummonerSpell] = {
    implicit val url = s"https://na.api.pvp.net/api/lol/static-data/na/$staticDataVer/summoner-spell/$id?api_key="
    jsonTo[SummonerSpell]
  }

  private def jsonToMap[A, B: Reads](keys: List[A])(implicit caller: ApiCaller, url: String): Try[Map[A, B]] = {
    def asMap(jsonResponse: String) = {
      (for {
        key ← keys
        value ← (Json.parse(jsonResponse) \ key.toString).asOpt[B]
      } yield key → value).toMap
    }

    caller.call(url).map(asMap)
  }

  private def jsonTo[A, B: Reads](key: A)(implicit caller: ApiCaller, url: String): Try[B] = {
    caller.call(url).map(json => {
      (Json.parse(json) \ key.toString).as[B]
    })
  }

  private def jsonTo[A: Reads]()(implicit caller: ApiCaller, url: String): Try[A] = {
    caller.call(url).map(json => Json.parse(json).as[A])
  }
}

