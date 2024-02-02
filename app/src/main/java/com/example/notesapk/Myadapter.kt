package com.example.notesapk

import android.content.Context
import android.content.Intent
import android.provider.Settings.Global
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class Myadapter(
    private var context: Context,
    private val roomdata:List<Row>,
    private val  chkdata:Int,
    private var chkif:Checkboxif
):RecyclerView.Adapter<Myadapter.Myviewholder>() {

//    var itemclick:((Row)->Unit)?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myviewholder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.notesrowlayout,parent,false)
        return Myviewholder(itemView)
    }

    override fun getItemCount(): Int {
        return roomdata.size
    }

    override fun onBindViewHolder(holder: Myviewholder, position: Int) {
        val curroomdata=roomdata[position]
        holder.txtview.text=curroomdata.notetitle
        holder.dateview.text=curroomdata.notetitle
        if(chkdata==1){
            holder.chkbox.visibility=View.VISIBLE
        }
        else{
            holder.chkbox.visibility=View.GONE
        }
        holder.chkbox.setOnClickListener{
            chkif.setid(holder.chkbox.isChecked,curroomdata.notedid)
        }
        holder.itemView.setOnClickListener {
            println("Nooo")
            if(chkdata==1) checkbox(holder,curroomdata.notedid)
            else {
                val intent = Intent(context, NewPageActivity::class.java)
                intent.putExtra("key", curroomdata.notedid)
                context.startActivity(intent)
            }
//            Toast.makeText(context,"this your id:${curroomdata.notedid}",Toast.LENGTH_SHORT).show()
        }

    }
    private fun checkbox(holder:Myviewholder,x:Int){
        if(holder.chkbox.isChecked){
            holder.chkbox.isChecked=false
        }
        else{
//            MainActivity().chklist.add(x)
//            checklist.setinto(x)
            holder.chkbox.isChecked=true
        }
        chkif.setid(holder.chkbox.isChecked,x)
    }
    class Myviewholder(itemView: View) :RecyclerView.ViewHolder(itemView){
//        constructor(itemView: View) : super(itemView)
        var txtview:TextView=itemView.findViewById(R.id.rowlaytitle)
        var dateview:TextView=itemView.findViewById(R.id.rowlaydate)
        var chkbox:CheckBox=itemView.findViewById((R.id.checkBox))
    }


}