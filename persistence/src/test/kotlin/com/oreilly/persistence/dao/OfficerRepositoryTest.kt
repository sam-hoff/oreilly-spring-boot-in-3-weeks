package com.oreilly.persistence.dao

import com.oreilly.persistence.entities.Officer
import com.oreilly.persistence.entities.Rank
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsInAnyOrder
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class OfficerRepositoryTest(
    @Autowired
    private val repository: OfficerRepository,
    @Autowired
    private val template: JdbcTemplate,
) {
    fun getIds(): List<Int> {
        return template.query("select id from officers") { rs, _ ->
            rs.getInt("id")
        }
    }

    @Test
    fun testSave() {
        var officer = Officer(Rank.LIEUTENANT, "Nyota", "Uhuru")
        officer = repository.save(officer)
        assertNotNull(officer.id)
    }

    @Test
    fun findOneThatExists() {
        getIds().forEach { id ->
            val officer = repository.findById(id)
            assertTrue(officer.isPresent)

            assertEquals(id, officer.get().id)

        }
    }

    @Test
    fun findOneThatDoesNotExist() {
        val officer = repository.findById(999)
        assertFalse(officer.isPresent)
    }

    @Test
    fun findAll() {
        val dbNames = repository.findAll().map { it.last }
        assertThat(dbNames, containsInAnyOrder("Kirk", "Picard", "Sisko", "Janeway", "Archer"))
    }

    @Test
    fun count() {
        assertEquals(5, repository.count())
    }

    @Test
    fun delete() {
        getIds().forEach { id ->
            val officer = repository.findById(id)
            assertTrue(officer.isPresent)
            repository.delete(officer.get())
        }
        assertEquals(0, repository.count())
    }

    @Test
    fun existsById() {
        getIds().forEach { id ->
            assertTrue(repository.existsById(id))
        }
    }
}