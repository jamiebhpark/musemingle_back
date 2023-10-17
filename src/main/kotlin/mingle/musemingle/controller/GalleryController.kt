package mingle.musemingle.controller

import mingle.musemingle.domain.Gallery
import mingle.musemingle.service.GalleryService
import mingle.musemingle.service.S3Service
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/galleries")
class GalleryController(private val galleryService: GalleryService, private val s3Service: S3Service) {

    @GetMapping("/{id}")
    fun getGallery(@PathVariable id: Int): ResponseEntity<Gallery> {
        val gallery = galleryService.findById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(gallery)
    }

    @GetMapping("/user/{userId}")
    fun getGalleryByUser(@PathVariable userId: Int): ResponseEntity<Gallery> {
        val gallery = galleryService.findByUserId(userId) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(gallery)
    }

    @PostMapping
    fun createGallery(@RequestBody gallery: Gallery): ResponseEntity<Gallery> {
        val savedGallery = galleryService.save(gallery)
        return ResponseEntity.ok(savedGallery)
    }

    @DeleteMapping("/{id}")
    fun deleteGallery(@PathVariable id: Int): ResponseEntity<Void> {
        galleryService.deleteById(id)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/{id}")
    fun updateGallery(@PathVariable id: Int, @RequestBody updatedGallery: Gallery): ResponseEntity<Gallery> {
        return try {
            val gallery = galleryService.update(id, updatedGallery)
            ResponseEntity.ok(gallery)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/{id}/upload-document")
    fun uploadVerificationDocument(@PathVariable id: Int, @RequestParam file: MultipartFile): ResponseEntity<Gallery> {
        val gallery = galleryService.findById(id) ?: return ResponseEntity.notFound().build()

        val url = s3Service.uploadFile("galleries", file.bytes, file.originalFilename ?: "document")
        gallery.verificationDocument = url
        galleryService.save(gallery)

        return ResponseEntity.ok(gallery)
    }

    @DeleteMapping("/{id}/delete-document")
    fun deleteVerificationDocument(@PathVariable id: Int): ResponseEntity<Void> {
        val gallery = galleryService.findById(id) ?: return ResponseEntity.notFound().build()

        if (gallery.verificationDocument != null) {
            s3Service.deleteFile(gallery.verificationDocument!!)
            gallery.verificationDocument = null
            galleryService.save(gallery)
        }
        return ResponseEntity.noContent().build()
    }
}
