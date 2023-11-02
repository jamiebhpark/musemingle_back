package mingle.musemingle.controller

import mingle.musemingle.domain.Artwork
import mingle.musemingle.service.ArtworkService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/artworks")
class ArtworkController(private val artworkService: ArtworkService) {

    @GetMapping("/{id}")
    fun getArtwork(@PathVariable id: Int): ResponseEntity<Artwork> {
        val artwork = artworkService.findById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(artwork)
    }

    @GetMapping("/artist/{artistId}")
    fun getArtworksByArtist(@PathVariable artistId: Int): ResponseEntity<List<Artwork>> {
        val artworks = artworkService.findByArtistId(artistId)
        return ResponseEntity.ok(artworks)
    }

    @PostMapping
    fun createArtwork(
        @RequestPart("artwork") artwork: Artwork,
        @RequestPart("imageFile") imageFile: MultipartFile
    ): ResponseEntity<Artwork> {
        val savedArtwork = artworkService.save(artwork, imageFile)
        return ResponseEntity.ok(savedArtwork)
    }

    @DeleteMapping("/{id}")
    fun deleteArtwork(@PathVariable id: Int): ResponseEntity<Void> {
        artworkService.deleteById(id)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/{id}")
    fun updateArtwork(
        @PathVariable id: Int,
        @RequestPart("updatedArtwork") updatedArtwork: Artwork,
        @RequestPart("imageFile") imageFile: MultipartFile?
    ): ResponseEntity<Artwork> {
        return try {
            val artwork = artworkService.update(id, updatedArtwork, imageFile)
            ResponseEntity.ok(artwork)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.notFound().build()
        }
    }
}