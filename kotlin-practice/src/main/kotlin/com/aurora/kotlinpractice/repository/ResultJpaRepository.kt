package com.aurora.kotlinpractice.repository

import com.aurora.kotlinpractice.entity.Result
import org.springframework.data.jpa.repository.JpaRepository

interface ResultJpaRepository : JpaRepository<Result, Long> {
}