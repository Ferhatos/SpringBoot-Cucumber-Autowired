package com.ferobase.demospringbootbatchcuccumber.cucumbertest

import com.ferobase.demospringbootbatchcuccumber.DemoSpringBootBatchCuccumberApplication
import com.ferobase.demospringbootbatchcuccumber.entities.MyEntity
import com.ferobase.demospringbootbatchcuccumber.repository.MyRepository
import cucumber.api.DataTable
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.junit.Ignore
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [DemoSpringBootBatchCuccumberApplication::class],
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
@Ignore
@Transactional
class FeatureTest : CucumberTest() {

    @LocalServerPort
    var port: Int = 0

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    lateinit var myRepository: MyRepository

    @Given("^: input entities$")
    @Throws(Throwable::class)
    fun input_entities(entities: List<MyEntity>) {
        myRepository.saveAll(entities)
    }

    @When("^: run job$")
    @Throws(Throwable::class)
    fun run_job() {
        this.restTemplate.getForObject("http://localhost:$port/launchjob",
                String::class.java)
    }

    @Then("^: result entities$")
    @Throws(Throwable::class)
    fun result_entities(arg1: DataTable) {
        println(myRepository.findAll().size)
    }

}