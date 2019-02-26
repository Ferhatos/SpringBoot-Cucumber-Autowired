package com.ferobase.demospringbootbatchcuccumber.cucumbertest

import com.ferobase.demospringbootbatchcuccumber.DemoSpringBootBatchCuccumberApplication
import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith
import org.springframework.test.context.ContextConfiguration


@RunWith(Cucumber::class)
@CucumberOptions(plugin = ["pretty"], features = ["src/test/resources/features/MyFeature.feature"],
        glue = ["com.ferobase.demospringbootbatchcuccumber"])
@ContextConfiguration(classes = [DemoSpringBootBatchCuccumberApplication::class])
open class CucumberTest