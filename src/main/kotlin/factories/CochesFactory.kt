package factories

import Aparcamiento
import Aparcamiento.itemExiste
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
        "Peugeot",
        "Citroen",
        "Porsche",
        "Ferrari",
        "Volvo",
        "Kia",
        "Suzuki",
        "Fiat",
        "Skoda",
        "Dacia",
        "Twingo"
    )
    val anyosFabricacion = 1990..2022
    var matriculas = Array<String> (0) { "" }

    fun crearCoche(conductor: Conductor): Coche {
        return Coche(generarMatriculaRandom(), marcas.random(), anyosFabricacion.random(), conductor)
    }

    private fun generarMatriculaRandom(): String {

        var matricula = ""

        do {
            repeat(4) { matricula += (0..9).random() }
            repeat(3) { matricula += (65..90).random().toChar() }
        } while (itemExiste(matricula))

        Aparcamiento.agregarItem(matricula)

        return matricula
    }


}