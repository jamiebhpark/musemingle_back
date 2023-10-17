package mingle.musemingle.domain

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import jakarta.persistence.*

@Entity
@Table(name = "artworks")
data class Artwork(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column(name = "artist_id")
    val artistId: Int,

    @Column(name = "title")
    var title: String,

    @Column(name = "description")
    var description: String?,

    @Column(name = "creation_date")
    var creationDate: LocalDate?,

    @Column(name = "price")
    var price: BigDecimal?,

    @Column(name = "image")
    var image: String?,

    @Column(name = "is_featured")
    var isFeatured: Boolean?,

    @Column(name = "category_name")
    var categoryName: String?,

    @Column(name = "created_at")
    val createdAt: LocalDateTime,

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime

)
