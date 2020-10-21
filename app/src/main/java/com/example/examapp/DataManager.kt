package com.example.examapp

import android.widget.TextView
import com.google.android.gms.maps.model.LatLng

//singleton
object DataManager {

    val nations = mutableListOf<NationClass>()
    var countQuestion: Int = 0
    var countCountries: Int = 1
    var wallet: Int = 0
    var reStart: Boolean = false
    var currentCountry: Int = -1
    var clearMaps: Boolean = false
    lateinit var userName: String
    var countryBeenTo: MutableList<String> = mutableListOf("Sweden")

    init{
        createMockData()

    }

    fun createMockData(){


        nations.add(NationClass("Peru", 0,R.drawable.ticket, mutableListOf(
            QuestionClass("Does the land of Peru reach the Northern Hemisphere? If so, by how many km does it pass over the equator?", "No", "Yes, 3.3km", "Yes, 3.0km", "Yes, 18.4km", LatLng(-0.106382, -75.202472), "Teniente Manuel Clavero", R.drawable.peru1),
            QuestionClass("Does Peru fight over a waterway to the ocean against Bolivia? If so, in which year?", "No", "Yes, 1876", "Yes, 1676", "Yes, 1823", LatLng(-18.017641, -70.249610), "Tacna", R.drawable.peru2),
            QuestionClass("What is the national bird of Peru called?", "Cock-of-the-rock", "Cock-of-the-stone", "Cock-of-ocean", "Cock-of-mountain", LatLng(-12.045653, -77.042097), "Lima", R.drawable.peru3),
            QuestionClass("The height of Machu Picchu?", "2,430 m", "2,630 m", "2,530 m", "2,730 m", LatLng(-13.163237, -72.544842), "Machu Picchu", R.drawable.peru4)),"PER"))


        nations.add(NationClass("Taiwan", 0, R.drawable.twn, mutableListOf(
            QuestionClass("Taiwan has the land size of Belgium. Belgium has 11 million residents. How many people live in Taiwan?", "23 million", "10 million", "17 million", "19 million", LatLng(24.2616609,120.5543753), "ChingShuei", R.drawable.chingshuei),
            QuestionClass("Taiwan is the first and currently only country that legalises gay marriage in Asia, in which year did it become legal?", "2019", "2017", "2013", "2015", LatLng(24.1572335,120.6638719), "Taichung", R.drawable.taichung),
            QuestionClass("Which of the following has never 'colonised/ruled' Taiwan?","UK", "China", "Spain", "Netherlands", LatLng(21.9326293,120.7415405), "Kenting", R.drawable.taichung),
            QuestionClass("Taiwan constructed one building that was once the tallest in the world. What's it called?", "Taipei 101", "Taiwan 101", "Taipei 100", "Taiwan 100", LatLng(24.4164556,121.5119348), "YiLang", R.drawable.mongolia),
            QuestionClass("Taiwan current have a female president, at what year was she elected to office?", "2016", "2017", "2018", "2019", LatLng(25.033976,121.5623502), "Taipei", R.drawable.taipei)),"TWN"))


        nations.add(NationClass("Japan", 0, R.drawable.jpn ,mutableListOf(
            QuestionClass("How large is the population in Tokyo?", "36 Million", "20 Million", "26 Million", "13 Million", LatLng(35.701528,139.6741807), "Tokyo", R.drawable.tokyo1),
            QuestionClass("Japan in Japanese is “Nihon” or “Nippon”. Which of the following is its meaning?", "Sun Origin", "Sun Never Leaves", "Sun Lights Up Life", "Sun Is Red", LatLng(35.4777818,138.5959693), "Yamanashi", R.drawable.yamanashi),
            QuestionClass("How high is the highest mountain, Mt. Fuji?", "3,776 m", "3,698 m", "3,779 m", "3,771 m", LatLng(35.2382716,138.3112288), "Shizuoka", R.drawable.japan),
            QuestionClass("Is Tokyo the only city in the World to issue passports to vampires?", "False, No Vampires", "False, Osaka also issues", "True, Only Tokyo", "False, Not the Only", LatLng(33.823099,134.1113743), "Tokushima", R.drawable.japan1),
            QuestionClass("Which of the following is the Capital of Japan?", "Tokyo", "Osaka", "Nagoya", "Sapporo", LatLng(39.738913,141.3488523), "Iwate", R.drawable.japan2)),  "JPN"))


        nations.add(NationClass("USA", 0, R.drawable.usaticket, mutableListOf(
            QuestionClass("What do people call the metropolitan area of Chicago?", "Chicagoland", "Toyland", "Smokey Downtown", "Chicagoville", LatLng(41.8339042,-88.0121653), "Chicago", R.drawable.chicago),
            QuestionClass("Besides the US, which of the following does not use the metric system?", "Myanmar", "North Korea", "Yemen", "Tibet", LatLng(38.8879991,-77.0938978), "Washington", R.drawable.usa),
            QuestionClass("Besides Prospect Park in Brooklyn, which of the following was also considered as a possible location for the Statue of Liberty?", "Central Park", "Time Square", "City Hall Park", "Washington DC", LatLng(40.6892494,-74.0466891), "New Jersey", R.drawable.timesq),
            QuestionClass("Yosemite Falls is one of the tallest on earth. However, it is actually made up of ___ separate falls. How many is it?", "3", "2", "4", "5", LatLng(37.7565957,-119.5990961), "Yosemite", R.drawable.yosemite),
            QuestionClass("Which of the following is one of the three sister cities of Fargo's??", "Vimmerby", "Trollhättan", "Karlstad", "Sundsvall", LatLng(46.8541125,-96.9685994), "Fargo", R.drawable.fargo),
            QuestionClass("Which national park is in Oregon?", "Crater Lake", "Yosemite", "Alcatraz Island", "Eugene O'Neill", LatLng(44.0409399,-123.0877358), "Eugene", R.drawable.eugene),
            QuestionClass("South Kaibab Trail is one of the popular trails to hike down to Colorado River from Rim, and it is 11.4 km long. How many water stops on the way?", "0", "Depends on Seasons", "3", "8", LatLng(36.073353,-112.0930617), "Grand Canyon",R.drawable.gc)), "USA"))


        nations.add(NationClass("Mongolia", 0, R.drawable.mng, mutableListOf(
            QuestionClass("Which of the following religions is the largest in Mongolia?", "Buddhism", "Islam", "Shamanism", "Christianity", LatLng(47.920540, 106.917278), "Ulaanbaatar", R.drawable.ulaanbaatar),
            QuestionClass("How many % of the Mongolian energy requirement comes from Russia?", "90 %", "80 %", "95 %", "85 %", LatLng(48.969742, 89.956230), "Uglii", R.drawable.mongolia4),
            QuestionClass("What is the traditional Mongolian tent called in Mongolian language?", "Ger", "Gur", "Ggpr", "Gor", LatLng(43.316320, 97.375580), "Great Gobi Desert", R.drawable.mongolia),
            QuestionClass("Morin khuur is a traditional Mongolian musical instrument. What type of instrument is it?", "Strings", "Vocal", "Brass", "Percussion", LatLng(47.818348, 106.966224), "Bogd Khan", R.drawable.mongolia2)), "MNG"))


        nations.add(NationClass("Russia", 0, R.drawable.rus, mutableListOf(
            QuestionClass("The first McDonald's in Russian was opened on Jan 31, 1990. The amount of customers served during a single day still remains the world record. How many people on that day?", "30,000", "10,000", "5,000", "3,600", LatLng(55.765532, 37.605850), "Pushkin Square, Moscow", R.drawable.russia1),
            QuestionClass("Dogs in Moscow are not allowed to bark at night. When?", "11pm - 7am", "10pm - 6am", "No Such a Rule", "12pm - 6am", LatLng(55.750981, 37.617209),"Moscow", R.drawable.russia4),
            QuestionClass("How many countries does Russia share a boarder with?", "14", "15", "12", "8", LatLng(59.930907, 30.361856), "St. Petersburg", R.drawable.russia2),
            QuestionClass("Russia is home to the deepest lake in the world. How many % of the world's fresh water it contains?", "20 %", "70 %", "22 %", "12 %", LatLng(53.4045986,108.1260905), "Lake Baikal", R.drawable.russia3)), "RUS"))


        nations.add(NationClass("Norway", 0, R.drawable.nor,mutableListOf(
            QuestionClass("Lærdal Tunnel, the world's longest road tunnel, is in Norway. How long is it?", "24.5 km", "25.5 km", "22.5 km", "30.5 km", LatLng(60.979206,7.3920573), "Laerdal", R.drawable.norway),
            QuestionClass("Which Nobel Prize is awarded in Oslo?", "Peace Prize", "Literature Prize", "Physics Prize", "Chemistry Prize", LatLng(59.9138453,10.7521148), "Oslo", R.drawable.norway1)), "NOR", R.drawable.nor))


        nations.add(NationClass("Germany", 0, R.drawable.deu, mutableListOf(
            QuestionClass("What year did the Berlin Wall between former east and west Germany fall?", "1989", "1991", "1987", "1985", LatLng(52.502823,13.4453844),"Berlin", R.drawable.berlin),
            QuestionClass("Munich lies in one of Germany's southern most states, what is its German name?", "Bavaria", "Saxony", "Hesse", "Brandenburg", LatLng(48.137238,11.5756182), "Munich", R.drawable.munich),
            QuestionClass("Hamburg has a very large harbour, it's Europe's __ largest", "3rd", "1st", "2nd", "4th", LatLng(53.5451198,9.9818934), "Hamburg", R.drawable.hamburg)), "DEU"))


        nations.add(NationClass("Iceland", 0, R.drawable.isl,mutableListOf(
            QuestionClass("Which of the following is the only mammal native to Iceland?", "Arctic Fox", "Polar Bear", "Icelandic Cat", "Vikings", LatLng(64.6656969,-21.3046096), "Reykholt", R.drawable.iceland),
            QuestionClass("Beer was illegal in Iceland until year ___ ?", "1989", "1993", "1458", "1901", LatLng(64.1335484,-21.9224814),"Reykjavik", R.drawable.iceland1),
            QuestionClass("How does Iceland smell like?", "Fart", "Rose", "Banana", "Deer", LatLng(64.3137852,-20.2996613), "Geysir", R.drawable.iceland2),
            QuestionClass("Iceland has only waged one war, and it can barely be called war. Which nation was it against?", "UK", "USA", "Green Land", "Denmark", LatLng(63.4376879,-20.2760758), "Vestmannaeyjar", R.drawable.iceland3),
            QuestionClass("How many railway lines are there in Iceland?", "0", "5", "3", "2", LatLng(65.679733,-18.0940219,), "Akureyri", R.drawable.iceland4)), "ISL"))
    }
}