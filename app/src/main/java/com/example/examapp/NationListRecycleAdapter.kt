package com.example.examapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class NationListRecycleAdapter(val context: Context, val nationList: List<NationClass> ,private val myOnItemClickListener: ShoppingActivity) :
    RecyclerView.Adapter<NationListRecycleAdapter.ViewHolder>() {

    val layoutInflater = LayoutInflater.from(context)


    override fun getItemCount () = nationList.size



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.list_nation, parent, false)
        return ViewHolder(itemView, myOnItemClickListener)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nationInfo = nationList[position]

        holder.nationTextView.text = nationInfo.nation
        holder.nationPriceTextView.text = nationInfo.ticketFare.toString()
        holder.nationImageView.setImageResource(nationInfo.imageResource)


        if (DataManager.nations[position].purchased){
            holder.itemView.isEnabled = false
            holder.itemView.alpha = 0.2F
        }
        else {
            holder.itemView.isEnabled = true
            holder.itemView.alpha = 1F
            holder.nationTextView.text = "${DataManager.nations[position].nation}"
        }

        DataManager.nations[position].purchased
    }




    inner class ViewHolder(itemView: View, myOnItemClickListener: ShoppingActivity): RecyclerView.ViewHolder(itemView){

        val nationTextView = itemView.findViewById<TextView>(R.id.nationTextView)
        val nationPriceTextView = itemView.findViewById<TextView>(R.id.nationPriceTextView)
        val nationImageView = itemView.findViewById<ImageView>(R.id.nationImageView)

        init{
            itemView.setOnClickListener  {
                myOnItemClickListener.onClick(adapterPosition)
                notifyDataSetChanged()
            }

            itemView.setOnLongClickListener{
                myOnItemClickListener.onLongClick(adapterPosition)
                return@setOnLongClickListener true
            }
        }
    }


    // Used to control RecyclerView from Shopping Activity.
    // Why? So that the prices of tickets and wallet are calculable to each other.
    interface OnItemClickListener {
        fun onClick (int: Int)
        fun onLongClick (int: Int)
    }
}