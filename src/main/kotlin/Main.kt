import factories.ConductoresFactory
import models.Coche

fun main() {

    val clientes = Aparcamiento.crearListaClientesInicial()

    for (i in clientes) {
        println(i)
        println(i.cochesEnPropiedad.contentDeepToString())
    }
}

//    aparcar -> si está lleno no
//    salir
//    donde aparcar? existe coche?
//    cuantos coches hay?
//    cuantos coches tiene un conductor en el parking?
//    Listado de vehículos ordenados por antiguedad
//    Recaudación
//    models.Coche -> id, matricula, año
//    Conductor -> id, nombre, dni
//    Aparcar -> 3.75