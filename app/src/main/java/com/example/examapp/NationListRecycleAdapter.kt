package com.example.examapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
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
        holder.nationTicket.setImageResource(nationInfo.nationTicket)
        holder.nationPostcard.alpha = 0.0F



        if (DataManager.nations[position].purchased){


            if (DataManager.nations[position].front_side){
                val out: Animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.slide_out)
                holder.nationTicket.startAnimation(out)
                holder.itemView.isEnabled = false

                DataManager.nations[position].front_side = false
                //holder.nationImageView.alpha = 0.0F

                holder.nationPostcard.alpha = 1.0F
                val inside: Animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.slide_in)
                holder.nationPostcard.startAnimation(inside)
                //holder.itemView.alpha = 0.2F

            }
            else{
                holder.itemView.isEnabled = false
                holder.itemView.alpha = 0.8F
                holder.nationTicket.alpha = 0.0F
                holder.nationPostcard.alpha = 1.0F

            }

        }
        else {
            holder.itemView.isEnabled = true
            holder.itemView.alpha = 1F
            holder.nationPostcard.alpha = 0.0F
            holder.nationTicket.alpha = 1.0F
            holder.nationTextView.text = "${DataManager.nations[position].nation}"
        }

        DataManager.nations[position].purchased
    }




    inner class ViewHolder(itemView: View, myOnItemClickListener: ShoppingActivity): RecyclerView.ViewHolder(itemView){

        val nationTextView = itemView.findViewById<TextView>(R.id.nationTextView)
        val nationPriceTextView = itemView.findViewById<TextView>(R.id.nationPriceTextView)
        val nationTicket = itemView.findViewById<ImageView>(R.id.nationTicket)
        val nationPostcard = itemView.findViewById<ImageView>(R.id.nationPostcard)

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