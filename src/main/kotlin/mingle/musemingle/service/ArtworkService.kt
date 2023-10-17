package mingle.musemingle.service

import mingle.musemingle.domain.Artwork
import mingle.musemingle.repository.ArtworkRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class ArtworkService(
    private val artworkRepository: ArtworkRepository,
    private val s3Service: S3Service  // AmazonS3Service 주입
) {

    @Transactional(readOnly = true)
    fun findById(id: Int) = artworkRepository.findById(id).orElse(null)!!

    @Transactional(readOnly = true)
    fun findByArtistId(artistId: Int) = artworkRepository.findByArtistId(artistId)

    @Transactional
    fun save(artwork: Artwork, imageFile: MultipartFile?): Artwork {
        imageFile?.let {
            val imageUrl = uploadToS3(it) // 이미지 업로드
            artwork.image = imageUrl
        }
        return artworkRepository.save(artwork)
    }

    @Transactional
    fun deleteById(id: Int) {
        val artwork = findById(id)
        artwork.image?.let {
            deleteFromS3(it) // 이미지 삭제
        }
        artworkRepository.deleteById(id)
    }

    @Transactional
    fun update(id: Int, updatedArtwork: Artwork, imageFile: MultipartFile?): Artwork {
        val artwork = artworkRepository.findById(id).orElseThrow { IllegalArgumentException("No Artwork with given ID found") }

        // S3에 이미지 업데이트 (이전 이미지가 있다면 삭제)
        artwork.image?.let { deleteFromS3(it) }
        imageFile?.let {
            val imageUrl = uploadToS3(it)
            updatedArtwork.image = imageUrl
        }

        return artworkRepository.save(updatedArtwork)
    }

    // S3 연동 메소드
    private fun uploadToS3(file: MultipartFile): String {
        val fileName = UUID.randomUUID().toString() + file.originalFilename
        return s3Service.uploadFile("artworks", file.bytes, fileName)
    }

    private fun deleteFromS3(url: String) {
        val fileName = url.substringAfterLast("artworks/")
        s3Service.deleteFile("artworks/$fileName")
    }

    // Additional method
}

