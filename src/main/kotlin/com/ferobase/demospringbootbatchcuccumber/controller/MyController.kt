package com.ferobase.demospringbootbatchcuccumber.controller

import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class MyController {

    @Autowired
    internal var jobLauncher: JobLauncher? = null

    @Autowired
    internal var job: Job? = null

    @RequestMapping("/launchjob")
    @Throws(Exception::class)
    fun handle(): String {

        val logger = LoggerFactory.getLogger(this.javaClass)
        try {
            val jobParameters = JobParametersBuilder().addLong("time", System.currentTimeMillis())
                    .toJobParameters()
            jobLauncher!!.run(job!!, jobParameters)
        } catch (e: Exception) {
            logger.info(e.message)
        }

        return "Done"
    }
}