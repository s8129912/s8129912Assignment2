package com.example.s8129912assignment2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EntityAdapter(private val onItemClick: (Entity) -> Unit) :
    RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {

    private var list: List<Entity> = emptyList()

    fun submitList(newList: List<Entity>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_entity, parent, false)
        return EntityViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val item = list[position]
        holder.property1Tv.text = item.name ?: "N/A"
        holder.property2Tv.text = item.type ?: "N/A"
        holder.itemView.setOnClickListener { onItemClick(item) }
    }

    override fun getItemCount(): Int = list.size

    class EntityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val property1Tv: TextView = view.findViewById(R.id.textProperty1)
        val property2Tv: TextView = view.findViewById(R.id.textProperty2)
    }
}
