package com.ferobase.demospringbootbatchcuccumber.batch

import com.ferobase.demospringbootbatchcuccumber.entities.MyEntity
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.database.JpaItemWriter
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import javax.persistence.EntityManagerFactory


@Configuration
@EnableBatchProcessing
class JobConfiguration {

    @Autowired
    var jobBuilderFactory: JobBuilderFactory? = null

    @Autowired
    var stepBuilderFactory: StepBuilderFactory? = null

    @Autowired
    var entityManagerFactory: EntityManagerFactory? = null

    @Bean
    fun reader(): FlatFileItemReader<MyEntity> {
        return FlatFileItemReaderBuilder<MyEntity>()
                .name("MyEntityItemReader")
                .resource(ClassPathResource("sample-data.csv"))
                .delimited()
                .names(arrayOf("name", "firstName"))
                .fieldSetMapper(object : BeanWrapperFieldSetMapper<MyEntity>() {
                    init {
                        setTargetType(MyEntity::class.java)
                    }
                })
                .build()
    }


    @Bean
    fun writer(): JpaItemWriter<MyEntity> {
        val writer: JpaItemWriter<MyEntity> = JpaItemWriter()
        writer.setEntityManagerFactory(entityManagerFactory!!)
        return writer
    }
    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    fun importUserJob(step1: Step): Job {
        return jobBuilderFactory!!.get("importUserJob")
                .incrementer(RunIdIncrementer())
                .flow(step1)
                .end()
                .build()
    }

    @Bean
    fun step1(): Step {
        return stepBuilderFactory!!.get("step1")
                .chunk<MyEntity, MyEntity>(10)
                .reader(reader())
                .writer(writer())
                .build()
    }
    // end::jobstep[]
}