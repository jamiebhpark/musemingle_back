package mingle.musemingle.repository

import mingle.musemingle.domain.Subscription
import org.springframework.data.jpa.repository.JpaRepository

interface SubscriptionRepository : JpaRepository<Subscription, Int>