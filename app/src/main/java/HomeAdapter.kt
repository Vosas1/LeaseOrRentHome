package com.example.victorosaikhuwuomwan_comp304_lab2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeAdapter(
    private val homesList: List<HomeData>,
    private val onHomeSelected: (HomeData, Boolean) -> Unit
) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private val selectedHomes = mutableSetOf<HomeData>()

    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageViewHome)
        val descriptionView: TextView = view.findViewById(R.id.textViewHomeDescription)
        val checkBox: CheckBox = view.findViewById(R.id.checkBoxSelectHome)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.home_item, parent, false)
        return HomeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val home = homesList[position]
        holder.imageView.setImageResource(home.imageResIds[0])
        holder.descriptionView.text = "${home.address}, ${home.price}"
        holder.checkBox.isChecked = selectedHomes.contains(home)

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedHomes.add(home)
            } else {
                selectedHomes.remove(home)
            }
            onHomeSelected(home, isChecked)
        }
    }

    override fun getItemCount() = homesList.size

    fun getSelectedHomes(): List<HomeData> = selectedHomes.toList()
}
