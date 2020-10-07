package com.example.examapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_finish.*
import java.util.zip.DataFormatException

class FinishFragment: Fragment() {

    lateinit var finishCountryTextVIew: TextView
    lateinit var finishQuestionTextView: TextView
    lateinit var finishWalletTextView: TextView
    lateinit var finishImageView1: ImageView
    lateinit var finishImageView2: ImageView
    lateinit var finishImageView3: ImageView
    var countCountries = 1


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_finish, container, false)

        finishCountryTextVIew = view.findViewById(R.id.finishCountryTextView)
        finishQuestionTextView = view.findViewById(R.id.finishQuestionTextView)
        finishWalletTextView = view.findViewById(R.id.finishWalletTextView)
        finishImageView1 = view.findViewById(R.id.finishImageView1)
        finishImageView2 = view.findViewById(R.id.finishImageView2)
        finishImageView3 = view.findViewById(R.id.finishImageView3)


        finishCountryTextVIew.alpha = 0F
        finishQuestionTextView.alpha = 0F
        finishWalletTextView.alpha = 0F

        finishImageView1.alpha = 0F
        finishImageView2.alpha = 0F
        finishImageView3.alpha = 0F

        return view
    }



    fun setText(){
        finishCountryTextVIew.alpha = 1F
        countCountries()
        finishCountryTextVIew.text = "Wow! You've been to ${countCountries.toString()} countries!"
        finishQuestionTextView.text = "You've answered ${DataManager.countQuestion.toString()} questions correctly!"
        finishWalletTextView.alpha = 1F
        finishWalletTextView.text = "At this moment, you still have ${DataManager.wallet.toString()} kr left."
        finishQuestionTextView.alpha = 1F
        finishImageView1.alpha = 1F
        finishImageView2.alpha = 1F
        finishImageView3.alpha = 1F


    }

    // To count how many countries have been to.
    fun countCountries () {

        for (i in 0 until DataManager.nations.size) {
            if (DataManager.nations[i].purchased) {
                countCountries++
            }
        }
    }
}