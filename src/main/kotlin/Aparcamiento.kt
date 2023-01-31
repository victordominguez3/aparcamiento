import factories.CochesFactory
import factories.ConductoresFactory
import models.Coche
import models.Conductor

object Aparcamiento {

    fun crearParking(): Array<Array<Coche?>> {
        return Array(5) { Array(8) { null } }
    }

    fun crearListaClientesInicial(): Array<Conductor> {
        return Array(10) { ConductoresFactory.crearConductor() }
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

}