package com.aurora.kotlinpractice.repository

import com.aurora.kotlinpractice.entity.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemJpaRepository : JpaRepository<Item, Long> {
}