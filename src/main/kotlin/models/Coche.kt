package models

import java.util.*

class Coche (
    val matricula: String,
    val marca: String,
    val anyoFabricacion: Int,
    val propietario: Conductor
) {
    val id = UUID.randomUUID()

    override fun toString(): String {
        return "Coche -> id: $id, matricula: $matricula, marca: $marca, año de fabricación: $anyoFabricacion"
    }

    fun hacerSonarMotor() {
        println("Coche con matricula $matricula hace BRUUUUM BRUUUUM")
    }
}