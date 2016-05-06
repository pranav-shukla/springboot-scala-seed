package com.brevitaz.bigginsight

import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SimpleTestSpec extends FlatSpec with Matchers {

  "this method" should " behave " in {

    1 should be (1)

  }

}
