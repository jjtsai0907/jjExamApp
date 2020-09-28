package com.example.examapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_shopping.*

class NationListRecycleAdapter(val context: Context, val nationList: List<NationClass>) :
    RecyclerView.Adapter<NationListRecycleAdapter.ViewHolder>() {

    val layoutInflater = LayoutInflater.from(context)


    override fun getItemCount () = nationList.size



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.list_nation, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nationInfo = nationList[position]

        holder.nationTextView.text = nationInfo.nation
        holder.nationPriceTextView.text = nationInfo.ticketFare.toString()
        //holder.nationImageView =
        holder.nationListPosition = position
        nationInfo.ticketFare

        if (!DataManager.nations[position].alphaInShoppingList){
            holder.itemView.isEnabled
            holder.itemView.alpha = 0.5F
            holder.nationTextView.text = "${DataManager.nations[position].nation} Purchased"



        }



        //DataManager.ClickedItemPosition[0] = position

        /*holder.itemView.setOnClickListener{
            Log.d("!!!", "item is clicked")
            //holder.itemView.isVisible = false

        }*/



    }


    fun remove (position: Int){
        DataManager.nations.removeAt(position)

    }

    /*
    fun removeStudent(position: Int){
        val dialogBuilder = AlertDialog.Builder(context)

        dialogBuilder.setTitle("Remove Student?")
            .setMessage("Do you wanna remove ${DataManager.students[position].name}?")
            .setPositiveButton("Remove", { dialog, which ->
                DataManager.students.removeAt(position)
                notifyDataChanged()
            })
            .setNegativeButton("Cancel", {dialog, which ->
                dialog.cancel()
            })

        val alert = dialogBuilder.create()
        alert.show()



    }


     */






    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val nationTextView = itemView.findViewById<TextView>(R.id.nationTextView)
        val nationPriceTextView = itemView.findViewById<TextView>(R.id.nationPriceTextView)
        //val nationImageView = itemView.findViewById<ImageView>(R.id.nationImageView)
        var nationListPosition = 0






        init{
            itemView.setOnClickListener {
                //Log.d("!!!", "first")
                nationTextView.text = "${DataManager.nations[nationListPosition].nation} Purchased"
                DataManager.nations[nationListPosition].shown = true
                //itemView.alpha = 0.2F
                DataManager.nations[nationListPosition].alphaInShoppingList = false



                notifyDataSetChanged()
                //itemView.isClickable = false
                //itemView.isEnabled = false
                //remove(nationListPosition)
                // set false    DataManager.nations[nationListPosition]




            }

        }

    }




}