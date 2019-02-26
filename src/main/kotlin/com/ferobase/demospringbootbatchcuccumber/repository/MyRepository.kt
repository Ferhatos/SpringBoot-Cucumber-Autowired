package com.ferobase.demospringbootbatchcuccumber.repository

import com.ferobase.demospringbootbatchcuccumber.entities.MyEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MyRepository : JpaRepository<MyEntity, Long>