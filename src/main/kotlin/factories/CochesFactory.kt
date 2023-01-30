package factories

import models.Coche
import models.Conductor

object CochesFactory {

    val nombre = "Fábrica de coches"
    val marcas = arrayOf(
        "Honda",
        "Ford",
        "Nissan",
        "Mitsubishi",
        "Volkswagen",
        "BMW",
        "Audi",
        "Peougeot",
        "Citroen",
        "Porsche",
        "Ferrari",
        "Volvo",
        "Kia",
        "Suzuki",
        "Fiat",
        "Skoda",
        "Dacia"
    )
    val anyosFabricacion = 1990..2022

    fun crearCoche(conductor: Conductor): Coche {
        return Coche(generarMatriculaRandom(), marcas.random(), anyosFabricacion.random(), conductor)
    }

    private fun generarMatriculaRandom(): String {

        var matricula = ""

        repeat(4) { matricula += (0..9).random() }
        repeat(3) { matricula += (65..90).random().toChar() }

        return matricula
    }
}