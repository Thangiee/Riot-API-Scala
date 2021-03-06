package thangiee

import scala.concurrent.duration.{TimeUnit, Duration, FiniteDuration, DurationConversions}

package object riotapi {
  type JsonString = String

  sealed trait RiotError { def code: Int }
  case class BadRequest(url: String)    extends RiotError { def code: Int = 400 }
  case class Unauthorized(key: String)  extends RiotError { def code: Int = 401 }
  case class RateLimit(key: String)     extends RiotError { def code: Int = 429 }
  object DataNotFound                   extends RiotError { def code: Int = 404 }
  object ServerError                    extends RiotError { def code: Int = 500 }
  object ServiceUnavailable             extends RiotError { def code: Int = 503 }
  object TimeOut                        extends RiotError { def code: Int = 408 }

  sealed trait QueueType { def `type`: String }
  object RankedSolo5v5 extends QueueType { def `type`: String = "RANKED_SOLO_5x5" }
  object RankedTeam5v5 extends QueueType { def `type`: String = "RANKED_TEAM_5x5" }
  object RankedTeam3v3 extends QueueType { def `type`: String = "RANKED_TEAM_3x3" }

  // Taken from scala.concurrent.duration package object to let the client
  // use the duration conversions easily without need to import scala.concurrent.duration._
  implicit final class DurationInt(private val n: Int) extends AnyVal with DurationConversions {
    override protected def durationIn(unit: TimeUnit): FiniteDuration = Duration(n.toLong, unit)
  }

  implicit final class DurationLong(private val n: Long) extends AnyVal with DurationConversions {
    override protected def durationIn(unit: TimeUnit): FiniteDuration = Duration(n, unit)
  }

  implicit final class DurationDouble(private val d: Double) extends AnyVal with DurationConversions {
    override protected def durationIn(unit: TimeUnit): FiniteDuration =
      Duration(d, unit) match {
        case f: FiniteDuration => f
        case _ => throw new IllegalArgumentException("Duration DSL not applicable to " + d)
      }
  }
  // ==================================================================
}
