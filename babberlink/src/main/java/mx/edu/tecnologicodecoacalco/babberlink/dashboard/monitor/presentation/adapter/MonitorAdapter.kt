package mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.domain.model.BabyDTO
import mx.edu.tecnologicodecoacalco.babberlink.databinding.MonitorCardViewBinding

class MonitorAdapter(
    private val babyModel: MutableList<BabyDTO> = mutableListOf()
) : RecyclerView.Adapter<MonitorAdapter.ViewHolder>() {

    fun replaceBabyData(babyModel: MutableList<BabyDTO>){
        this.babyModel.clear()
        this.babyModel.addAll(babyModel)
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: MonitorCardViewBinding) : RecyclerView.ViewHolder(binding.root) {

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

    }

    override fun getItemCount() = babyModel.size



}

