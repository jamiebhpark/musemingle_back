package mingle.musemingle.controller

import mingle.musemingle.domain.AuthMethod
import mingle.musemingle.domain.AuthenticationMethod
import mingle.musemingle.service.AuthenticationMethodService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth-methods")
class AuthenticationMethodController(private val authMethodService: AuthenticationMethodService) {

    @GetMapping("/method/{method}/user/{userId}")
    fun getAuthMethodByMethodAndUserId(@PathVariable method: AuthMethod, @PathVariable userId: Int): ResponseEntity<AuthenticationMethod> {
        val authMethod = authMethodService.findByMethodAndUserId(method, userId)
            ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(authMethod)
    }

    @PostMapping
    fun createAuthMethod(@RequestBody authMethod: AuthenticationMethod): ResponseEntity<AuthenticationMethod> {
        val savedAuthMethod = authMethodService.save(authMethod)
        return ResponseEntity.ok(savedAuthMethod)
    }

    // 필요한 다른 엔드포인트와 로직들을 추가하세요.
}
