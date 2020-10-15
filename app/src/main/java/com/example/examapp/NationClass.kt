package com.example.examapp

class NationClass (val nation: String,
                   val ticketFare: Int,
                   val nationTicket: Int = R.drawable.jj,
                   var questionClassList: MutableList<QuestionClass>,
                   var purchased: Boolean = false,
                   var front_side: Boolean = true,
                   val nationCode: String = "TW",
                   val nationPostcard: Int = R.drawable.usapost)



