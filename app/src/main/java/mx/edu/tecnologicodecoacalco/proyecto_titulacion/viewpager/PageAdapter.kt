package mx.edu.tecnologicodecoacalco.proyecto_titulacion.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PageAdapter(fm:FragmentManager):FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position){
            0->{return Consejo1()}
            1->{return Consejo2()}
            2->{return Consejo3()}
        else->{return Consejo1()}
        }
    }
    override fun getCount(): Int {
        return 3
    }
}