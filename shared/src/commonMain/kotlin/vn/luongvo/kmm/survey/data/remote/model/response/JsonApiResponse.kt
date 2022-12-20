package vn.luongvo.kmm.survey.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class Response<T>(
    var data: Resource<T>,
    var errors: List<ResponseError>? = null,
    var included: List<Resource<T>>? = null,
    var links: Map<String, String>? = null,
    var meta: JsonObject? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Response<*>

        if (data != other.data) return false
        if (errors != other.errors) return false
        if (included != other.included) return false
        if (links != other.links) return false
        if (meta != other.meta) return false

        return true
    }

    override fun hashCode(): Int {
        var result = data?.hashCode() ?: 0
        result = 31 * result + (errors?.hashCode() ?: 0)
        result = 31 * result + (included?.hashCode() ?: 0)
        result = 31 * result + (links?.hashCode() ?: 0)
        result = 31 * result + (meta?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Response(data=$data, included=$included, links=$links, meta=$meta)"
    }

    fun getIncludedOrNull(link: ResourceLink): Resource<T>? {
        return included?.firstOrNull { it.id == link.id && it.type == link.type }
    }

    fun getIncluded(link: ResourceLink): Resource<T> {
        return getIncludedOrNull(link)
            ?: throw NoSuchElementException("Response contains no included element matching the link.")
    }
}

@Serializable
data class Resource<T>(
    val id: String,
    val type: String,
    val attributes: T,
    val relationships: Map<String, Relationship>? = null,
    val links: Map<String, String>? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Resource<*>

        if (id != other.id) return false
        if (type != other.type) return false
        if (attributes != other.attributes) return false
        if (relationships != other.relationships) return false
        if (links != other.links) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + (attributes?.hashCode() ?: 0)
        result = 31 * result + (relationships?.hashCode() ?: 0)
        result = 31 * result + (links?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Resource(id='$id', type='$type', attributes=$attributes, relationships=$relationships)"
    }
}

@Serializable
data class Relationship(@SerialName("data") val links: List<ResourceLink>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Relationship

        if (links != other.links) return false

        return true
    }

    override fun hashCode(): Int {
        return links.hashCode()
    }
}

@Serializable
data class ResourceLink(val id: String, val type: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ResourceLink

        if (id != other.id) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }
}

@Serializable
data class ResponseError(
    val id: String? = null,
    val links: List<ResourceLink>,
    val status: String? = null,
    val code: String? = null,
    val title: String? = null,
    val detail: String? = null,
    val source: Source? = null,
    val meta: JsonObject? = null
)

@Serializable
data class Source(val pointer: String, val parameter: String)
