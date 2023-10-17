package mingle.musemingle.repository

import mingle.musemingle.domain.Gallery
import org.springframework.data.jpa.repository.JpaRepository

interface GalleryRepository : JpaRepository<Gallery, Int> {

    fun findByUserId(userId: Int): Gallery?
}
