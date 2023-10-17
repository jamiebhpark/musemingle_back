package mingle.musemingle.repository

import mingle.musemingle.domain.AuthMethod
import mingle.musemingle.domain.AuthenticationMethod
import org.springframework.data.jpa.repository.JpaRepository

interface AuthenticationMethodRepository : JpaRepository<AuthenticationMethod, Int> {
    fun findByMethodAndUserId(method: AuthMethod, userId: Int): AuthenticationMethod?
}
