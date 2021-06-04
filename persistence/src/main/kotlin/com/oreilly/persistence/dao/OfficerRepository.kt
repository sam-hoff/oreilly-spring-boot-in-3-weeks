package com.oreilly.persistence.dao

import com.oreilly.persistence.entities.Officer
import com.oreilly.persistence.entities.Rank
import org.springframework.data.jpa.repository.JpaRepository


interface OfficerRepository: JpaRepository<Officer, Int>{
    fun findByRank(rank: Rank): Iterable<Officer>
}