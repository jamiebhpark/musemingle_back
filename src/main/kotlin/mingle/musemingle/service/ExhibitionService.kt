package mingle.musemingle.service

import mingle.musemingle.domain.Exhibition
import mingle.musemingle.repository.ExhibitionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile  // MultipartFile 임포트 추가

@Service
class ExhibitionService(
    private val exhibitionRepository: ExhibitionRepository,
    private val s3Service: S3Service  // 여기에 S3Service를 주입받습니다.
) {
    @Transactional(readOnly = true)
    fun findById(id: Int): Exhibition? = exhibitionRepository.findById(id).orElse(null)

    @Transactional(readOnly = true)
    fun findByGalleryId(galleryId: Int) = exhibitionRepository.findByGalleryId(galleryId)

    @Transactional
    fun save(exhibition: Exhibition, posterImage: MultipartFile?): Exhibition {
        posterImage?.let {
            val imageUrl = s3Service.uploadFile("exhibitions", it.bytes, it.originalFilename!!)  // 여기를 수정했습니다.
            exhibition.posterImage = imageUrl
        }
        return exhibitionRepository.save(exhibition)
    }

    @Transactional
    fun deleteById(id: Int) {
        val exhibition = findById(id) ?: throw IllegalArgumentException("No Exhibition with given ID found")
        exhibition.posterImage?.let {
            s3Service.deleteFile(it)
        }
        exhibitionRepository.deleteById(id)
    }

    @Transactional
    fun update(id: Int, updatedExhibition: Exhibition, posterImage: MultipartFile?): Exhibition {
        val exhibition = exhibitionRepository.findById(id)
            .orElseThrow { IllegalArgumentException("No Exhibition with given ID found") }

        // 기존의 포스터 이미지가 있다면 삭제
        exhibition.posterImage?.let {
            s3Service.deleteFile(it)
        }

        // 새로운 포스터 이미지가 있다면 업로드
        posterImage?.let {
            val imageUrl = s3Service.uploadFile("exhibitions", it.bytes, it.originalFilename!!)  // 여기를 수정했습니다.
            exhibition.posterImage = imageUrl
        }

        // 다른 필드 업데이트...

        return exhibitionRepository.save(exhibition)
    }
}
