package com.oreilly.persistence.dao

import com.oreilly.persistence.entities.Officer
import com.oreilly.persistence.entities.Rank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

import org.springframework.jdbc.core.simple.SimpleJdbcInsert

@Repository
class JdbcOfficerDAO(
    @Autowired
    val jdbcTemplate: JdbcTemplate
) : OfficerDAO {
    private val insertOfficer = SimpleJdbcInsert(jdbcTemplate)
        .withTableName("officers")
        .usingGeneratedKeyColumns("id")

    override fun save(officer: Officer): Officer {
        val parameters = mapOf<String, Any?>(
            "rank" to officer.rank,
            "first_name" to officer.first,
            "last_name" to officer.last
        )
        val newId = insertOfficer.executeAndReturnKey(parameters)
        officer.id = newId as Int
        return officer
    }

    override fun findById(id: Int): Officer? {
        if (!existsById(id)) return null
        return jdbcTemplate.queryForObject(
            "select * from officers where id=?",
            RowMapper { rs, _ ->
                return@RowMapper Officer(
                    rs.getInt("id"),
                    Rank.valueOf(rs.getString("rank")),
                    rs.getString("first_name"),
                    rs.getString("last_name")
                )
            },
            id
        )
    }

    override fun findAll(): List<Officer> {
        return jdbcTemplate.query(
            "select * from officers"
        ) { rs, _ ->
            Officer(
                rs.getInt("id"),
                Rank.valueOf(rs.getString("rank")),
                rs.getString("first_name"),
                rs.getString("last_name")
            )
        }
    }

    override fun count(): Long {
        return jdbcTemplate.queryForObject(
            "select count(*) from officers", Long::class.java
        )!!
    }

    override fun delete(officer: Officer) {
        jdbcTemplate.update(
            "delete from officers where id=?", officer.id
        )
    }

    override fun existsById(id: Int): Boolean {
        return jdbcTemplate.queryForObject(
            "select exists(select 1 from officers where id=?)", Boolean::class.java, id
        )
    }

}