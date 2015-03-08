package thangiee.riotapi.core

import scala.util.Try

trait ApiCaller {
  def call(url: String): Try[String]
}
