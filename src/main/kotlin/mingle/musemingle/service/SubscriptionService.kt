package mingle.musemingle.service

import mingle.musemingle.domain.Subscription
import mingle.musemingle.repository.SubscriptionRepository
import org.springframework.stereotype.Service

@Service
class SubscriptionService(private val subscriptionRepository: SubscriptionRepository) {
    fun findAll(): List<Subscription> = subscriptionRepository.findAll()

    fun findById(id: Int): Subscription? = subscriptionRepository.findById(id).orElse(null)

    fun save(subscription: Subscription): Subscription = subscriptionRepository.save(subscription)

    fun deleteById(id: Int) = subscriptionRepository.deleteById(id)
}
