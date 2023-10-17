package mingle.musemingle.service

import mingle.musemingle.domain.Gallery
import mingle.musemingle.repository.GalleryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GalleryService(private val galleryRepository: GalleryRepository) {

    @Transactional(readOnly = true)
    fun findById(id: Int): Gallery? = galleryRepository.findById(id).orElse(null)

    @Transactional(readOnly = true)
    fun findByUserId(userId: Int) = galleryRepository.findByUserId(userId)

    @Transactional
    fun save(gallery: Gallery) = galleryRepository.save(gallery)

    @Transactional
    fun deleteById(id: Int) = galleryRepository.deleteById(id)

    @Transactional
    fun update(id: Int, updatedGallery: Gallery): Gallery {
        val gallery = galleryRepository.findById(id).orElseThrow { IllegalArgumentException("No Gallery with given ID found") }

        gallery.name = updatedGallery.name
        gallery.city = updatedGallery.city
        gallery.state = updatedGallery.state
        gallery.country = updatedGallery.country
        gallery.zipCode = updatedGallery.zipCode
        gallery.isVerified = updatedGallery.isVerified
        gallery.website = updatedGallery.website
        if (updatedGallery.verificationDocument != null && updatedGallery.verificationDocument != gallery.verificationDocument) {
            gallery.verificationDocument = updatedGallery.verificationDocument
        }

        return galleryRepository.save(gallery)
    }
}
