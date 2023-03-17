import isel.leic.UsbPort

    fun main (args: Array<String>) {
        /*while(true) {
        val value = UsbPort.read()
        UsbPort.write(value)
    }
    */
       println(readBits(1000))
    }
    fun readBits(mask: Int) : Int {
            var num = mask
            var decimalNumber = 0
            var i = 0
            var remainder: Long

            while (num != 0) {
                remainder = num.toLong() % 10
                num /= 10
                decimalNumber += (remainder * Math.pow(2.0, i.toDouble())).toInt()
                ++i
            }
            return decimalNumber
        }
