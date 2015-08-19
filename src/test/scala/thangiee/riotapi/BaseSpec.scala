package thangiee.riotapi

import org.scalactic.Good
import org.scalamock.scalatest.MockFactory
import org.scalatest._

trait BaseSpec extends FlatSpec with Matchers with MockFactory {
  abstract class MockScope {
    def json: String
    RiotApi.key = "456267a6-1777-4763-a77f-f3b1f06ed99d"
    implicit val m: ApiCaller = mock[ApiCaller]
    (m.call _).expects(*, *).returning(Good(json))
  }
}
