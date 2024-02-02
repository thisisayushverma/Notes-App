package com.example.notesapk


import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notesapk.NoteDb.Companion.getdatabase
import com.example.notesapk.databinding.ActivityNewPageBinding


class NewPageActivity : AppCompatActivity() {
    private lateinit var npvbinding:ActivityNewPageBinding
    private lateinit var db:NoteDb
    private var pageid:Int=-1
    private var chkbold:Boolean=false;
    private var chkitalic:Boolean=false;
    private var chkunderline:Boolean=false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        npvbinding=ActivityNewPageBinding.inflate(layoutInflater)
        setContentView(npvbinding.root)
        db= getdatabase(this)
        val dbdao=db.pack()

        val value=intent.getIntExtra("key",-1)
        if(value!=-1){
            val rw:Row=dbdao.getpartdata(value)
            npvbinding.titletxt.setText(rw.notetitle)
            npvbinding.contenttxt.setText(rw.notecontent)
            pageid=value
        }
        npvbinding.submitimg.setOnClickListener{
            if(pageid!=-1){  // this line check that if already saved or not if yesss then its time to update otherwise it will insert
                dbdao.updatedata(pageid,npvbinding.titletxt.text.toString(),npvbinding.contenttxt.text.toString())
            }
            else {
                if (npvbinding.contenttxt.text.toString() != "") {
                    //                GlobalScope.launch {
                    dbdao.insert(
                        row = Row(
                            notetitle = npvbinding.titletxt.text.toString(),
                            notecontent = npvbinding.contenttxt.text.toString()
                        )
                    )
                    pageid=dbdao.getlatdata()
                    //                }
                    Toast.makeText(this, "Data inserted", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "not Inseted", Toast.LENGTH_LONG).show()
                }
            }
        }
        npvbinding.backimg.setOnClickListener{
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        npvbinding.idbold.setOnClickListener{
            if(chkbold==false){
                chkbold=true
                npvbinding.contenttxt.setTypeface(npvbinding.contenttxt.typeface,Typeface.BOLD)
                print(npvbinding.contenttxt.typeface)

            }
            else{
                chkbold=false
                npvbinding.contenttxt.setTypeface(npvbinding.contenttxt.typeface,Typeface.NORMAL)
                print(npvbinding.contenttxt.typeface)
            }
            Toast.makeText(this,"bold works",Toast.LENGTH_SHORT).show()
        }

    }


}