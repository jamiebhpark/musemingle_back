package mingle.musemingle.domain

import jakarta.persistence.*

@Entity
@Table(name = "authentication_methods")
data class AuthenticationMethod(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val method: AuthMethod,

    val details: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User? = null
)

enum class AuthMethod {
    FACEBOOK, GOOGLE, APPLE, EMAIL
}
