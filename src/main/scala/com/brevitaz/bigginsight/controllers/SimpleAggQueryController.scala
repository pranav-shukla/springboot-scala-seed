package com.brevitaz.bigginsight.controllers

import com.brevitaz.bigginsight.restdomain.AggQuery
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.{RequestBody, RequestMapping, RestController}

/**
  * Created by pranavshukla on 26/02/16.
  */
@RestController
class SimpleAggQueryController {

  val logger = Logger(LoggerFactory.getLogger(classOf[SimpleAggQueryController]))

  @RequestMapping(path = Array("/simpleAggQuery"))
  def sayHello(@RequestBody request: AggQuery) = {

    logger.info(s"simple agg query $request")

    println("before calling ...")

    logger.debug(somefunction())
    logger.info(anotherfunc())
    logger.info("this will be printed")


    "Hello How are you?"
  }

  def somefunction(): String = {
    println("Inside the function")
    "this will not be printed"
  }

  def anotherfunc(): String = {
    println("Inside another function")
    "this another func will be printed"
  }

}
