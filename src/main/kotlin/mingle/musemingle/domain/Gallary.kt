package mingle.musemingle.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "galleries")
data class Gallery(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column(name = "user_id", unique = true)
    val userId: Int,

    @Column(name = "name")
    var name: String,

    @Column(name = "city")
    var city: String?,

    @Column(name = "state")
    var state: String?,

    @Column(name = "country")
    var country: String?,

    @Column(name = "zip_code")
    var zipCode: String?,

    @Column(name = "is_verified")
    var isVerified: Boolean?,

    @Column(name = "website")
    var website: String?,

    @Column(name = "verification_document")
    var verificationDocument: String?,

    @Column(name = "created_at")
    val createdAt: LocalDateTime,

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime
)
