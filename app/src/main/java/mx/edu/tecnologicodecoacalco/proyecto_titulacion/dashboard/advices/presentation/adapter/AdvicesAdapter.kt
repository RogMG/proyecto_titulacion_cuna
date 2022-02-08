package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.domain.model.AdvicesModelDTO
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.AdvicesCardViewBinding

class AdvicesAdapter(private val dataList: MutableList<AdvicesModelDTO>): RecyclerView.Adapter<AdvicesAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdvicesCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    class ViewHolder(val binding: AdvicesCardViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val titleText: TextView = binding.titleTextView
        val descriptionText: TextView = binding.descriptionTextView
        val articleImage: ImageView = binding.articleImageView

    }

    override fun onBindViewHolder(holder: AdvicesAdapter.ViewHolder, position: Int) {
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