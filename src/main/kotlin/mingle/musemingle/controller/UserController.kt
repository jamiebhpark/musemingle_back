package mingle.musemingle.controller

import mingle.musemingle.domain.User
import mingle.musemingle.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Int): ResponseEntity<User> {
        val user = userService.findById(id)
            ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(user)
    }

    @GetMapping("/email/{email}")
    fun getUserByEmail(@PathVariable email: String): ResponseEntity<User> {
        val user = userService.findByEmail(email)
            ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(user)
    }

    @PostMapping
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        val savedUser = userService.save(user)
        return ResponseEntity.ok(savedUser)
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Int, @RequestBody user: User): ResponseEntity<User> {
        if (userService.existsById(id)) {
            val updatedUser = userService.save(user)
            return ResponseEntity.ok(updatedUser)
        }
        return ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Int): ResponseEntity<Void> {
        if (userService.existsById(id)) {
            userService.deleteById(id)
            return ResponseEntity.noContent().build()
        }
        return ResponseEntity.notFound().build()
    }
    @PostMapping("/upload/profile")
    fun uploadProfileImage(@RequestParam("file") file: MultipartFile): ResponseEntity<String> {
        val url = userService.uploadProfileImage(file)
        return ResponseEntity.ok(url)
    }

    @PostMapping("/upload/certificate")
    fun uploadDegreeCertificate(@RequestParam("file") file: MultipartFile): ResponseEntity<String> {
        val url = userService.uploadDegreeCertificate(file)
        return ResponseEntity.ok(url)
    }

    @DeleteMapping("/delete/profile/{url}")
    fun deleteProfileImage(@PathVariable url: String): ResponseEntity<Void> {
        userService.deleteProfileImage(url)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/delete/certificate/{url}")
    fun deleteDegreeCertificate(@PathVariable url: String): ResponseEntity<Void> {
        userService.deleteDegreeCertificate(url)
        return ResponseEntity.noContent().build()
    }

    // 필요한 다른 엔드포인트와 로직들을 추가하세요.
}
