package com.example.therickandmortybook.data.model.charcter

import com.example.therickandmortybook.data.dataBaseLocal.model.DataModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("species")
    val species: String? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("gender")
    val gender: String? = null,
    @SerialName("origin")
    val origin: OriginDto? = null,
    @SerialName("location")
    val location: LocationDto? = null,
    @SerialName("image")
    val image: String? = null,
    @SerialName("episode")
    val episode: List<String?>? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("created")
    val created: String? = null,
    val isFavorite: Boolean = false
) {
    companion object {

        fun ResultDto.toDataModel(
            isFavorite: Boolean = false
        ): DataModel {
            return DataModel(
                id = this.id ?: 0,
                name = this.name,
                status = this.status,
                image = this.image,
                isFavorite = isFavorite
            )
        }
    }
}