package com.ferobase.demospringbootbatchcuccumber

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith
import org.springframework.test.context.ContextConfiguration


@RunWith(Cucumber::class)
@CucumberOptions(features = ["src/test/resources"], plugin = ["pretty", "json:target/cucumber.json"])
//@CucumberOptions(plugin = {"json:target/cucumber.json"; "pretty"}, features = ["src/test/features"])
@ContextConfiguration(classes = [DemoSpringBootBatchCuccumberApplication::class])
class CucumberTest