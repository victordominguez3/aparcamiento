import factories.CochesFactory
import factories.ConductoresFactory
import models.Coche
import models.Conductor

object Aparcamiento {

    var recaudacion = 0

    fun crearParking(listaCoches: Array<Coche?>): Array<Array<Coche?>> {

        val parking = Array(5) { Array<Coche?>(8) { null } }
        var randI = -1
        var randJ = -1

        for (i in listaCoches) {
            do {
                randI = (0 until 5).random()
                randJ = (0 until 8).random()
                if (parking[randI][randJ] == null) {
                    parking[randI][randJ] = i
                } else randI = -1
            } while (randI == -1)
        }

        return parking
    }

    fun imprimirParking(parking: Array<Array<Coche?>>) {

        var char = 'A'

        for (i in 0 until parking[0].size) {
            print(" 1 \t")
        }

        println()

        for (i in parking.indices) {
            print("$char\t")
            char++
            for (j in parking[i].indices) {
                if (parking[i][j] is Coche) {
                    print("[C]\t")
                } else print("[ ]\t")
            }
            println()

        }

    }

    fun crearListaClientesInicial(): Array<Conductor> {
        return Array<Conductor>(10) { ConductoresFactory.crearConductor() }
    }

    fun crearListaCoches(listaClientes: Array<Conductor>): Array<Coche?> {

        val listaCoches = Array<Coche?>(numCoches(listaClientes)) { null }
        var cont = 0

        for (i in listaClientes.indices) {
            for (j in listaClientes[i].cochesEnPropiedad) {
                listaCoches[cont] = j
                cont++
            }
        }

        return listaCoches
    }

    fun imprimirListaClientes(lista: Array<Conductor>) {

        for (i in lista) {
            println(i)
            for (j in i.cochesEnPropiedad) {
                println("\t$j")
            }
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

    fun numCoches(listaClientes: Array<Conductor>): Int {
        var coches = 0

        for (i in listaClientes) {
            coches += i.numCoches
        }

        return coches
    }

}