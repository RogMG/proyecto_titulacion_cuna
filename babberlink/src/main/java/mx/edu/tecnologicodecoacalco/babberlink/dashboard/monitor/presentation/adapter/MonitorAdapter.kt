package mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.presentation.adapter

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.squareup.picasso.Picasso
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection.domain.model.UserModelDTO

import mx.edu.tecnologicodecoacalco.babberlink.databinding.MonitorCardViewBinding
import mx.edu.tecnologicodecoacalco.babberlink.utils.GenericDialogFragment

class MonitorAdapter(
    private val babyModel: MutableList<UserModelDTO> = mutableListOf(),
    private val babyUri: MutableList<Uri> = mutableListOf(),
    private val context: Context
) : RecyclerView.Adapter<MonitorAdapter.ViewHolder>() {

    fun replaceBabyData(babyModel: MutableList<UserModelDTO>){
        this.babyModel.clear()
        this.babyModel.addAll(babyModel)
        notifyDataSetChanged()
    }

    fun recieveUserData(list: UserModelDTO){
        babyModel.add(list)
        notifyDataSetChanged()
    }

    fun receiveimages(list: Uri){
        babyUri.add(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: MonitorCardViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.profileImageViewMonitorCard
        val name = binding.nombreMonitorCardViewBabberLink
        val celular = binding.celularMonitorCardViewBabberLink
        val correo = binding.emailTextViewMonitorDisplay
        init {
            binding.root.setOnClickListener {
                AlertDialog.Builder(context)
                    .setTitle("Confirmacion de Llamada")
                    .setMessage("¿Quieres llamar a ${babyModel[adapterPosition].nombre}")
                    .setPositiveButton("Llamar"){ i,e ->
                        checkPermission(babyModel[adapterPosition].celular)
                    }
                    .setNegativeButton("Cerrar"){ i,e ->
                        i.dismiss()
                    }.create().show()
            }
            binding.unsubscribeImageButtonMonitor.setOnClickListener {
                deleteAccount(babyModel[adapterPosition].userToken)
            }
        }

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
        with(viewHolder){
            name.text = babyModel[position].nombre
            celular.text = babyModel[position].celular
            correo.text = babyModel[position].correo
            if(babyUri.isNotEmpty()){
                if(babyUri[position] != Uri.EMPTY){
                    Picasso.get().load(babyUri[position]).into(image)
                }
            }

        }
    }

    fun checkPermission(number: String){
        if (ContextCompat.checkSelfPermission(
                context, Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_DENIED){
            Toast.makeText(context, "No podemos hacer la llamada porque no haz concedido permisos, hazlo manualmente", Toast.LENGTH_LONG).show()
        }else{
            startCall(context, number)
        }
    }

    fun startCall(context: Context,number: String? = ""){
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:$number")
        context.startActivity(callIntent)
    }

    fun deleteAccount(token: String){
        FirebaseMessaging.getInstance().unsubscribeFromTopic(token).addOnSuccessListener {
            Firebase.auth.currentUser?.email?.let { it1 ->
                Firebase.firestore.collection("linkUsers")
                    .document(it1).update("dispositivosVinculados", FieldValue.arrayRemove(token))
                    .addOnSuccessListener {
                        Toast.makeText(context, "Listo, ya no recibirá notificaciones de este usuario", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "No pudimos dar de baja la suscripción, intentelo mas tarde.", Toast.LENGTH_LONG).show()
                    }
            }
        }.addOnFailureListener {
            Toast.makeText(context, "Sucedio un error, intentelo mas tarde", Toast.LENGTH_LONG).show()
        }


    }

    override fun getItemCount() = babyModel.size



}

