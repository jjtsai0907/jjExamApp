package com.example.examapp

import android.media.Image
import android.util.Log
import com.google.android.gms.maps.model.LatLng

//singleton
object DataManager {


    val nations = mutableListOf<NationClass>()
    var countQuestion: Int = 0
    var wallet: Int = 0

    init{
        createMockData()

    }

    fun createMockData(){
        //done
        nations.add(NationClass("Hong Kong", 4300,R.drawable.hongkong, mutableListOf(
            QuestionClass("Which of the following has broken a deal/promise of 'One Country Two Systems',which violate human rights, after UK ruling power withdrew in 1841?", "China", "North Korea", "India", "USA", LatLng(22.520035,114.1735213)),
            QuestionClass("There have been enormous protests in HK since March 2019. HK seeks for human rights and democracy. Which country does HK fight against?", "China", "North Korea", "USA", "Turkey", LatLng(22.40599,114.0347053)),
            QuestionClass("After which war between China and UK, UK obtained the control over Hong Kong in 1842?", "Opium War", "Great War", "Asian War", "Hong K War", LatLng(22.24171,113.9309983)),
            QuestionClass("What does 'Hong Kong' mean?", "Fragrant Harbour", "Fragrant Dumplings", "Fragrant Skyscraper", "Fragrant Asia", LatLng(22.257787,114.2245383)))))

        //done
        nations.add(NationClass("Taiwan", 0,R.drawable.taiwan, mutableListOf(
            QuestionClass("Taiwan has the land size of Belgium. Belgium has 11 million residents. How many people live in Taiwan?", "23 million", "10 million", "17 million", "19 million", LatLng(24.2616609,120.5543753), "Ching-Shuei", R.drawable.chingshuei),
            QuestionClass("Which of the following is the first and currently only country that legalises gay marriage in Asia?", "Taiwan", "Japan", "South Korea", "China", LatLng(24.1572335,120.6638719), "Taichung", R.drawable.taichung),
            QuestionClass("Which of the following has never 'colonised/ruled' Taiwan?","UK", "China", "Spain", "Netherlands", LatLng(21.9326293,120.7415405), "Taichung", R.drawable.taichung),
            QuestionClass("Taiwan constructed one building that was once the tallest in the world. What's it called?", "Taipei 101", "Taiwan 101", "Taipei 100", "Taiwan 100", LatLng(24.4164556,121.5119348), "Taichung", R.drawable.taichung),
            QuestionClass("Which of the following country 'strives' not only to discriminate Taiwan's democracy and autonomy, but also threatening to start wars?", "China", "USA", "Czech Republic", "UK", LatLng(25.033976,121.5623502), "Taichung", R.drawable.taichung))))

        //done
        nations.add(NationClass("Japan", 5500,R.drawable.japan ,mutableListOf(
            QuestionClass("How large is the population in Tokyo?", "36 Million", "20 Million", "26 Million", "13 Million", LatLng(35.701528,139.6741807)),
            QuestionClass("Japan in Japanese is “Nihon” or “Nippon”. Which of the following is its meaning?", "Sun Origin", "Sun Never Leaves", "Sun Lights Up Life", "Sun Is Red", LatLng(35.568751,138.0967948)),
            QuestionClass("How high is the highest mountain, Mt. Fuji?", "3,776 m", "3,698 m", "3,779 m", "3,771 m", LatLng(35.2382716,138.3112288)),
            QuestionClass("Is Tokyo the only city in the World to issue passports to vampires?", "False, No Vampires", "False, Osaka also issues", "True, Only Tokyo", "False, Not the Only", LatLng(33.823099,134.1113743)),
            QuestionClass("Which of the following is the Capital of Japan?", "Tokyo", "Osaka", "Nagoya", "Sapporo", LatLng(39.738913,141.3488523)))))


        nations.add(NationClass("USA", 5800, R.drawable.usa, mutableListOf(
            QuestionClass("What do people call the metropolitan area of Chicago?", "Chicagoland", "Toyland", "Smokey Downtown", "Chicagoville", LatLng(41.8333925,-88.0121587)),
            QuestionClass("Besides the US, which of the following does not use the metric system?", "Myanmar", "North Korea", "Yemen", "Tibet", LatLng(38.8935128,-77.1546631)),
            QuestionClass("Besides Prospect Park in Brooklyn, which of the following was also considered as a possible location for the Statue of Liberty?", "Central Park", "Time Square", "City Hall Park", "Washington DC", LatLng(40.6892494,-74.0466891)),
            QuestionClass("Yosemite Falls is one of the tallest on earth. However, it is actually made up of ___ separate falls. How many is it?", "3", "2", "4", "5", LatLng(37.7565957,-119.5990961)),
            QuestionClass("FARGO?", "Myanmar", "North Korea", "Yemen", "Tibet", LatLng(46.8541125,-96.9685994)),
            QuestionClass("Which national park is in Oregon?", "Crater Lake", "Yosemite", "Alcatraz Island", "Eugene O'Neill", LatLng(44.0409399,-123.0877358)),
            QuestionClass("South Kaibab Trail is one of the popular trails to hike down to Colorado River from Rim, and it is 11.4 km long. How many water stops on the way?", "0", "Depends on Seasons", "3", "8", LatLng(36.073353,-112.0930617)))))



        nations.add(NationClass("Mongolia", 3700, R.drawable.mongolia, mutableListOf(
            QuestionClass("The first McDonald's in Russian was opened on Jan 31, 1990. The amount of customers served during a single day still remains the world record. How many people on that day?", "30,000", "10,000", "5,000", "3,600", LatLng(55.7554841,37.5442743)),
            QuestionClass("Dogs in Moscow are not allowed to bark at night. When?", "11pm - 7am", "10pm - 6am", "No Such a Rule", "12pm - 6am", LatLng(55.683907,37.7619633)),
            QuestionClass("How many countries does Russia share a boarder with?", "14", "15", "12", "8", LatLng(52.183874,35.8217343)),
            QuestionClass("Russia is home to the deepest lake in the world. How many % of the world's fresh water it contains?", "20 %", "70 %", "22 %", "12 %", LatLng(53.5962123,102.3422383)))))

        //done
        nations.add(NationClass("Russia", 1700, R.drawable.russia, mutableListOf(
            QuestionClass("The first McDonald's in Russian was opened on Jan 31, 1990. The amount of customers served during a single day still remains the world record. How many people on that day?", "30,000", "10,000", "5,000", "3,600", LatLng(55.7554841,37.5442743)),
            QuestionClass("Dogs in Moscow are not allowed to bark at night. When?", "11pm - 7am", "10pm - 6am", "No Such a Rule", "12pm - 6am", LatLng(55.683907,37.7619633)),
            QuestionClass("How many countries does Russia share a boarder with?", "14", "15", "12", "8", LatLng(52.183874,35.8217343)),
            QuestionClass("Russia is home to the deepest lake in the world. How many % of the world's fresh water it contains?", "20 %", "70 %", "22 %", "12 %", LatLng(53.5962123,102.3422383)))))


        //done
        nations.add(NationClass("Norway", 200, R.drawable.norway,mutableListOf(
            QuestionClass("Lærdal Tunnel, the world's longest road tunnel, is in Norway. How long is it?", "24.5 km", "25.5 km", "22.5 km", "30.5 km", LatLng(61.0200222,7.3733351)),
            QuestionClass("Which Nobel Prize is awarded in Oslo?", "Peace Prize", "Literature Prize", "Physics Prize", "Chemistry Prize", LatLng(59.8937806,10.6450336)))))

        nations.add(NationClass("Home to JJ", 350, R.drawable.jj, mutableListOf(
            QuestionClass("Lærdal Tunnel, the world's longest road tunnel, is in Norway. How long is it?", "24.5 km", "25.5 km", "22.5 km", "30.5 km", LatLng(61.0200222,7.3733351)),
            QuestionClass("Which Nobel Prize is awarded in Oslo?", "Peace Prize", "Literature Prize", "Physics Prize", "Chemistry Prize", LatLng(59.8937806,10.6450336)))))

        //done
        nations.add(NationClass("Iceland", 1100, R.drawable.iceland,mutableListOf(
            QuestionClass("Which of the following is the only mammal native to Iceland?", "Arctic Fox", "Polar Bear", "Icelandic Cat", "Vikings", LatLng(64.142314, -21.056091)),
            QuestionClass("Beer was illegal in Iceland until year ___ ?", "1989", "1993", "1458", "1901", LatLng(63.9829993,-19.0757547)),
            QuestionClass("How does Iceland smell like?", "Fart", "Rose", "Banana", "Deer", LatLng(65.220143, -16.671786)),
            QuestionClass("Iceland has only waged one war, and it can barely be called war. Which nation was it against?", "UK", "USA", "Green Land", "Denmark", LatLng(64.869977, -20.901669)),
            QuestionClass("How many railway lines are there in Iceland?", "0", "5", "3", "2", LatLng(65.644703, -23.082149)))))





    }

}