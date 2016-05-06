package com.brevitaz.bigginsight

import com.brevitaz.bigginsight.converter.Json4sHttpMessageConverter
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.web.HttpMessageConverters
import org.springframework.context.annotation.Bean

/**
  * Created by pranavshukla on 26/02/16.
  */
@SpringBootApplication
class BiggInsightRestConf {

  @Bean
  @ConditionalOnMissingBean
  def json4sHttpMessageConverter() = {
    new Json4sHttpMessageConverter
  }

  @Bean
  def customConverters(): HttpMessageConverters = {
    new HttpMessageConverters(json4sHttpMessageConverter())
  }
}


object BiggInsightRestApp extends App {
  SpringApplication.run(classOf[BiggInsightRestConf])
}
