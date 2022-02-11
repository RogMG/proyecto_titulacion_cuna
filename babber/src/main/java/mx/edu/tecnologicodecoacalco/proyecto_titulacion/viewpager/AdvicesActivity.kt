package mx.edu.tecnologicodecoacalco.proyecto_titulacion.viewpager

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.ActivityAdvicesBinding

class AdvicesActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAdvicesBinding.inflate(layoutInflater)
    }

    private val view: ViewPager2 by lazy {
        binding.viewPager2
    }

    private val tab: TabLayout by lazy {
        binding.tabLayout
    }

    companion object {
        fun launch(context: Activity){
            val intent = Intent(context, AdvicesActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        view.adapter = PageAdapter(lifecycle, supportFragmentManager)
        TabLayoutMediator(tab, view) {tab, position ->

        }.attach()

    }
}