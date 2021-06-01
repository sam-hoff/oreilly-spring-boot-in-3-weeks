package com.example.jdbcexample


class Customer(
    val firstName: String,
    val lastName: String,
    var id: Int?,
) {
    override fun toString(): String {
        return "Customer[id=$id, firstName=$firstName', lastName=$lastName]"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Customer) return false
        val customer: Customer = other
        if (id != customer.id) return false
        return if (
            firstName != customer.firstName)
                        false
                else lastName == customer.lastName
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        return result
    }
}