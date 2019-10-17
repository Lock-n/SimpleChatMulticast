package chat

import java.util.Scanner
import auxiliary.ChatMulticastConnection
import auxiliary.ChatUdpConnection
import java.net.SocketException

class Client {
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			val input = Scanner(System.`in`)

			println("Type your nickname:")

			val nickname = input.next()
			multicast(input, nickname)
		}

		@JvmStatic
		fun udp(input: Scanner, nickname: String) {
			println("Type the server's ip: ")
			//224.3.29.71

			val serverIP = input.next()

			println("Type the server's port: ")
			//10000

			val serverPort = input.nextInt()

			val chat = ChatUdpConnection(serverIP, serverPort)

			println("Connection established. Type EXIT to exit the chat")

			var message = input.next()

			Thread(Runnable {
				try {
					while (true)
						println(chat.readMessage())
				}
				catch (e: SocketException) {

				}
			}).start()

			while (message != "EXIT") {
				chat.send(message);

				message = input.next()
			}

			chat.close()
		}

		@JvmStatic
		fun multicast(input: Scanner, nickname: String) {
			println("Type the server's ip: ")
			//224.3.29.71
			//235.0.0.0

			val serverIP = input.next()

			println("Type the server's port: ")
			//10000
			//1236

			val serverPort = input.nextInt()

			val chat = ChatMulticastConnection(serverIP, serverPort)

			println("Connection established. Type EXIT to exit the chat")

			var message = input.next()

			Thread(Runnable {
				try {
					while (true)
						println(chat.readMessage())
				}
				catch (e: SocketException) {

				}
			}).start()

			while (message != "EXIT") {
				chat.send(nickname + ": " + message);

				message = input.next()
			}

			chat.close()
		}
	}
}