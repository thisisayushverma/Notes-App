package com.example.notesapk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapk.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),Checkboxif {
    private lateinit var mavbinding:ActivityMainBinding
    private lateinit var db:NoteDb
    private lateinit  var rows:List<Row>
    private var chkbox:Int=0
    private var deletelist:ArrayList<Int> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mavbinding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(mavbinding.root)
        if(chkbox==0){
            mavbinding.trashicon.visibility=View.GONE
        }
        getroomdata()
    }

    private fun getroomdata() {  // this function is basically used for the show the data form the room and the passing the intent to new page
        db= NoteDb.getdatabase(this)
        val dao=db.pack()
//        var noteadapter:Myadapter

        mavbinding.notesrecylcer.layoutManager=LinearLayoutManager(this)
//        val launch = GlobalScope.launch {
            rows = dao.getalldata()
//            noteadapter = Myadapter(rows)
            mavbinding.notesrecylcer.adapter = Myadapter(this,rows,chkbox,this@MainActivity)
//        }
        mavbinding.newpageimg.setOnClickListener{
            val intent = Intent(this, NewPageActivity::class.java)
            startActivity(intent)
        }

        mavbinding.edtiBtn.setOnClickListener{
            if(chkbox==0){
                chkbox=1
                mavbinding.edtiBtn.text="Cancel"
                mavbinding.trashicon.visibility= View.VISIBLE
                mavbinding.newpageimg.visibility=View.GONE
                mavbinding.newpagetxt.visibility=View.GONE
            }
            else{
                chkbox=0
                mavbinding.edtiBtn.text="Edit"
                mavbinding.trashicon.visibility= View.GONE
                mavbinding.newpageimg.visibility=View.VISIBLE
                mavbinding.newpagetxt.visibility=View.VISIBLE
            }
            val row1=dao.getalldata()
            mavbinding.notesrecylcer.adapter = Myadapter(this,row1,chkbox,this@MainActivity)
        }

        mavbinding.trashicon.setOnClickListener{
            for( i in deletelist){
                dao.delnote(i)
            }
            val row1=dao.getalldata()
            mavbinding.notesrecylcer.adapter = Myadapter(this,row1,chkbox,this@MainActivity)
        }



    }

    override fun setid(ischecked: Boolean, value: Int) {
//        TODO("Not yet implemented")
        if(ischecked){
            deletelist.add(value)
        }
        else deletelist.remove(value)

        for(i in deletelist) print(i)

    }



}