package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.R
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.model.BabyDTO

class MonitorAdapter(private val babyModel: MutableList<BabyDTO>, private var babyData: MutableList<Entry> ) :
    RecyclerView.Adapter<MonitorAdapter.ViewHolder>() {

    private var babyChart: LineData

    init {
        babyChart = LineData(LineDataSet(babyData, "MonitorBaby"))
    }

    fun sendLpmData(list: MutableList<Entry>) {
        babyChart =  LineData(LineDataSet(list, "MonitorBaby"))
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.baby_name_edit_text)
        val lpmtext: TextView = view.findViewById(R.id.ltm_baby)
        val chartText: LineChart = view.findViewById(R.id.baby_monitor_chart)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.monitor_card_view, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.nameText.text = babyModel[position].babyName
        viewHolder.chartText.xAxis.labelCount = 10
        viewHolder.chartText.axisRight.isEnabled = false
        viewHolder.chartText.xAxis.isEnabled = false
        viewHolder.chartText.data = babyChart
        viewHolder.chartText.invalidate()


    }

    override fun getItemCount() = babyModel.size

}

