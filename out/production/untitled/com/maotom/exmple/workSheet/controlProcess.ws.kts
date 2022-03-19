import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

//GlobalScope.launch {
//    delay(1000)
//    println("world")
//}
//
//println("hello")
//Thread.sleep(2000)

//var decs = "你好".also (::println)

class Child(var name: String){
    init{ var sex = "男".also(::println)}
    var age: Int? = null
    constructor(name: String,age: Int) : this(name){
        println(name)
        this.age = age
    }

    override fun toString(): String {
        return "name: "+name+"age: "+age
    }

    fun printDecs(){
        println(this.toString())
    }
}

var child = Child("lihao",25)
child.printDecs()
