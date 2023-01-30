package factories

import models.Coche
import models.Conductor

object ConductoresFactory {

    val nombre = "Fábrica de conductores"

    val nombres = arrayOf(
        "Lucas",
        "Sonia",
        "Pedro",
        "Jose Miguel",
        "Rebeca",
        "Natalia",
        "Lucía",
        "Fernando",
        "Víctor",
        "Juan",
        "Hernán",
        "Nuria",
        "Pablo",
        "Rosa",
        "Pedro"
    )
    val apellidos = arrayOf(
        "Pérez",
        "Martín",
        "Dominguez",
        "González",
        "Paz",
        "Maestre",
        "Poza",
        "Gulán",
        "Díaz",
        "Solis",
        "Fuente",
        "Gómez",
        "Mirlo",
        "Quintana",
        "Pérez"
    )
    val rangoCoches = 1..3 // Cuando creamos un conductor, puede tener de 1 a 3 coches

    fun crearConductor(): Conductor {
        val conductor = Conductor(nombres.random(), apellidos.random(), generarDniRandom(), rangoCoches.random())
        for (i in 0 until conductor.cochesEnPropiedad.size) {
            conductor.cochesEnPropiedad[i] = CochesFactory.crearCoche(conductor)
        }
        return conductor
    }

    private fun generarDniRandom(): String {

        var dni = ""

        repeat(8) { dni += (1..9).random().toString() }
        dni += (65..90).random().toChar()

        return dni
    }
}