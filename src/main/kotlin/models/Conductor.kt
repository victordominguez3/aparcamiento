package models

import java.util.*

class Conductor(
    val nombre: String,
    val apellido: String,
    val dni: String,
    val numCoches: Int
) {
    val id: UUID = UUID.randomUUID()
    val cochesEnPropiedad = Array<Coche?>(numCoches) {null}

    override fun toString(): String {
        return "Persona -> id: $id, nombre: $nombre $apellido, dni: $dni"
    }

    fun presentarse() {
        println("Soy $nombre $apellido, con dni: $dni")
    }
}