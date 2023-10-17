package mingle.musemingle.domain

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "exhibitions")
data class Exhibition(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column(name = "gallery_id")
    val galleryId: Int,

    @Column(name = "title")
    var title: String,

    @Column(name = "description")
    val description: String?,

    @Column(name = "start_date")
    val startDate: LocalDate?,

    @Column(name = "end_date")
    val endDate: LocalDate?,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    val status: ExhibitionStatus?,

    @Column(name = "poster_image")
    var posterImage: String?,

    @Column(name = "created_at")
    val createdAt: LocalDateTime,

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime
)

enum class ExhibitionStatus {
    UPCOMING, ONGOING, ENDED
}

