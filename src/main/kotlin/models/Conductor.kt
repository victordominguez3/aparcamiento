package models

import java.util.*

class Conductor(
    val nombre: String,
    val apellido: String,
    val dni: String,
    var numCoches: Int
) {
    val id: UUID = UUID.randomUUID()
    var cochesEnParking = Array<Coche?>(numCoches) {null}

    override fun toString(): String {
        return "Persona -> id: $id, nombre: $nombre $apellido, dni: $dni, número de coches: $numCoches"
    }

    fun presentarse() {
        println("Soy $nombre $apellido, con dni: $dni")
    }
}