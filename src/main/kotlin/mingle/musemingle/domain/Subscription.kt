package mingle.musemingle.domain

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "subscriptions")
data class Subscription(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_type", nullable = false)
    val subscriptionType: SubscriptionType,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val role: Role,

    @Column(nullable = false)
    val price: BigDecimal
) {
    enum class SubscriptionType {
        FREE, PREMIUM
    }

    enum class Role {
        ARTIST, GALLERY
    }
}