package com.example.firstproject.models

class Hero(
    var status:Status = Status.NORMAL,
    var question: Question = Question.NAME
) {
    fun askQuestion():String= when(question) {
            Question.NAME -> Question.NAME.question
            Question.PROFESSION -> Question.PROFESSION.question
            Question.MATERIAL -> Question.MATERIAL.question
            Question.BDAY -> Question.BDAY.question
            Question.SERIAL -> Question.SERIAL.question
            Question.IDLE -> Question.IDLE.question
        }

    fun listenAnswer(answer:String):Pair<String,Triple<Int,Int,Int>>{
return if(question.answers.contains(answer)){
    question = question.nextQuestion()
    status = status.previousStatus()
    "Отлично - Это правильный ответ\n${question.question}" to status.color

}else{
    status = status.nextStatus()
    "Это не правильный ответ\n${question.question}" to status.color
}
    }
    enum class Status(var color:Triple<Int,Int,Int>){
        NORMAL(Triple(128,219,226)),
        WARNING(Triple(255,255,0)),
        DANGER(Triple(255,120,0)),
        CRITICAL(Triple(255,60,60));

        fun nextStatus():Status{
            return if(this.ordinal == values().lastIndex){
                values()[this.ordinal]
            }else if(this.ordinal < values().lastIndex){
                values()[this.ordinal+1]
            }else{
                values()[0]
            }
    }
        fun previousStatus():Status{
            return values()[this.ordinal-1]
        }
    }
    enum class Question(var question: String, var answers:List<String>){
        NAME("Как меня зовут?", listOf("Герой", "Hero","герой","hero")){
            override fun nextQuestion(): Question = PROFESSION  },
        PROFESSION("Какова моя профессия?", listOf("Повар","Герой","Защитник","Defender","Hero")){
            override fun nextQuestion(): Question = MATERIAL},
        MATERIAL("Из чего я сделан ?", listOf("Плоть")){
            override fun nextQuestion(): Question = BDAY},
        BDAY("Когда я рожден?", listOf("Когда солнце взошло")){
            override fun nextQuestion(): Question = SERIAL},
        SERIAL("Мой серийный номер?", listOf("223332")){
            override fun nextQuestion(): Question = IDLE},
        IDLE("Все, ухади", listOf()){
            override fun nextQuestion(): Question = IDLE};
        abstract fun nextQuestion():Question

    }

    }
