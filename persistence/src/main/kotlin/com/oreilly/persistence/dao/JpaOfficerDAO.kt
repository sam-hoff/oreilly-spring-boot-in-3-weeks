package com.oreilly.persistence.dao

import com.oreilly.persistence.entities.Officer
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


@Repository
class JpaOfficerDAO(
    @PersistenceContext
    val entityManager: EntityManager
) : OfficerDAO {
    override fun save(officer: Officer): Officer {
        entityManager.persist(officer)
        return officer
    }

    override fun findById(id: Int): Officer? {
        return entityManager.find(Officer::class.java, id)
    }

    override fun findAll(): List<Officer> {
        return entityManager.createQuery("select o from Officer o", Officer::class.java).resultList
    }

    override fun count(): Long {
        return entityManager.createQuery("select count(o.id) from Officer o", Long::class.javaObjectType).singleResult
    }

    override fun delete(officer: Officer) {
        entityManager.remove(officer)
    }

    override fun existsById(id: Int): Boolean {
        val result = entityManager.createQuery("select 1 from Officer o where o.id = :id")
            .setParameter("id", id)
            .singleResult
        return result != null
    }

}
