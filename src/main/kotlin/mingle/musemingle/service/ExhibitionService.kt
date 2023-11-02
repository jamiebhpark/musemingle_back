package mingle.musemingle.service

import mingle.musemingle.domain.Exhibition
import mingle.musemingle.repository.ExhibitionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class ExhibitionService(
    private val exhibitionRepository: ExhibitionRepository,
    private val s3Service: S3Service
) {
    @Transactional(readOnly = true)
    fun findById(id: Int): Exhibition? = exhibitionRepository.findById(id).orElse(null)

    @Transactional(readOnly = true)
    fun findByGalleryId(galleryId: Int) = exhibitionRepository.findByGalleryId(galleryId)

    @Transactional
    fun save(exhibition: Exhibition, posterImage: MultipartFile?): Exhibition {
        posterImage?.let {
            val imageUrl = s3Service.uploadFile("exhibitions", it.bytes, it.originalFilename!!)
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

        exhibition.posterImage?.let {
            s3Service.deleteFile(it)
        }

        posterImage?.let {
            val imageUrl = s3Service.uploadFile("exhibitions", it.bytes, it.originalFilename!!)
            exhibition.posterImage = imageUrl
        }


        return exhibitionRepository.save(exhibition)
    }
}
