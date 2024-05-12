package com.example.to_doapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val data: List<CardInfo>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val priority: TextView = itemView.findViewById(R.id.priority)
        val myLayout: View = itemView.findViewById(R.id.mylayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context: Context = holder.itemView.context

        // Set background color based on priority
        when (data[position].priority.toLowerCase()) {
            "high" -> holder.myLayout.setBackgroundColor(Color.parseColor("#1e40af"))
            "medium" -> holder.myLayout.setBackgroundColor(Color.parseColor("#86198f"))
            else -> holder.myLayout.setBackgroundColor(Color.parseColor("#450a0a")) // Default to white
        }

        holder.title.text = data[position].title
        holder.priority.text = data[position].priority

        // Handle item click
        holder.itemView.setOnClickListener {
            val intent = Intent(context, Update_cardActivity::class.java)
            intent.putExtra("id", position)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}