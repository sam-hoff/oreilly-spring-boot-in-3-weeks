package com.oreilly.persistence.dao

import com.oreilly.persistence.entities.Officer

interface OfficerDAO {
 fun save(officer: Officer): Officer
 fun findById(id: Int): Officer?
 fun findAll(): List<Officer>
 fun count(): Long
 fun delete(officer: Officer): Unit
 fun existsById(id: Int): Boolean
}