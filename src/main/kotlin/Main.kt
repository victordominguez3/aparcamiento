import Aparcamiento.aparcarCoche
import Aparcamiento.comprobarSitio
import Aparcamiento.imprimirListaClientes
import Aparcamiento.imprimirListaCoches
import Aparcamiento.imprimirListaCompleta
import Aparcamiento.imprimirParking
import Aparcamiento.parking
import Aparcamiento.recaudacion
import Aparcamiento.sacarCoche

fun main() {

    var resp: String
    val respRegex = Regex("[1-8]")

    do {
        imprimirParking(parking)
        println()
        println("\n1.\tVer lista de clientes y sus coches")
        println("2.\tVer lista de clientes")
        println("3.\tVer lista de coches")
        println("4.\tVer recaudación actual del parking")
        println("5.\tAparcar un coche")
        println("6.\tSacar un coche")
        println("7.\tComprobar datos de un sitio")
        println("8.\tSalir")

        print("\nSeleccione una acción: ")
        resp = readln()
        while (!respRegex.matches(resp)) {
            print("Seleccione una acción válida: ")
            resp = readln()
        }

        when (resp) {
            "1" -> imprimirListaCompleta()
            "2" -> imprimirListaClientes()
            "3" -> imprimirListaCoches()
            "4" -> println("\nSe han recaudado $recaudacion€")
            "5" -> aparcarCoche()
            "6" -> sacarCoche()
            "7" -> comprobarSitio()
        }

    } while (resp != "8")

    println("Cerrando programa...")

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