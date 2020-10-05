package com.example.examapp

class NationClass (val nation: String,
                   val ticketFare: Int,
                   val imageResource: Int = R.drawable.jj,
                   var questionClassList: MutableList<QuestionClass> ,
                   var purchased: Boolean = false)



