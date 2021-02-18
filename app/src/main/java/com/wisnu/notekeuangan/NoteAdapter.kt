package com.wisnu.notekeuangan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wisnu.notekeuangan.model.Note

class NoteAdapter(
    private val listItems: ArrayList<Note>,
    private val listener: NoteListener
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = listItems[position]
        holder.textViewTitle.text = item.title
        holder.textViewTitle2.text = "RP . "+item.jumlah.toString()
        holder.textViewTitle3.text = item.tanggal
        holder.textViewTitle4.text = item.nomor.toString()
        holder.textViewBody.text = item.body
        holder.itemView.setOnClickListener {
            listener.OnItemClicked(item)
        }
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle = itemView.findViewById<TextView>(R.id.text_view_title)
        var textViewTitle2 = itemView.findViewById<TextView>(R.id.text_view_title2)
        var textViewTitle3 = itemView.findViewById<TextView>(R.id.text_view_title3)
        var textViewTitle4 = itemView.findViewById<TextView>(R.id.text_view_title4)
        var textViewBody = itemView.findViewById<TextView>(R.id.text_view_body)
    }

    interface NoteListener{
        fun OnItemClicked(note: Note)
    }
}

