package com.example.examapp

import android.app.Activity
import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import kotlinx.android.synthetic.main.enter_name_dialog.*
import kotlinx.android.synthetic.main.list_nation.*

class EnterNameDialog(var activity: Activity) {

    lateinit var enterNameDialog: AlertDialog
    lateinit var nameButton: ImageButton

    lateinit var a: String

    fun startEnterNameDialog() {
        var builder = AlertDialog.Builder(activity)


        val enterNameInflator: LayoutInflater = activity.layoutInflater
        builder.setView(enterNameInflator.inflate(R.layout.enter_name_dialog, null))
        builder.setCancelable(false)

        enterNameDialog = builder.create()
        enterNameDialog.show()

        //nameEditView = enterNameDialog.nameEditText
        //a = nameEditView.text.toString()


    }
    fun name (): String{

        var nameEditView: EditText = enterNameDialog.nameEditText
        var ab = nameEditView.text.toString()!!
        Log.d("rrrrrr", "ooooooooooo")

        return ab
        Log.d("rrrrrr", ab)
    }


}