package thangiee.riotapi

import org.scalactic.Or
import play.api.libs.json._
import thangiee.riotapi.`match`.MatchDetail
import thangiee.riotapi.currentgame.CurrentGameInfo
import thangiee.riotapi.game.RecentGames
import thangiee.riotapi.league.League
import thangiee.riotapi.matchhistory.PlayerHistory
import thangiee.riotapi.matchlist.MatchList
import thangiee.riotapi.staticdata.{Champion, SummonerSpell}
import thangiee.riotapi.stats.{PlayerStatsSummaryList, RankedStats}
import thangiee.riotapi.summoner.{MasteryPages, RunePages, Summoner}
import thangiee.riotapi.team.Team

object RiotApi {
  private var _key = ""
  private var _reg = "na"

  val gameVer       = "v1.3"
  val summVer       = "v1.4"
  val teamVer       = "v2.4"
  val staticDataVer = "v1.2"
  val statsVer      = "v1.3"
  val leagueVer     = "v2.5"
  val matchVer      = "v2.2"

  def regionId_=(regionId: String) = _reg = regionId.toLowerCase

  def regionId = _reg

  def baseUrl(region: String = _reg) = s"https://${region.toLowerCase}.api.pvp.net/api/lol/${region.toLowerCase}"

  def key_=(key: String) = _key = key

  def key = _key

  // =====================
  //   Current games api call
  // =====================

  def currentGameInfoById(id: Long, reg: String = _reg)(implicit caller: ApiCaller): CurrentGameInfo Or RiotError = {
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
    implicit val url = s"https://$reg.api.pvp.net/observer-mode/rest/consumer/getSpectatorGameInfo/$platformId/$id?api_key=$key"
    jsonTo[CurrentGameInfo]
  }

  // =====================
  //    Games api call
  // =====================

  def recentGamesById(id: Long, reg: String = _reg)(implicit caller: ApiCaller): RecentGames Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$gameVer/game/by-summoner/$id/recent?api_key=$key"
    jsonTo[RecentGames]
  }

  // =====================
  //    league api calls
  // =====================

  def leagueByIds(ids: List[Long], reg: String = _reg)(implicit caller: ApiCaller): Map[Long, List[League]] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$leagueVer/league/by-summoner/${ids.mkString(",")}?api_key=$key"
    jsonToMap[Long, List[League]](ids)
  }

  def leagueEntryByIds(ids: List[Long], reg: String = _reg)(implicit caller: ApiCaller): Map[Long, List[League]] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$leagueVer/league/by-summoner/${ids.mkString(",")}/entry?api_key=$key"
    jsonToMap[Long, List[League]](ids)
  }

  def leagueEntryById(id: Long, reg: String = _reg)(implicit caller: ApiCaller): List[League] Or RiotError = {
    leagueEntryByIds(List(id), reg).map(_.getOrElse(id, Nil))
  }

  def leagueByTeamIds(ids: List[Long], reg: String = _reg)(implicit caller: ApiCaller): Map[Long, List[League]] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$leagueVer/league/by-team/${ids.mkString(",")}?api_key=$key"
    jsonToMap[Long, List[League]](ids)
  }

  def leagueEntryByTeamIds(ids: List[Long], reg: String = _reg)(implicit caller: ApiCaller): Map[Long, List[League]] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$leagueVer/league/by-team/${ids.mkString(",")}/entry?api_key=$key"
    jsonToMap[Long, List[League]](ids)
  }

  def challengerLeague(queue: QueueType, reg: String = _reg)(implicit caller: ApiCaller): League Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$leagueVer/league/challenger?type=${queue.`type`}&api_key=$key"
    jsonTo[League]
  }

  def masterLeague(queue: QueueType, reg: String = _reg)(implicit caller: ApiCaller): League Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$leagueVer/league/master?type=${queue.`type`}&api_key=$key"
    jsonTo[League]
  }

  // =====================
  //    Stats api calls
  // =====================

  def rankedStatsById(id: Long, season: Int, reg: String = _reg)(implicit caller: ApiCaller): RankedStats Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$statsVer/stats/by-summoner/$id/ranked?season=SEASON$season&api_key=$key"
    jsonTo[RankedStats]
  }

  def summaryStatsById(id: Long, season: Int, reg: String = _reg)(implicit caller: ApiCaller): PlayerStatsSummaryList Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$statsVer/stats/by-summoner/$id/summary?season=SEASON$season&api_key=$key"
    jsonTo[PlayerStatsSummaryList]
  }

  // =====================
  //  Summoner api calls
  // =====================

  def summonerByNames(names: List[String], reg: String = _reg)(implicit caller: ApiCaller): Map[String, Summoner] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/by-name/${names.mkString(",")}?api_key=$key"
    jsonToMap[String, Summoner](names.map(_.toLowerCase))
  }

  def summonerByName(name: String, reg: String = _reg)(implicit caller: ApiCaller): Summoner Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/by-name/$name?api_key=$key"
    jsonTo[String, Summoner](name.toLowerCase)
  }

  def summonerByIds(ids: List[Long], reg: String = _reg)(implicit caller: ApiCaller): Map[Long, Summoner] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/${ids.mkString(",")}?api_key=$key"
    jsonToMap[Long, Summoner](ids)
  }

  def summonerById(id: Long, reg: String = _reg)(implicit caller: ApiCaller): Summoner Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/$id?api_key="
    jsonTo[Long, Summoner](id)
  }

  def masteryPagesByIds(ids: List[Long], reg: String = _reg)(implicit caller: ApiCaller): Map[Long, MasteryPages] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/${ids.mkString(",")}/masteries?api_key=$key"
    jsonToMap[Long, MasteryPages](ids)
  }

  def summonerNameById(id: Long, reg: String = _reg)(implicit caller: ApiCaller): String Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/$id/name?api_key=$key"
    jsonTo[Long, String](id)
  }

  def runePagesByIds(ids: List[Long], reg: String = _reg)(implicit caller: ApiCaller): Map[Long, RunePages] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/${ids.mkString(",")}/runes?api_key=$key"
    jsonToMap[Long, RunePages](ids)
  }

  // =====================
  //    Team api calls
  // =====================

  def teamBySummonerIds(ids: List[Long], reg: String = _reg)(implicit caller: ApiCaller): Map[Long, List[Team]] Or RiotError= {
    implicit val url = s"${baseUrl(reg)}/$teamVer/team/by-summoner/${ids.mkString(",")}?api_key=$key"
    jsonToMap[Long, List[Team]](ids)
  }

  def teamByTeamIds(ids: List[String], reg: String = _reg)(implicit caller: ApiCaller): Map[String, Team] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$teamVer/team/${ids.mkString(",")}?api_key=$key"
    jsonToMap[String, Team](ids)
  }

  // =====================
  // Static-data api calls
  // =====================

  def champStaticDataById(id: Int, reg: String = _reg)(implicit caller: ApiCaller): Champion Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$staticDataVer/champion/$id?&api_key=$key"
    jsonTo[Champion]
  }

  def spellStaticDataById(id: Int, reg: String = _reg)(implicit caller: ApiCaller): SummonerSpell Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$staticDataVer/summoner-spell/$id?api_key=$key"
    jsonTo[SummonerSpell]
  }

  // =====================
  // Match api calls
  // =====================

  def matchListById(id: Int, reg: String = _reg)(implicit caller: ApiCaller): MatchList Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$matchVer/matchlist/by-summoner/$id?api_key=$key"
    jsonTo[MatchList]
  }

  def matchHistoryById(id: Int, reg: String = _reg)(implicit caller: ApiCaller): PlayerHistory Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$matchVer/matchhistory/$id?api_key=$key"
    jsonTo[PlayerHistory]
  }

  def matchByMatchId(id: Int, reg: String = _reg)(implicit caller: ApiCaller): MatchDetail Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$matchVer/match/$id?api_key=$key"
    jsonTo[MatchDetail]
  }

  // =====================

  private def jsonToMap[A, B: Reads](keys: List[A])(implicit caller: ApiCaller, url: String): Map[A, B] Or RiotError = {
    def asMap(jsonResponse: String) = {
      (for {
        key ← keys
        value ← (Json.parse(jsonResponse) \ key.toString).asOpt[B]
      } yield key → value).toMap
    }

    caller.call(url).map(asMap)
  }

  private def jsonTo[A, B: Reads](key: A)(implicit caller: ApiCaller, url: String): B Or RiotError =
    caller.call(url).map(json => (Json.parse(json) \ key.toString).as[B])

  private def jsonTo[A: Reads](implicit caller: ApiCaller, url: String): A Or RiotError =
    caller.call(url).map(json => Json.parse(json).as[A])
}

