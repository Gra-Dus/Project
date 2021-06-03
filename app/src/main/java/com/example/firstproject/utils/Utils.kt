package com.example.firstproject.utils

object Utils {
fun paresFullName(fullName:String?):Pair<String?,String?>{
    val pares:List<String>? = fullName?.split(" ")
val firstName = pares?.getOrNull(0)
val lastName = pares?.getOrNull(1)
    return firstName to lastName
}

    fun traneliteration(payload: String, divider:String): String {
var ch:CharArray = payload.toCharArray()
        var chEdited:String =""
        for ( Ch in ch){
        when(Ch){
            //LowerCase
            'а' ->chEdited += 'a'.toString()
            'б' ->chEdited += 'b'.toString()
            'в' ->chEdited += 'v'.toString()
            'г' ->chEdited += 'g'.toString()
            'д' ->chEdited += 'd'.toString()
            'е' ->chEdited += 'e'.toString()
            'ё' ->chEdited += "je"
            'ж' ->chEdited += "zh"
            'з' ->chEdited += 'z'.toString()
            'и' ->chEdited += 'i'.toString()
            'й' ->chEdited += 'y'.toString()
            'к' ->chEdited += 'k'.toString()
            'л' ->chEdited += 'l'.toString()
            'м' ->chEdited += 'm'.toString()
            'н' ->chEdited += 'n'.toString()
            'о' ->chEdited += 'o'.toString()
            'п' ->chEdited += 'p'.toString()
            'р' ->chEdited += 'r'.toString()
            'с' ->chEdited += 's'.toString()
            'т' ->chEdited += 't'.toString()
            'у' ->chEdited += 'u'.toString()
            'ф' ->chEdited += 'f'.toString()
            'х' ->chEdited += "kh"
            'ц' ->chEdited += 'c'.toString()
            'ч' ->chEdited += "ch"
            'ш' ->chEdited += "sh"
            'щ' ->chEdited += "jsh"
            'ъ' ->chEdited += "hh"
            'ы' ->chEdited += "ih"
            'ь' ->chEdited += "jh"
            'э' ->chEdited += "eh"
            'ю' ->chEdited += "ju"
            'я' ->chEdited += "ja"
            //UpperCase
            'А' ->chEdited += 'A'.toString()
            'Б' ->chEdited += 'B'.toString()
            'В' ->chEdited += 'V'.toString()
            'Г' ->chEdited += 'G'.toString()
            'Д' ->chEdited += 'd'.toString()
            'Е' ->chEdited += 'E'.toString()
            'Ё' ->chEdited += "JE"
            'Ж' ->chEdited += "ZH"
            'З' ->chEdited += 'Z'.toString()
            'И' ->chEdited += 'I'.toString()
            'Й' ->chEdited += 'Y'.toString()
            'К' ->chEdited += 'K'.toString()
            'Л' ->chEdited += 'L'.toString()
            'М' ->chEdited += 'M'.toString()
            'Н' ->chEdited += 'N'.toString()
            'О' ->chEdited += 'O'.toString()
            'П' ->chEdited += 'P'.toString()
            'Р' ->chEdited += 'R'.toString()
            'С' ->chEdited += 'S'.toString()
            'Т' ->chEdited += 'T'.toString()
            'У' ->chEdited += 'U'.toString()
            'Ф' ->chEdited += 'F'.toString()
            'Х' ->chEdited += "KH"
            'Ц' ->chEdited += 'C'.toString()
            'Ч' ->chEdited += "CH"
            'Ш' ->chEdited += "SH"
            'Щ' ->chEdited += "JSH"
            'Ъ' ->chEdited += "HH"
            'Ы' ->chEdited += "IH"
            'Ь' ->chEdited += "JH"
            'Э' ->chEdited += "EH"
            'Ю' ->chEdited += "JU"
            'Я' ->chEdited += "JA"
            ' ' ->chEdited += divider
        }

        }
        return chEdited
    }
    fun initials(firstName: String?, lastName: String?): String? {
        return firstName?.toCharArray()!![0].toString() + lastName?.toCharArray()!![0]
    }


}
