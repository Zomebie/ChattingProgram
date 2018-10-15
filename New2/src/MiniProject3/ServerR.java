package MiniProject3;

import java.io.*;
import java.net.*;
import java.util.*;

class ServerR extends Thread {

	Socket clientSocket;
	DataInputStream in;
	DataOutputStream out;
	HashMap<String, DataOutputStream> client;

	public ServerR(Socket clientSocket, HashMap<String, DataOutputStream> client) {

		this.clientSocket = clientSocket;
		this.client = client;
		try {

			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());

		} catch (IOException e) {

			//e.printStackTrace();
		}
	}

	public void run() {
		String name = "";
		try {
			name = in.readUTF();
			sendToAll("[" + name + "" + "님 입장]");
			// 처음 한 명이 채팅에 접속했을 땐 client map에 아무도 없기 때문에 안뜸
			client.put(name, out);
			System.out.println("현재 서버 접속자 수는 " + client.size() + "입니다.");

			while (in != null) {
				sendToAll(in.readUTF());

			}

		} catch (IOException e) {
			// 클라이언트가 종료되어 입력스트림(in)이 null이 되면 while문을 빠져 나와 client 목록에서 해당 클라이언트를 제거
			//e.printStackTrace();

		} finally {
			sendToAll("*" + name + "님 퇴장");
			client.remove(name);
			System.out.println("*" + clientSocket.getInetAddress() + " : " + clientSocket.getPort() + "에서 접속 종료");
			System.out.println("현재 서버 접속자 수는" + client.size() + "입니다.");

		} // try~catch~finally block

	}// run

	public void sendToAll(String msg) {

		Iterator it = client.keySet().iterator();

		while (it.hasNext()) {

			try {

				DataOutputStream out = client.get(it.next());
				out.writeUTF(msg);

			} catch (IOException e) {
				//e.printStackTrace();

			}
		} // while

	}// sendToAll
}