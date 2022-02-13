package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.presentation.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.R
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.domain.model.AdvicesModelDTO
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.model.BabyDTO
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.presentation.view.UpdateBabyInfoActivity
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.ActivityAdvicesDescriptionBinding

class AdvicesDescriptionActivity : AppCompatActivity() {

    companion object {

        private const val ADVICES_MODEL_KEY = "advicesModelKey"

        fun launch(context: Context, model: AdvicesModelDTO){
            val intent = Intent(context, AdvicesDescriptionActivity::class.java)
            intent.putExtra(ADVICES_MODEL_KEY, model)
            context.startActivity(intent)
        }

    }

    private val binding by lazy{
        ActivityAdvicesDescriptionBinding.inflate(layoutInflater)
    }

    private lateinit var articleImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var linkImageTextView: TextView

    private lateinit var modelReceived: AdvicesModelDTO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val intent = intent.extras
        modelReceived = intent?.getSerializable(ADVICES_MODEL_KEY) as AdvicesModelDTO
        articleImageView = binding.articleAdvicesDescriptionImageView
        titleTextView = binding.titleAdvicesTextView
        descriptionTextView = binding.contentAdvicesTextView
        linkImageTextView = binding.linkAdvicesTextView
        Picasso.get().load(modelReceived.linkImage).into(articleImageView)
        titleTextView.text = modelReceived.title
        descriptionTextView.text = modelReceived.content
        linkImageTextView.setTextColor(getColor(R.color.purple_200))
        linkImageTextView.setOnClickListener{
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(modelReceived.linkArticle))
                startActivity(browserIntent)
        }

    }

}
