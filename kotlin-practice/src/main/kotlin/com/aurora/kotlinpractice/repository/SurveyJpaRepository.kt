package com.aurora.kotlinpractice.repository

import com.aurora.kotlinpractice.entity.Survey
import org.springframework.data.jpa.repository.JpaRepository

interface SurveyJpaRepository : JpaRepository<Survey, Long> {
}