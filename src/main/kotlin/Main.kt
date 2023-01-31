import factories.CochesFactory
import factories.ConductoresFactory
import models.Coche

const val PRECIO_PARKING = 3.75

fun main() {

    var listaClientes = Aparcamiento.crearListaClientesInicial()

    Aparcamiento.imprimirListaClientes(listaClientes)

    println(CochesFactory.matriculas.contentToString())
    println(ConductoresFactory.dnis.contentToString())

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