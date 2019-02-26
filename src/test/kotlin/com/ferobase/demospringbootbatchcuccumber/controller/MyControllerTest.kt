package com.ferobase.demospringbootbatchcuccumber.controller

import com.ferobase.demospringbootbatchcuccumber.DemoSpringBootBatchCuccumberApplication
import com.ferobase.demospringbootbatchcuccumber.repository.MyRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [DemoSpringBootBatchCuccumberApplication::class])
@ContextConfiguration
//@WebAppConfiguration
//@IntegrationTest
class MyControllerTest {

    @LocalServerPort
    var port: Int = 0

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    lateinit var myRepository: MyRepository

    @Test
    @Throws(Exception::class)
    fun greetingShouldReturnDefaultMessage() {
        assertThat(this.restTemplate.getForObject("http://localhost:$port/launchjob",
                String::class.java)).contains("Done")
        assertThat(myRepository.findAll().size == 3)
    }
}