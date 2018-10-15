package MiniProject3;

import java.io.*;

public class ClientR implements Runnable {

	DataInputStream in;
	Client client;

	ClientR(Client client) {

		this.client = client;
		try {
			in = new DataInputStream(client.socket.getInputStream());
		} catch (IOException e) {

			//e.printStackTrace();
		}

	}

	// 받은 메세지
	@Override
	public void run() {

		while (in != null) {
			try {

				client.tf2.append(in.readUTF() + "\n");
				client.tf1.setText("");

			} catch (IOException e) {

				//e.printStackTrace();
			}
		}

	}
}
