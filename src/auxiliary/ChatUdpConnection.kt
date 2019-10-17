package auxiliary

class ChatUdpConnection(ipAddress: String, port: Int) : UdpConnection(ipAddress, port) {
    fun send(message: String) {
        super.send(message.toByteArray())
    }

    fun readMessage(): String {
        val data = super.read()

        return String(data, 0, data.size)
    }
}