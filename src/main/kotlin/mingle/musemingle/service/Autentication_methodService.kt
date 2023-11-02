package mingle.musemingle.service

import mingle.musemingle.domain.AuthMethod
import mingle.musemingle.domain.AuthenticationMethod
import mingle.musemingle.repository.AuthenticationMethodRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthenticationMethodService(private val authMethodRepository: AuthenticationMethodRepository) {

    @Transactional(readOnly = true)
    fun findByMethodAndUserId(method: AuthMethod, userId: Int): AuthenticationMethod? {
        return authMethodRepository.findByMethodAndUserId(method, userId)
    }

    @Transactional
    fun save(authMethod: AuthenticationMethod): AuthenticationMethod {
        return authMethodRepository.save(authMethod)
    }

}

