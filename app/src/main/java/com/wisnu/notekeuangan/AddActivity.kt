package com.wisnu.notekeuangan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.wisnu.notekeuangan.model.Note
import com.wisnu.notekeuangan.utils.NoteDao
import com.wisnu.notekeuangan.utils.NoteRoomDb
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    val EDIT_NOTE_EXTRA = "edit_note_extra"
    private lateinit var note: Note
    private var isUpdate = false
    private lateinit var database: NoteRoomDb
    private lateinit var dao: NoteDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        database = NoteRoomDb.getDatabase(applicationContext)
        dao = database.getNoteDao()

        if (intent.getParcelableExtra<Note>(EDIT_NOTE_EXTRA) != null){
            button_delete.visibility = View.VISIBLE
            isUpdate = true
            note = intent.getParcelableExtra(EDIT_NOTE_EXTRA)!!
            edit_text_title.setText(note.title)
            edit_text_body.setText(note.body)

            edit_text_title.setSelection(note.title.length)

        }

        button_save.setOnClickListener {
            val title = edit_text_title.text.toString()
            val body = edit_text_body.text.toString()
            val body2 = edit_text_body2.text.toString()
            val body3 = edit_text_body3.text.toString()
            val body4 = edit_text_body4.text.toString()

            if (title.isEmpty() && body.isEmpty()&& body4.isEmpty()&& body3.isEmpty()&& body2.isEmpty()){
                Toast.makeText(applicationContext, "Note cannot be empty", Toast.LENGTH_SHORT).show()
            }
            else{
                if (isUpdate){
                    saveNote(Note(id = note.id, title = title, body = body,jumlah = body2.toInt(),tanggal = body3,nomor = body4.toInt()))
                }
                else{
                    saveNote(Note(title = title, body = body,jumlah = body2.toInt(),tanggal = body3,nomor = body4.toInt()))
                }
            }

            finish()
        }

        button_delete.setOnClickListener {
            deleteNote(note)
            finish()
        }

    }

    private fun saveNote(note: Note){

        if (dao.getById(note.id).isEmpty()){

            dao.insert(note)
        }
        else{

            dao.update(note)
        }

        Toast.makeText(applicationContext, "Note saved", Toast.LENGTH_SHORT).show()

    }

    private fun deleteNote(note: Note){
        dao.delete(note)
        Toast.makeText(applicationContext, "Note removed", Toast.LENGTH_SHORT).show()
    }
}
