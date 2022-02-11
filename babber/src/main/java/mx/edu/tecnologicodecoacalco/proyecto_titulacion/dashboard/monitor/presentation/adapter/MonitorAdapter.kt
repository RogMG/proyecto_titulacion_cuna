package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.model.BabyDTO
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.MonitorCardViewBinding

class MonitorAdapter(
    private val babyModel: MutableList<BabyDTO> = mutableListOf()
) : RecyclerView.Adapter<MonitorAdapter.ViewHolder>() {

    private var babyChart = mutableListOf<LineData>()

    fun sendLpmData(list: MutableList<MutableList<Entry>>) {
        for(position in 0 until list.size){
            babyChart[position] = LineData(LineDataSet(list[position], "Latidos Por Minuto"))
        }
        notifyDataSetChanged()
    }

    fun sendBabyData(babyModel: MutableList<BabyDTO>){
        if(this.babyModel.isEmpty()){
            this.babyModel.addAll(babyModel)
        }
        for(baby in 0 until this.babyModel.size){
            val entry = this.babyModel[baby].monitor
            babyChart.add(
                LineData(
                    LineDataSet(
                        mutableListOf(Entry(0F, entry.toFloat())), "Latidos Por Minuto"
                    )
                )
            )
        }
        notifyDataSetChanged()
    }

    class ViewHolder(binding: MonitorCardViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val nameText: TextView = binding.babyNameEditText
        val fatherLastName: TextView = binding.fatherLastNameMonitor
        val motherLastName: TextView = binding.motherLastNameMonitor
        val age: TextView = binding.ageLastNameMonitor
        val sex: TextView = binding.sexLastNameMonitor
        val weight: TextView = binding.weigthMonitorBaby
        val lpmtext: TextView = binding.ltmBaby
        val chartText: LineChart = binding.babyMonitorChart
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = MonitorCardViewBinding.inflate(
            LayoutInflater
                .from(viewGroup.context)
            , viewGroup,
            false)


        return ViewHolder(binding)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        with(viewHolder){
            nameText.text = babyModel[position].nombre
            fatherLastName.text = babyModel[position].apellidoPaterno
            motherLastName.text = babyModel[position].apellidoMaterno
            age.text = babyModel[position].edad
            sex.text = babyModel[position].sexo
            weight.text = babyModel[position].peso
            lpmtext.text = babyModel[position].monitor
            chartText.xAxis.labelCount = 10
            chartText.axisRight.isEnabled = false
            chartText.xAxis.isEnabled = false
            chartText.data = babyChart[position]
            chartText.invalidate()
        }
    }

    override fun getItemCount() = babyModel.size



}

