package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.domain.model.AdvicesModelDTO
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.presentation.view.AdvicesDescriptionActivity
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.AdvicesCardViewBinding

class AdvicesAdapter(
    private val dataList: MutableList<AdvicesModelDTO> = mutableListOf(),
    private val context: Context
    ): RecyclerView.Adapter<AdvicesAdapter.ViewHolder>(){

    fun addItems(list: MutableList<AdvicesModelDTO>){
        if(dataList.isEmpty()){
            dataList.addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdvicesCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: AdvicesCardViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val titleText: TextView = binding.titleTextView
        val descriptionText: TextView = binding.descriptionTextView
        val articleImage: ImageView = binding.articleImageView

        init{
            binding.root.setOnClickListener {
                AdvicesDescriptionActivity.launch(context, dataList[adapterPosition])
            }
        }


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(dataList[position]){
                titleText.text = title
                descriptionText.text = description
                setImage(articleImage, linkImage)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    private fun setImage (image: ImageView, url: String){
        var showUrl = url
        if(showUrl.isEmpty()){
            showUrl = "https://www.housingeurope.eu/image/163/sectionheaderpng/resourcesdef.png"
        }
        Picasso
            .get()
            .load(showUrl)
            .resize(100, 100)
            .centerCrop()
            .into(image);
    }

}