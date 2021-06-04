package com.oreilly.persistence.entities

import javax.persistence.*

@Entity
@Table(name = "officers")
data class Officer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val rank: Rank,
    @Column(nullable = false, name = "first_name")
    val first: String,
    @Column(nullable = false, name = "last_name")
    val last: String
) {
    constructor(
        rank: Rank,
        first: String,
        last: String
    ) : this(id = null, rank, first, last)
}