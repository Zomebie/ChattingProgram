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
			sendToAll("[" + name + "" + "�� ����]");
			// ó�� �� ���� ä�ÿ� �������� �� client map�� �ƹ��� ���� ������ �ȶ�
			client.put(name, out);
			System.out.println("���� ���� ������ ���� " + client.size() + "�Դϴ�.");

			while (in != null) {
				sendToAll(in.readUTF());

			}

		} catch (IOException e) {
			// Ŭ���̾�Ʈ�� ����Ǿ� �Է½�Ʈ��(in)�� null�� �Ǹ� while���� ���� ���� client ��Ͽ��� �ش� Ŭ���̾�Ʈ�� ����
			//e.printStackTrace();

		} finally {
			sendToAll("*" + name + "�� ����");
			client.remove(name);
			System.out.println("*" + clientSocket.getInetAddress() + " : " + clientSocket.getPort() + "���� ���� ����");
			System.out.println("���� ���� ������ ����" + client.size() + "�Դϴ�.");

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