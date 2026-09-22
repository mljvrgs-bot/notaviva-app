package com.example.notaviva.data

/**
 * Estados posibles de un caso periodístico.
 * Se guarda en BD como [name] y se muestra al usuario con [label].
 */
enum class CaseStatus(val label: String) {
    EN_INVESTIGACION("En investigación"),
    PUBLICADO("Publicado"),
    CERRADO("Cerrado");

    companion object {
        fun fromName(name: String?): CaseStatus =
            values().firstOrNull { it.name == name } ?: EN_INVESTIGACION
    }
}
