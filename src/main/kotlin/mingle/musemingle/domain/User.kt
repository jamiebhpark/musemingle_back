package mingle.musemingle.domain

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    var username: String? = null,

    @Column(unique = true)
    var email: String? = null,

    @Enumerated(EnumType.STRING)
    var role: UserRole,

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    var subscription: Subscription? = null,

    @ManyToOne
    @JoinColumn(name = "auth_method_id")
    var authMethod: AuthenticationMethod? = null,

    @Column(name = "password_hash")
    var passwordHash: String? = null,

    @Column(name = "password_salt")
    var passwordSalt: String? = null,

    var credentials: String? = null,

    @Column(name = "profile_image")
    var profileImage: String? = null,

    var bio: String? = null,

    @Column(name = "degree_certificate")
    var degreeCertificate: String? = null,

    var portfolio: String? = null,

    @Column(name = "is_verified")
    var isVerified: Boolean? = null,

    @Column(name = "created_at")
    var createdAt: Timestamp? = null,

    @Column(name = "updated_at")
    var updatedAt: Timestamp? = null,

    @Column(name = "apple_sub")
    var appleSub: String? = null
)

enum class UserRole {
    ARTIST, GALLERY
}
