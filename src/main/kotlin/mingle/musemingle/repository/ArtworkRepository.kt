package mingle.musemingle.repository

import mingle.musemingle.domain.Artwork
import org.springframework.data.jpa.repository.JpaRepository

interface ArtworkRepository : JpaRepository<Artwork, Int> {

    fun findByArtistId(artistId: Int): List<Artwork>
}