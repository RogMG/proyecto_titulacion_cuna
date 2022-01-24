package mx.edu.tecnologicodecoacalco.proyecto_titulacion.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class PageAdapter(
    lifeCycle: Lifecycle,
    fragmentManager:FragmentManager
):FragmentStateAdapter(fragmentManager, lifeCycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when (position){
            0->{return Consejo1()}
            1->{return Consejo2()}
            2->{return Consejo3()}
            else->{return Consejo1()}
        }
    }
}