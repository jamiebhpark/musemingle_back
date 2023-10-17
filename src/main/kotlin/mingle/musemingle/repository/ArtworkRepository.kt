package mingle.musemingle.repository

import mingle.musemingle.domain.Artwork
import org.springframework.data.jpa.repository.JpaRepository

interface ArtworkRepository : JpaRepository<Artwork, Int> {

    fun findByArtistId(artistId: Int): List<Artwork>

    // 추가적인 쿼리 메서드를 여기에 작성할 수 있습니다.
}