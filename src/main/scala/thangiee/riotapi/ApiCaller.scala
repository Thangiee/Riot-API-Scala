package thangiee.riotapi

import org.scalactic.Or

trait ApiCaller {
  def call(url: String): JsonString Or RiotError
}
