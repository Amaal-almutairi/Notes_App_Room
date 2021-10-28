package com.example.notes_app_room

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var addNote: EditText
    lateinit var btnsub: Button
    lateinit var Notes: List<Notes>
    lateinit var myadap: RecyclerView
    var note = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addNote = findViewById(R.id.edtext)
        btnsub = findViewById(R.id.b1)
        myadap = findViewById(R.id.rvmain)
        Notes = arrayListOf()
        updtRC()




         // will add and save notes
        btnsub.setOnClickListener {
            var note = addNote.text.toString()
            var N = Notes(0,note)
            CoroutineScope(IO).launch {
                NotesDatabase.getinstance(applicationContext).NotesDao().Insertnote(N)
            }
            Toast.makeText(this, "Data Saved Successfully", Toast.LENGTH_SHORT).show()
            addNote.text.clear()
            addNote.clearFocus()
            updtRC()



        }


    }
// update recycler view
    fun updtRC(){
        Notes= NotesDatabase.getinstance(applicationContext).NotesDao().getallnotes()
        myadap.adapter = myadap(this,Notes)
        myadap.layoutManager = LinearLayoutManager(this)
        myadap.adapter?.notifyDataSetChanged()

    }


    fun update(id:Int,newnote:String){
        NotesDatabase.getinstance(applicationContext).NotesDao().updatenote(Notes(id,newnote))
        updtRC()

    }
    fun delete(note:Notes){
        NotesDatabase.getinstance(applicationContext).NotesDao().deletenote(note)
        updtRC()


    }

    fun openwendow(oldnote: Notes){
        val dialog= AlertDialog.Builder(this)
        val newNote=EditText(this)
        val id = oldnote.id
        newNote.hint="Enter new text"
        dialog.setCancelable(false).setPositiveButton("Save", DialogInterface.OnClickListener {
                _, i -> update(id,newNote.text.toString())})

        dialog.setNegativeButton("Cancel", DialogInterface.OnClickListener { _, i ->  })
        // the alert will create if we press the edit icon the function edit will cal the updatNote function
        // in database so we can make change in database
        val Alert = dialog.create()
        Alert.setTitle("Update Note")
        // setView will show the value in edittext inside alert
        Alert.setView(newNote)
        Alert.show()


    }


}