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
class JpaOfficerDAOTest(
    @Autowired
    private val dao: JpaOfficerDAO,
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
        officer = dao.save(officer)
        assertNotNull(officer.id)
    }

    @Test
    fun findOneThatExists() {
        getIds().forEach { id ->
            val officer = dao.findById(id)
            assertTrue(officer != null)

            assertEquals(id, officer?.id)

        }
    }

    @Test
    fun findOneThatDoesNotExist() {
        val officer = dao.findById(999)
        assertTrue(officer == null)
    }

    @Test
    fun findAll() {
        val dbNames = dao.findAll().map { it.last }
        assertThat(dbNames, containsInAnyOrder("Kirk", "Picard", "Sisko", "Janeway", "Archer"))
    }

    @Test
    fun count() {
        assertEquals(5, dao.count())
    }

    @Test
    fun delete() {
        getIds().forEach { id ->
            val officer = dao.findById(id)
            assertTrue(officer != null)
            dao.delete(officer!!)
        }
        assertEquals(0, dao.count())
    }

    @Test
    fun existsById() {
        getIds().forEach { id ->
            assertTrue(dao.existsById(id))
        }
    }
}