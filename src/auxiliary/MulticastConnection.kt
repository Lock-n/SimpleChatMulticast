package auxiliary

import java.net.*
import java.net.DatagramPacket
import java.net.InetAddress
import java.net.DatagramSocket

open class MulticastConnection(multicastAddress: String, val port: Int) {
	private val sender = DatagramSocket()
	private val multicastGroup: InetAddress = InetAddress.getByName(multicastAddress)
	private val receiver = MulticastSocket(port/*InetSocketAddress(multicastGroup, port)*/)
	private val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
	private val packet = DatagramPacket(buffer, buffer.size)

	init {
		receiver.joinGroup(multicastGroup)
	}

	var closed = false
	
	fun send(data: ByteArray) {
		val packet = DatagramPacket(data, data.size, multicastGroup, port)
		sender.send(packet)
	}
	
	fun read(): ByteArray {
		receiver.receive(packet)

		return packet.data
	}

	fun close() {
		receiver.leaveGroup(multicastGroup)
		receiver.close()

		sender.close()
		closed = true
	}
}