package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.model

data class BabyModel (
    val id: MutableList<BabyIdModel>,
    val model: MutableList<BabyMonitorDTO>
    )