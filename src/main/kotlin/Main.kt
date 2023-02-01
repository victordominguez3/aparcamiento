import Aparcamiento.menuAparcarCoche
import Aparcamiento.comprobarSitio
import Aparcamiento.imprimirLista
import Aparcamiento.imprimirListaCompleta
import Aparcamiento.imprimirParking
import Aparcamiento.listaClientes
import Aparcamiento.listaCoches
import Aparcamiento.menuOrdenCoches
import Aparcamiento.parking
import Aparcamiento.recaudacion
import Aparcamiento.sacarCoche

fun main() {

    var resp: String
    val respRegex = Regex("[1-9]")

    do {
        imprimirParking(parking)
        println()
        println("\n1.\tVer lista de clientes y sus coches")
        println("2.\tVer lista de clientes")
        println("3.\tVer lista de coches")
        println("4.\tVer lista de coches ordenada")
        println("5.\tVer recaudación actual del parking")
        println("6.\tAparcar un coche")
        println("7.\tSacar un coche")
        println("8.\tComprobar datos de un sitio")
        println("9.\tSalir")

        print("\nSeleccione una acción: ")
        resp = readln()
        while (!respRegex.matches(resp)) {
            print("Seleccione una acción válida: ")
            resp = readln()
        }

        when (resp) {
            "1" -> imprimirListaCompleta()
            "2" -> imprimirLista(listaClientes as Array<Any?>)
            "3" -> imprimirLista(listaCoches as Array<Any?>)
            "4" -> menuOrdenCoches()
            "5" -> println("\nSe han recaudado $recaudacion€")
            "6" -> menuAparcarCoche()
            "7" -> sacarCoche()
            "8" -> comprobarSitio()
        }

    } while (resp != "9")

    println("\nCerrando programa...")

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