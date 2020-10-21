package com.example.examapp

class NationClass (val nation: String,
                   val ticketFare: Int,
                   val nationTicket: Int = R.drawable.norway,
                   var questionClassList: MutableList<QuestionClass>,
                   val nationCode: String = "SWE",
                   val nationPostcard: Int = R.drawable.usapost,
                   var purchased: Boolean = false,
                   var front_side: Boolean = true)



