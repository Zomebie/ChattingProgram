package MiniProject3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ClientS implements Runnable {

	DataOutputStream out;
	Client client;

	ClientS(Client client) {
		this.client = client;

		try {
			out = new DataOutputStream(client.socket.getOutputStream());
		} catch (IOException e) {

			//e.printStackTrace();
		}

	} // 생성자

	// 보낼 메세지
	public void run() {

		System.out.println("clientS run");

		try {
			if (out != null)
				out.writeUTF(client.id);
			client.tf1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					try {
						out.writeUTF("[" + client.id + "]" + client.tf1.getText());
					} catch (IOException e1) {

						// e1.printStackTrace();
					}
				}

			});

		} catch (

		IOException e) {

			// e.printStackTrace();
		}

	}

}
