import factories.CochesFactory
import factories.CochesFactory.crearCoche
import factories.ConductoresFactory
import factories.ConductoresFactory.apellidos
import factories.ConductoresFactory.generarDniRandom
import factories.ConductoresFactory.nombres
import models.Coche
import models.Conductor

const val PRECIO_PARKING = 3.75

object Aparcamiento {

    var listaClientes = crearListaClientesInicial()
    var listaCoches = crearListaCoches(listaClientes)
    val parking = crearParking(listaCoches)
    var recaudacion = 0.0

    private fun crearParking(listaCoches: Array<Coche?>): Array<Array<Coche?>> {

        val parking = Array(5) { Array<Coche?>(8) { null } }
        var randI = -1
        var randJ = -1

        for (i in listaCoches) {
            do {
                randI = (0 until 5).random()
                randJ = (0 until 8).random()
                if (parking[randI][randJ] == null) {
                    parking[randI][randJ] = i
                    recaudacion += PRECIO_PARKING
                } else randI = -1
            } while (randI == -1)
        }

        return parking
    }

    fun imprimirParking(parking: Array<Array<Coche?>>) {

        var char = 'A'
        var cont = 1

        println("\n-----------------   P  A  R  K  I  N  G   -----------------")
        println()

        for (i in 0 until parking[0].size) {
            if (cont == 1) { print(" \t $cont        ") }
            else if (cont == 3 || cont == 5 || cont == 7) print(" $cont        ")
            else { print(" $cont  ") }
            cont++
        }

        println()

        for (i in parking.indices) {
            print("$char\t")
            char++
            for (j in parking[i].indices) {
                if (parking[i][j] is Coche) {
                    if ((i == 1 || i == 3) && (j == 0 || j == 2 || j == 4 || j == 6)) {
                        print("[■] ↑ | ↓ ")
                    } else if (j == 1 || j == 3 || j == 5 || j == 7) {
                        print("[■] ")
                    } else print("[■]   |   ")
                } else {
                    if ((i == 1 || i == 3) && (j == 0 || j == 2 || j == 4 || j == 6)) {
                        print("[ ] ↑ | ↓ ")
                    } else if (j == 1 || j == 3 || j == 5 || j == 7) {
                        print("[ ] ")
                    } else print("[ ]   |   ")
                }
            }
            println()
        }
    }

    fun crearListaClientesInicial(): Array<Conductor?> {
        return Array<Conductor?>(10) { ConductoresFactory.crearConductor() }
    }

    fun crearListaCoches(listaClientes: Array<Conductor?>): Array<Coche?> {

        val listaCoches = Array<Coche?>(numCoches(listaClientes)) { null }
        var cont = 0

        for (i in listaClientes.indices) {
            for (j in listaClientes[i]!!.cochesEnParking) {
                listaCoches[cont] = j
                cont++
            }
        }

        return listaCoches
    }

    fun imprimirListaClientes() {
        println()
        for (i in listaClientes.indices) {
            println("${i+1}. ${listaClientes[i]}")
        }
    }

    fun imprimirListaCompleta() {
        println()
        for (i in listaClientes) {
            println(i)
            for (j in i!!.cochesEnParking) {
                println("\t$j")
            }
        }

    }

    fun imprimirListaCoches() {
        println()
        for (i in listaCoches.indices) {
            println("${i+1}. ${listaCoches[i]}")
        }
    }

    fun itemExiste(item: String): Boolean {

        if (item.length == 7) {
            for (i in CochesFactory.matriculas) {
                if (item == i) return true
            }
        } else if (item.length == 9) {
            for (i in ConductoresFactory.dnis) {
                if (item == i) return true
            }
        }
        return false
    }

    fun agregarItem(item: String) {

        if (item.length == 7) {
            val arrayConNuevoItem = Array<String>(CochesFactory.matriculas.size + 1) { "" }

            for (i in CochesFactory.matriculas.indices) {
                arrayConNuevoItem[i] = CochesFactory.matriculas[i]
            }
            arrayConNuevoItem[arrayConNuevoItem.size - 1] = item

            CochesFactory.matriculas = arrayConNuevoItem
        } else if (item.length == 9) {
            val arrayConNuevoItem = Array<String>(ConductoresFactory.dnis.size + 1) { "" }

            for (i in ConductoresFactory.dnis.indices) {
                arrayConNuevoItem[i] = ConductoresFactory.dnis[i]
            }
            arrayConNuevoItem[arrayConNuevoItem.size - 1] = item

            ConductoresFactory.dnis = arrayConNuevoItem
        }
    }

    fun numCoches(listaClientes: Array<Conductor?>): Int {
        var coches = 0

        for (i in listaClientes) {
            coches += i!!.numCoches
        }

        return coches
    }

    fun aparcarCoche() {

        var resp: String
        val respRegex = Regex("[1-3]")

        if (!parkingLleno()) {
            println("\n1. Nuevo cliente")
            println("2. Nuevo coche de un cliente existente")
            println("3. Volver atrás")

            print("\nSeleccione una acción: ")
            resp = readln()
            while (!respRegex.matches(resp)) {
                print("Seleccione una acción válida: ")
                resp = readln()
            }

            when (resp) {
                "1" -> aparcaNuevoCliente()
                "2" -> aparcaOtroCoche()
            }
        } else println("\nEl parking está lleno, espere a que salga algún coche")
    }

    private fun aparcaNuevoCliente() {

        val conductor = Conductor(nombres.random(), apellidos.random(), generarDniRandom(), 1)

        agregarCliente(conductor)

        conductor.cochesEnParking[0] = crearCoche(conductor)

        elegirSitio(conductor.cochesEnParking[0])
    }

    private fun elegirSitio(coche: Coche?) {
        var resp: String
        val respRegex = Regex("[A-E][1-8]")

        do {
            print("\nSeleccione el sitio en el que desea aparcar el coche (Ejemplo -> A1, B2): ")
            resp = readln().uppercase()
            while (!respRegex.matches(resp)) {
                print("Seleccione un sitio válido: ")
                resp = readln().uppercase()
            }
            if (!existeCoche(resp)) {
                parking[resp[0].code-65][resp[1].code-49] = coche
                agregarCoche(coche)
            } else {
                println("\nYa hay un coche aparcado")
                resp = ""
            }

        } while (resp == "")

        coche?.hacerSonarMotor()
        println("\nCoche aparcado en $resp")
    }

    private fun existeCoche(sitio: String): Boolean {
        if (parking[sitio[0].code-65][sitio[1].code-49] is Coche) {
            return true
        }
        return false
    }

    private fun agregarCliente(conductor: Conductor) {
        val listaNuevoCliente = Array<Conductor?>(listaClientes.size + 1) { null }

        for (i in listaClientes.indices) {
            listaNuevoCliente[i] = listaClientes[i]
        }
        listaNuevoCliente[listaNuevoCliente.size - 1] = conductor

        listaClientes = listaNuevoCliente
    }

    private fun agregarCoche(coche: Coche?) {
        val listaNuevoCoche = Array<Coche?>(listaCoches.size + 1) { null }

        for (i in listaCoches.indices) {
            listaNuevoCoche[i] = listaCoches[i]
        }
        listaNuevoCoche[listaNuevoCoche.size - 1] = coche

        listaCoches = listaNuevoCoche

        recaudacion += PRECIO_PARKING
    }

    private fun aparcaOtroCoche() {
        val conductor = elegirCliente()
        val coche = crearCoche(conductor)
        val arrayCocheNuevo = Array<Coche?>(conductor!!.numCoches + 1) {null}

        if (!parkingLleno()) {
            for (i in conductor.cochesEnParking.indices) {
                arrayCocheNuevo[i] = conductor.cochesEnParking[i]
            }

            arrayCocheNuevo[arrayCocheNuevo.size - 1] = coche
            conductor.cochesEnParking = arrayCocheNuevo
            elegirSitio(coche)
            conductor.numCoches++
        } else println("\nEl parking está lleno, espere a que salga algún coche")
    }

    private fun elegirCliente(): Conductor? {

        val arrayClientesSeleccionados = clientesConMenosTresCoches()

        for (i in arrayClientesSeleccionados.indices) {
            println("${i+1} -> ${arrayClientesSeleccionados[i]}")
        }

        var resp: String
        val respRegex = Regex("[1-${arrayClientesSeleccionados.size}]")

        print("\nEscoja un cliente de la lista: ")
        resp = readln()
        while (!respRegex.matches(resp)) {
            print("Escoja un cliente válido: ")
            resp = readln()
        }

        return arrayClientesSeleccionados[resp.toInt() - 1]
    }

    private fun clientesConMenosTresCoches(): Array<Conductor?> {

        var cont = 0

        for (i in listaClientes) {
            if (i!!.numCoches < 3) cont++
        }

        val array = Array<Conductor?>(cont) {null}

        cont = 0

        for (i in listaClientes) {
            if (i!!.numCoches < 3) {
                array[cont] = i
                cont++
            }
        }

        return array
    }

    fun sacarCoche() {
        val coche = elegirCoche()
        val arrayCoches = Array<Coche?>(coche!!.propietario!!.numCoches - 1) {null}
        var cont = 0

        if (!parkingVacio()) {
            for (i in coche.propietario!!.cochesEnParking) {
                if (i != coche) {
                    arrayCoches[cont] = i
                    cont++
                }
            }
            coche.propietario!!.cochesEnParking = arrayCoches
            coche.hacerSonarMotor()
            println("\nEl coche ha sido sacado con éxito")

            if (coche.propietario!!.numCoches == 0) {
                eliminarCliente(coche.propietario)
            }
            coche.propietario!!.numCoches--
            coche.propietario = null
        } else println("\nEl parking esta vacío, no hay coches que sacar")
    }

    private fun elegirCoche(): Coche? {
        var coche: Coche? = null
        var resp: String
        val respRegex = Regex("[A-E][1-8]")

        do {
            print("\nSeleccione el coche que desea sacar (Ejemplo -> A1, B2): ")
            resp = readln()
            while (!respRegex.matches(resp)) {
                print("Seleccione un sitio válido: ")
                resp = readln()
            }
            if (existeCoche(resp)) {
                coche = parking[resp[0].code-65][resp[1].code-49]
                parking[resp[0].code-65][resp[1].code-49] = null

            } else {
                println("\nNo existe ningún coche en el sitio seleccionado")
                resp = ""
            }

        } while (resp == "")

        return coche
    }

    private fun eliminarCliente(cliente: Conductor?) {
        val array = Array<Conductor?>(listaClientes.size - 1) {null}
        var cont = 0

        for (i in listaClientes) {
            if (i != cliente) {
                array[cont] = i
                cont++
            }
        }

        listaClientes = array
    }

    fun comprobarSitio() {
        var resp: String
        val respRegex = Regex("[A-E][1-8]")

        print("\nSeleccione el sitio que desea comprobar (Ejemplo -> A1, B2): ")
        resp = readln()
        while (!respRegex.matches(resp)) {
            print("Seleccione un sitio válido: ")
            resp = readln()
        }

        if (parking[resp[0].code-65][resp[1].code-49] is Coche) {
            println("\nLa plaza esta ocupada por -> ${parking[resp[0].code - 65][resp[1].code - 49]}")
            println("El dueño es -> ${parking[resp[0].code - 65][resp[1].code - 49]!!.propietario}")
        } else println("\nLa plaza está libre")
    }

    private fun parkingLleno(): Boolean {

        for (i in parking.indices) {
            for (j in parking[i].indices) {
                if (parking[i][j] == null) return false
            }
        }
        return true
    }

    private fun parkingVacio(): Boolean {

        for (i in parking.indices) {
            for (j in parking[i].indices) {
                if (parking[i][j] != null) return false
            }
        }
        return true
    }
}


//PROYECTO PROGRAMACION FUNCIONAL

//Hacer clase fraccion y clase complejo y clase vector en 3D, arrays, in -> ALGEBRA

    //fracciones: igualdad, suma, resta, multiplicacion, division
    //complejo: suma resta conjugado multiplicacion division
    //vector: suma, resta
    //arrays: suma de arrays, resta de arrays
    //in(contains)

//infix de operadores

//Funcion llamada ordenar que acepte el predicado de ordenacion

//Imprimir y recorrer una matriz, encontrar un elemento en la matriz

//personas.map -> que te saque una lista de los nombres de un array de personas

//sobre un array de enteros: implementar forEach, find, filter, map, max, min, contar, sumar

//sobre una cadena: palindormo(boolean)

//sobre un numero entero: primo(boolean)












