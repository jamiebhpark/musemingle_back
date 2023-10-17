package mingle.musemingle.service

import mingle.musemingle.domain.User
import mingle.musemingle.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile


@Service
class UserService(private val userRepository: UserRepository, private val s3Service: S3Service) {

    // 이메일로 사용자 검색
    @Transactional(readOnly = true)
    fun findByEmail(email: String) = userRepository.findByEmail(email)

    // ID로 사용자 검색
    @Transactional(readOnly = true)
    fun findById(id: Int) = userRepository.findById(id).orElse(null)

    // 모든 사용자 조회
    @Transactional(readOnly = true)
    fun findAll() = userRepository.findAll()

    // 사용자 생성
    @Transactional
    fun create(user: User) = userRepository.save(user)

    // 사용자 업데이트
    @Transactional
    fun update(user: User) = userRepository.save(user)

    @Transactional
    fun save(user: User) = userRepository.save(user)

    @Transactional
    fun existsById(id: Int): Boolean = userRepository.existsById(id)

    // 사용자 삭제
    @Transactional
    fun deleteById(id: Int) = userRepository.deleteById(id)

    fun uploadProfileImage(file: MultipartFile): String {
        return s3Service.uploadFile("users/profiles", file.bytes, file.originalFilename ?: "default.jpg")
    }

    fun uploadDegreeCertificate(file: MultipartFile): String {
        return s3Service.uploadFile("users/certificates", file.bytes, file.originalFilename ?: "default.jpg")
    }

    fun deleteProfileImage(url: String) {
        s3Service.deleteFile(url)
    }

    fun deleteDegreeCertificate(url: String) {
        s3Service.deleteFile(url)
    }
}
