package thangiee

package object riotapi {
  type JsonString = String

  sealed trait RiotError
  object BadRequest extends RiotError
  object Unauthorized extends RiotError
  object DataNotFound extends RiotError
  object RateLimit extends RiotError
  object ServerError extends RiotError
  object ServiceUnavailable extends RiotError
  object TimeOut extends RiotError

  sealed trait QueueType { def `type`: String }
  object RankedSolo5v5 extends QueueType { def `type`: String = "RANKED_SOLO_5x5" }
  object RankedTeam5v5 extends QueueType { def `type`: String = "RANKED_TEAM_5x5" }
  object RankedTeam3v3 extends QueueType { def `type`: String = "RANKED_TEAM_3x3" }

  object Implicit {
    implicit val simpleApiCaller = SimpleApiCaller
  }
}
