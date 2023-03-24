import isel.leic.UsbPort

    fun main (args: Array<String>) {
        /*while(true) {
        val value = UsbPort.read()
        UsbPort.write(value)
    }
    */
       println(isBit(32))

    }
    fun readBits(mask: Int) : Int {
            var num = mask
            var decimalNumber = 0
            var i = 0
            var remainder: Long
            var kappa = num.and(7)
            println("lol = $kappa")
            while (num != 0) {
                remainder = num.toLong() % 10
                num /= 10
                decimalNumber += (remainder * Math.pow(2.0, i.toDouble())).toInt()
                ++i
            }
            return decimalNumber
        }
fun isBit(mask: Int): Boolean{
    val read = UsbPort.read()
    val kappa = mask.and(UsbPort.read())
    return kappa != 0

}
/*

"10010001"

        isBit("00000001")= true
        isBit("10000000")= true
        isBit("00000010")= false

 */

fun writeBits(mask: Int, value: Int) {

}

fun setBits(mask: Int){
    val write = mask.or(UsbPort.read())
    UsbPort.write(write)
}