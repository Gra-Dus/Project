package com.example.firstproject.models

class Hero(
    var status:Status = Status.NORMAL,
    var question: Question = Question.NAME
) {
    fun askQuestion():String=
        when(question) {
            Question.NAME -> Question.NAME.question
            Question.PROFESSION -> Question.PROFESSION.question
            Question.MATERIAL -> Question.MATERIAL.question
            Question.BDAY -> Question.BDAY.question
            Question.SERIAL -> Question.SERIAL.question
            Question.IDLE -> Question.IDLE.question


        }

    fun listenAnswer(answer:String):Pair<String,Triple<Int,Int,Int>>{
return if(question.answers.contains(answer)){
    "Отлично - Это правильный ответ" to status.color

}else{
    "Это не правильный ответ" to status.color
}
    }
    enum class Status(val color:Triple<Int,Int,Int>){
        NORMAL(Triple(255,255,255)),
        WARNING(Triple(255,120,0)),
        DANGER(Triple(255,60,60)),
        CRITICAL(Triple(255,255,0)),
    }
    enum class Question(val question: String, val answers:List<String>){
        NAME("Как меня зовут?", listOf("Герой", "Hero","герой","hero")),
        PROFESSION("Какова моя профессия?", listOf("Повар","Герой","Защитник","Defender","Hero")),
        MATERIAL("Из чего я сделан ?", listOf("Плоть")),
        BDAY("Когда я рожден?", listOf("Когда солце взошло")),
        SERIAL("Мой серийный номер?", listOf("223332")),
        IDLE("Все, ухади", listOf()),
    }
}