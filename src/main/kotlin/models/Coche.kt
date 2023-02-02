package models

import java.util.*

class Coche (
    val matricula: String,
    val marca: String,
    val anyoFabricacion: Int,
    var propietario: Conductor?
) {
    val id = UUID.randomUUID()
    var plaza = ""

    override fun toString(): String {
        return "Coche -> id: $id, matricula: $matricula, marca: $marca, año de fabricación: $anyoFabricacion, plaza: $plaza, dueño: $propietario"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Coche) return false
        return this.id == other.id && this.matricula == other.matricula && this.marca == other.marca && this.anyoFabricacion == other.anyoFabricacion
    }

    override fun hashCode(): Int {
        var res = anyoFabricacion

        res = 31 * res + matricula.hashCode()
        res = 31 * res + marca.hashCode()
        res = 31 * res + anyoFabricacion.hashCode()

        return res
    }

    fun hacerSonarMotor() {
        println("\nCoche $marca con matricula $matricula hace BRUUUUM BRUUUUM")
    }
}