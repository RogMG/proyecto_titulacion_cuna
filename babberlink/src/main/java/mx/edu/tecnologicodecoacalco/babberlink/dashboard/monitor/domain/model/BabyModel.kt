package mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.domain.model

data class BabyModel (
    val id: MutableList<BabyIdModel>,
    val model: MutableList<BabyMonitorDTO>,
    val babyName: MutableList<String>
    )