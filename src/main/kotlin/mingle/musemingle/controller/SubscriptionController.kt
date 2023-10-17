package mingle.musemingle.controller

import mingle.musemingle.domain.Subscription
import mingle.musemingle.service.SubscriptionService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/subscriptions")
class SubscriptionController(private val subscriptionService: SubscriptionService) {
    @GetMapping
    fun getAllSubscriptions(): List<Subscription> = subscriptionService.findAll()

    @GetMapping("/{id}")
    fun getSubscriptionById(@PathVariable id: Int): Subscription? = subscriptionService.findById(id)

    @PostMapping
    fun createSubscription(@RequestBody subscription: Subscription): Subscription = subscriptionService.save(subscription)

    @PutMapping("/{id}")
    fun updateSubscription(@PathVariable id: Int, @RequestBody subscription: Subscription): Subscription {
        if (subscriptionService.findById(id) != null) {
            return subscriptionService.save(subscription)
        }
        throw Exception("Subscription not found")
    }

    @DeleteMapping("/{id}")
    fun deleteSubscription(@PathVariable id: Int) = subscriptionService.deleteById(id)
}
