import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult


class Person(val name: String, val age: Int)

fun tryingLamda(): Unit {
    var x = OnCompleteListener<AuthResult> {
        Log.e("Aa", "Aa")
    }
    var myVariable = { parm1: String ->
        Log.e("parm", "is $parm1")
    }
    var q = myVariable.invoke("")

    fun String.printName(): Unit {
        Log.e("eaa", "$this")
    }
    "aa1".printName()

    var w = { number: Int -> number + 1 }

    fun addOffset(base: Int, lamda: (Int) -> Int): Int {
        return lamda.invoke(base)
    }

    addOffset(2) { x -> x + 1 }
}

interface Formatter {
    fun format(string: java.lang.String): String
}

class Printer(var stringFormatStrategy: Formatter) {
    fun print(string: java.lang.String) {
        Log.e("PrinterFunction", "is ${stringFormatStrategy.format(string)}")
    }
}


class Printer2(var stringFormatterStrategy2: (java.lang.String) -> String) {
    fun print(string: java.lang.String) {
        Log.e("PrintFunction2", "is ${stringFormatterStrategy2.invoke(string)}")
    }
}


var printerPar: (java.lang.String) -> String = {
    it.toUpperCase()

}

fun qq() {
    var wq = x().invoke(6)

}

var lower = Printer2(printerPar)


var lowerCasFormat: Formatter = object : Formatter {
    override fun format(string: java.lang.String): String {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return string.toLowerCase()
    }

}

fun x() = { y: Int -> y + 3 }

var upperCaseFormat: Formatter = object : Formatter {
    override fun format(string: java.lang.String): String {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return string.toUpperCase()
    }

}

