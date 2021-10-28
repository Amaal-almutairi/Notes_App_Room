package com.example.notes_app_room

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes_app_room.databinding.ListRowBinding


class myadap (val activity:MainActivity,val Notes:List<Notes>):RecyclerView.Adapter<myadap.ItemViewHolder>(){

    class ItemViewHolder(val binding:ListRowBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
       (ListRowBinding.inflate(LayoutInflater.from(parent.context),parent
           ,false)))

    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val NOTE = Notes[position]
        holder.binding.apply {
          textv.text=NOTE.Note
            imgbrvEdit.setOnClickListener {
                activity.openwendow(NOTE)



            }

            imgbrvdelete.setOnClickListener {
                activity.delete(NOTE)



            }

        }
    }

    override fun getItemCount() = Notes.size

}


