import kotlin.collections.sortedByDescending as sortedByDescending1

var a = 1

var s = "a is $a"

a = 2

println("$s,but now a is $a")

var se = "smeg"

if (se is String)
    println(se.length)

if (se !is String)
    println()
println(se.length)

val list = mutableListOf("one","two")
list.add("three")
list.map {
    println(it)
}

val list1 = listOf("one","two")
val list2 = listOf("three","four")
var list3 = list1.zip(list2)

val iterator = list3.iterator()
while (iterator.hasNext()){
    println(iterator.next())
}

println(list1.take(1))
println(list1.take(1))


var list4 = listOf("1","1234","123456","12","123","12345")
var temp = list4.sortedBy {
    it.length
}

println(temp)

var temp1 = list4.sortedWith(compareBy { it.length })
println(temp1)
