package auxiliary

class ChatMulticastConnection(multicastAddress: String, port: Int) : MulticastConnection(multicastAddress, port) {
    fun send(message: String) {
        this.send(message.toByteArray())
    }

    fun readMessage(): String {
        val data = super.read()

        return String(data, 0, data.size)
    }
}