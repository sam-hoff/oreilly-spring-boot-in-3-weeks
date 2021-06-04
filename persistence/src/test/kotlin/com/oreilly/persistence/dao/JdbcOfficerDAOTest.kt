package com.oreilly.persistence.dao

import com.oreilly.persistence.entities.Officer
import com.oreilly.persistence.entities.Rank
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import org.junit.jupiter.api.Assertions.*

import org.hamcrest.Matchers.containsInAnyOrder
import org.springframework.beans.factory.annotation.Qualifier

@SpringBootTest
@Transactional
class JdbcOfficerDAOTest(
    @Autowired @Qualifier("jdbcOfficerDAO")
    val dao: OfficerDAO
) {
    @Test
    fun save() {
        var officer = Officer(rank = Rank.LIEUTENANT, first = "Nyota", last = "Uhuru")
        officer = dao.save(officer)
        assertNotNull(officer.id)
    }

    @Test
    fun findByIdThatExists() {
        val officer = dao.findById(1)
        assertTrue(officer != null)
        assertEquals(1, officer!!.id)
    }

    @Test
    fun findByIdThatDoesNotExist() {
        val officer = dao.findById(999)
        assertTrue(officer == null)
    }

    @Test
    fun count() {
        assertEquals(5, dao.count())
    }

    @Test
    fun findAll() {
        val dbNames = dao.findAll().map { it.last }
        assertThat(dbNames, containsInAnyOrder("Kirk", "Picard", "Sisko", "Janeway", "Archer"))
    }

    @Test
    fun delete() {
        (1..5).forEach { id ->
            val officer = dao.findById(id)
            assertTrue(officer != null)
            dao.delete(officer!!)
        }
        assertEquals(0, dao.count())
    }

    @Test
    fun existsById() {
        (1..5).forEach { id -> assertTrue(dao.existsById(id)) }
    }
}