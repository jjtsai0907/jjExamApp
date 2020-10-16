package com.example.examapp

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageButton
import kotlinx.android.synthetic.main.enter_name_dialog.*

class EnterNameDialog(var activity: Activity) {

    lateinit var enterNameDialog: AlertDialog
    lateinit var nameButton: ImageButton

    lateinit var a: String

    fun startEnterNameDialog() {
        var builder = AlertDialog.Builder(activity)


        val enterNameInflator: LayoutInflater = activity.layoutInflater
        builder.setView(enterNameInflator.inflate(R.layout.enter_name_dialog, null))
        builder.setCancelable(true)

        enterNameDialog = builder.create()
        enterNameDialog.show()

        //nameEditView = enterNameDialog.nameEditText
        //a = nameEditView.text.toString()


    }
    // needs to have this here, otherwise MainActivity cannot access nameEditText.
    fun userInput (): String{

        var nameEditView: EditText = enterNameDialog.nameEditText
        var userInput: String? = nameEditView.text.toString()

        if(userInput != null && userInput.isNotEmpty()){
            return userInput
        }
        else{
            userInput = "Todoro"
            return userInput
        }

    }


}