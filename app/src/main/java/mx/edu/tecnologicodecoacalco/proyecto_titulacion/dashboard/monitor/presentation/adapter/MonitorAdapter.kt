package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.R
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.model.BabyDTO

class MonitorAdapter() :
    RecyclerView.Adapter<MonitorAdapter.ViewHolder>() {

    private lateinit var babyModel: MutableList<BabyDTO>

    fun sendMonitorData(babyModel: MutableList<BabyDTO>){
        this.babyModel = babyModel
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.baby_name_edit_text)
        val lpmtext: TextView = view.findViewById(R.id.ltm_baby)
        val chartText: LineChart = view.findViewById(R.id.baby_monitor_chart)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.monitor_card_view, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.nameText.text = babyModel[position].babyName
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = babyModel.size

}
