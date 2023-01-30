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

}