import factories.CochesFactory
import factories.CochesFactory.crearCoche
import factories.ConductoresFactory
import factories.ConductoresFactory.crearConductorNuevo
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
        var randI: Int
        var randJ: Int

        for (i in listaCoches) {
            do {
                randI = (0 until 5).random()
                randJ = (0 until 8).random()
                if (parking[randI][randJ] == null) {
                    parking[randI][randJ] = i
                    asignarPlaza(i, randI, randJ)
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
            when (cont) {
                1 -> { print(" \t $cont        ") }
                3, 5, 7 -> print(" $cont        ")
                else -> { print(" $cont  ") }
            }
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

    private fun crearListaClientesInicial(): Array<Conductor?> {
        return Array<Conductor?>(10) { ConductoresFactory.crearConductorInicial() }
    }

    private fun crearListaCoches(listaClientes: Array<Conductor?>): Array<Coche?> {

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

    fun imprimirLista(lista: Array<Any?>) {
        println()
        for (i in lista.indices) {
            println("${i+1}. ${lista[i]}")
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

    fun menuOrdenCoches() {
        var resp: String
        val respRegex = Regex("[1-5]")

        do {
            println("\n1. Ordenar por matrícula de menor a mayor")
            println("2. Ordenar por matrícula de mayor a menor")
            println("3. Ordenar por año de fabricación de menor a mayor")
            println("4. Ordenar por año de fabricación de mayor a menor")
            println("5. Volver atrás")

            print("\nSeleccione una acción: ")
            resp = readln()
            while (!respRegex.matches(resp)) {
                print("Seleccione una acción válida: ")
                resp = readln()
            }

            when (resp) {
                "1" -> imprimirLista(ordenarLista(1) as Array<Any?>)
                "2" -> imprimirLista(ordenarLista(1).reversedArray() as Array<Any?>)
                "3" -> imprimirLista(ordenarLista(2) as Array<Any?>)
                "4" -> imprimirLista(ordenarLista(2).reversedArray() as Array<Any?>)
            }
        } while (resp != "5")
    }

    fun menuDatosSitios() {

        var resp: String
        val respRegex = Regex("[1-4]")

        do {
            println("\n1. Ver datos de una plaza")
            println("2. Ver cuantas plazas hay disponibles")
            println("3. Ver cuantas plazas hay ocupadas")
            println("4. Volver atrás")

            print("\nSeleccione una acción: ")
            resp = readln()
            while (!respRegex.matches(resp)) {
                print("Seleccione una acción válida: ")
                resp = readln()
            }

            when (resp) {
                "1" -> comprobarSitio()
                "2" -> stringPlazasDisponibles(plazasDisponibles())
                "3" -> stringPlazasOcupadas(plazasOcupadas())
            }
        } while (resp != "4")
    }

    private fun ordenarLista(num: Int): Array<Coche?> {

        val listaOrdenada = listaCoches
        var min = 0
        var guardar: Coche?

        if (num == 1) {

            for (i in 0 until listaOrdenada.size - 1) {
                min = i
                for (j in i+1 until listaOrdenada.size) {
                    if (listaOrdenada[j]!!.matricula[4] < listaOrdenada[min]!!.matricula[4]) {
                        min = j
                    } else if (listaOrdenada[j]!!.matricula[4] == listaOrdenada[min]!!.matricula[4]) {
                        if (listaOrdenada[j]!!.matricula[5] < listaOrdenada[min]!!.matricula[5]) {
                            min = j
                        } else if (listaOrdenada[j]!!.matricula[5] == listaOrdenada[min]!!.matricula[5]) {
                            if (listaOrdenada[j]!!.matricula[6] < listaOrdenada[min]!!.matricula[6]) {
                                min = j
                            } else if (listaOrdenada[j]!!.matricula[6] == listaOrdenada[min]!!.matricula[6]) {
                                if (listaOrdenada[j]!!.matricula[0] < listaOrdenada[min]!!.matricula[0]) {
                                    min = j
                                } else if (listaOrdenada[j]!!.matricula[0] == listaOrdenada[min]!!.matricula[0]) {
                                    if (listaOrdenada[j]!!.matricula[1] < listaOrdenada[min]!!.matricula[1]) {
                                        min = j
                                    } else if (listaOrdenada[j]!!.matricula[1] == listaOrdenada[min]!!.matricula[1]) {
                                        if (listaOrdenada[j]!!.matricula[2] < listaOrdenada[min]!!.matricula[2]) {
                                            min = j
                                        } else if (listaOrdenada[j]!!.matricula[2] == listaOrdenada[min]!!.matricula[2]) {
                                            if (listaOrdenada[j]!!.matricula[3] < listaOrdenada[min]!!.matricula[3]) {
                                                min = j
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                guardar = listaOrdenada[i]
                listaOrdenada[i] = listaOrdenada[min]
                listaOrdenada[min] = guardar
            }
        }
        if (num == 2) {

            for (i in 0 until listaOrdenada.size - 1) {
                min = i
                for (j in i+1 until listaOrdenada.size) {
                    if (listaOrdenada[j]!!.anyoFabricacion < listaOrdenada[min]!!.anyoFabricacion) {
                        min = j
                    }
                }
                guardar = listaOrdenada[i]
                listaOrdenada[i] = listaOrdenada[min]
                listaOrdenada[min] = guardar
            }
        }

        return listaOrdenada
    }

    fun itemExiste(item: String): Boolean {

        if (item.length == 7) { // Siete caracteres indican que es una matrícula
            for (i in CochesFactory.matriculas) {
                if (item == i) return true
            }
        } else if (item.length == 9) { // Nueve caracteres indican que es un dni
            for (i in ConductoresFactory.dnis) {
                if (item == i) return true
            }
        }
        return false
    }

    fun agregarItem(item: String) {

        if (item.length == 7) { // Siete caracteres indican que es una matrícula
            val arrayConNuevoItem = Array<String>(CochesFactory.matriculas.size + 1) { "" }

            for (i in CochesFactory.matriculas.indices) {
                arrayConNuevoItem[i] = CochesFactory.matriculas[i]
            }
            arrayConNuevoItem[arrayConNuevoItem.size - 1] = item

            CochesFactory.matriculas = arrayConNuevoItem
        } else if (item.length == 9) { // Nueve caracteres indican que es un dni
            val arrayConNuevoItem = Array<String>(ConductoresFactory.dnis.size + 1) { "" }

            for (i in ConductoresFactory.dnis.indices) {
                arrayConNuevoItem[i] = ConductoresFactory.dnis[i]
            }
            arrayConNuevoItem[arrayConNuevoItem.size - 1] = item

            ConductoresFactory.dnis = arrayConNuevoItem
        }
    }

    private fun numCoches(listaClientes: Array<Conductor?>): Int {
        var coches = 0

        for (i in listaClientes) {
            coches += i!!.numCoches
        }

        return coches
    }

    fun menuAparcarCoche() {

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

        val conductor = crearConductorNuevo()

        agregarCliente(conductor)
        conductor.cochesEnParking[0] = crearCoche(conductor)

        aparcar(conductor)
    }

    private fun aparcar(conductor: Conductor) {

        var resp: String
        val tam = conductor.cochesEnParking.size

        do {
            resp = elegirSitio()

            if (!existeCoche(resp)) {
                parking[resp[0].code - 65][resp[1].code - 49] = conductor.cochesEnParking[tam - 1]
                agregarCoche(conductor.cochesEnParking[tam - 1])
            } else {
                println("\nYa hay un coche aparcado")
                resp = ""
            }
        } while (resp == "")

        conductor.cochesEnParking[tam - 1]!!.hacerSonarMotor()
        conductor.presentarse()
        println("Coche aparcado en $resp")
        conductor.cochesEnParking[tam - 1]!!.plaza = resp
    }

    private fun elegirSitio(): String {

        var resp: String
        val respRegex = Regex("[A-E][1-8]")

        print("\nSeleccione un sitio (Ejemplo -> A1, b2): ")
        resp = readln().uppercase()
        while (!respRegex.matches(resp)) {
            print("Seleccione un sitio válido: ")
            resp = readln().uppercase()
        }

        return resp
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

        for (i in conductor.cochesEnParking.indices) {
            arrayCocheNuevo[i] = conductor.cochesEnParking[i]
        }

        arrayCocheNuevo[arrayCocheNuevo.size - 1] = coche
        conductor.cochesEnParking = arrayCocheNuevo
        conductor.numCoches++

        aparcar(conductor)
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

        var cont = 0

        if (!parkingVacio()) {

            val coche = elegirCoche()
            val arrayCoches = Array<Coche?>(coche!!.propietario!!.numCoches - 1) {null}

            for (i in coche.propietario!!.cochesEnParking) {
                if (!i!!.equals(coche)) {
                    arrayCoches[cont] = i
                    cont++
                }
            }
            coche.propietario!!.cochesEnParking = arrayCoches
            coche.hacerSonarMotor()
            println("El coche ha sido sacado con éxito")
            coche.plaza = ""

            coche.propietario!!.numCoches--

            if (coche.propietario!!.numCoches == 0) {
                eliminarCliente(coche.propietario)
            }
            coche.propietario = null
            eliminarCoche(coche)
        } else println("\nEl parking esta vacío, no hay coches que sacar")
    }

    private fun elegirCoche(): Coche? {
        var coche: Coche? = null
        var resp: String

        do {
            resp = elegirSitio()

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

    private fun eliminarCoche(coche: Coche) {
        val array = Array<Coche?>(listaCoches.size - 1) {null}
        var cont = 0

        for (i in listaCoches) {
            if (!i!!.equals(coche)) {
                array[cont] = i
                cont++
            }
        }

        listaCoches = array
    }

    private fun eliminarCliente(cliente: Conductor?) {
        val array = Array<Conductor?>(listaClientes.size - 1) {null}
        var cont = 0

        for (i in listaClientes) {
            if (!i!!.equals(cliente)) {
                array[cont] = i
                cont++
            }
        }

        listaClientes = array
    }

    fun comprobarSitio() {

        val resp: String = elegirSitio()

        if (parking[resp[0].code-65][resp[1].code-49] is Coche) {
            println("\nLa plaza esta ocupada por -> ${parking[resp[0].code - 65][resp[1].code - 49]}")
            println("El dueño es -> ${parking[resp[0].code - 65][resp[1].code - 49]!!.propietario}")
        } else println("\nLa plaza está libre")
    }

    private fun plazasDisponibles(): Int {

        var plazas = 0

        for (i in parking.indices) {
            for (j in parking[i].indices) {
                if (parking[i][j] == null) {
                    plazas++
                }
            }
        }

        return plazas
    }

    private fun stringPlazasDisponibles(plazas: Int) {

        when (plazas) {
            0 -> println("\nNo hay plazas disponibles, disculpen las molestias")
            1 -> println("\nQueda una única plaza, igual la pillas jeje")
            else -> println("\nQuedan $plazas plazas disponibles")
        }
    }

    private fun plazasOcupadas(): Int {

        var plazas = 0

        for (i in parking.indices) {
            for (j in parking[i].indices) {
                if (parking[i][j] is Coche) {
                    plazas++
                }
            }
        }

        return plazas
    }

    private fun stringPlazasOcupadas(plazas: Int) {

        when (plazas) {
            0 -> println("\nNo hay ningún coche, el parking está vacío")
            1 -> println("\nHay una plaza ocupada")
            else -> println("\nHay $plazas plazas ocupadas")
        }
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

    private fun asignarPlaza(coche: Coche?, i: Int, j: Int) {

        val caracter = (i + 65).toChar()
        val num = (j + 49).toChar()

        coche!!.plaza = "$caracter$num"
    }
}