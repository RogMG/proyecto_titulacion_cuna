package mx.edu.tecnologicodecoacalco.proyecto_titulacion.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import androidx.viewpager2.widget.ViewPager2
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.R

class FragmentsConsejos : AppCompatActivity() {
    val view: ViewPager2 =findViewById(R.id.viewPager2)
    val tab: TableLayout =findViewById(R.id.tabLayout)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragments_consejos)

        view.adapter = PageAdapter(supportFragmentManager)
        tab.setupWithInicio_fragments(Inicio_fragments)
    }
}