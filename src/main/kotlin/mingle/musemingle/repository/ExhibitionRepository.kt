package mingle.musemingle.repository

import mingle.musemingle.domain.Exhibition
import org.springframework.data.jpa.repository.JpaRepository

interface ExhibitionRepository : JpaRepository<Exhibition, Int> {

    fun findByGalleryId(galleryId: Int): List<Exhibition>
}
