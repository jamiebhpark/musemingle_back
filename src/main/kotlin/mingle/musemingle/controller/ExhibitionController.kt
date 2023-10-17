package mingle.musemingle.controller

import mingle.musemingle.domain.Exhibition
import mingle.musemingle.service.ExhibitionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/exhibitions")
class ExhibitionController(private val exhibitionService: ExhibitionService) {

    @GetMapping("/{id}")
    fun getExhibition(@PathVariable id: Int): ResponseEntity<Exhibition> {
        val exhibition = exhibitionService.findById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(exhibition)
    }

    @GetMapping("/gallery/{galleryId}")
    fun getExhibitionsByGallery(@PathVariable galleryId: Int): ResponseEntity<List<Exhibition>> {
        val exhibitions = exhibitionService.findByGalleryId(galleryId)
        return ResponseEntity.ok(exhibitions)
    }

    @PostMapping
    fun createExhibition(
        @RequestPart("exhibition") exhibition: Exhibition,
        @RequestPart("posterImage") posterImage: MultipartFile?
    ): ResponseEntity<Exhibition> {
        val savedExhibition = exhibitionService.save(exhibition, posterImage)
        return ResponseEntity.ok(savedExhibition)
    }

    @DeleteMapping("/{id}")
    fun deleteExhibition(@PathVariable id: Int): ResponseEntity<Void> {
        exhibitionService.deleteById(id)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/{id}")
    fun updateExhibition(
        @PathVariable id: Int,
        @RequestPart("updatedExhibition") updatedExhibition: Exhibition,
        @RequestPart("posterImage") posterImage: MultipartFile?
    ): ResponseEntity<Exhibition> {
        return try {
            val exhibition = exhibitionService.update(id, updatedExhibition, posterImage)
            ResponseEntity.ok(exhibition)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.notFound().build()
        }
    }
}
