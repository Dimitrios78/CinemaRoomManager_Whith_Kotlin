
const val GRAVITY_CONSTANT = 9.8
fun main() {
    val g = readln().toDouble()
    val h = readln().toDouble()
    val p: Double =  (GRAVITY_CONSTANT * g * h)
    println(p)
}