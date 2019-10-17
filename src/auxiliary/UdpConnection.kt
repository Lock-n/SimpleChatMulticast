package auxiliary

import java.net.*

open class UdpConnection(ipAddress: String, val port: Int) {
    private val socket = DatagramSocket()
    private val ipAddressObj: InetAddress = InetAddress.getByName(ipAddress)
    private val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
    private val packet = DatagramPacket(buffer, buffer.size)

    var closed = false

    fun send(data: ByteArray) {
        val packet = DatagramPacket(data, data.size, ipAddressObj, port)
        socket.send(packet)
    }

    fun read(): ByteArray {
        socket.receive(packet)

        return packet.data
    }

    fun close() {
        socket.close()
        closed = true
    }
}