package com.brevitaz.bigginsight

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration

/**
  * Created by pranavshukla on 27/02/16.
  */
@RunWith(classOf[SpringJUnit4ClassRunner])
@SpringApplicationConfiguration(classes = Array(classOf[BigginsightRestApplicationTests]))
@WebAppConfiguration
class BigginsightRestApplicationTests {

  @Test def contextLoads {
  }

}