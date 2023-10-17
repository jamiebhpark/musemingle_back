package mingle.musemingle.repository

import mingle.musemingle.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {
    fun findByEmail(email: String): User?
}
