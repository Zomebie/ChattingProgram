package MiniProject3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import javax.swing.*;

public class Client extends JFrame {

	private JPanel panel;
	public JTextField tf1;
	public JButton send;
	public JTextArea tf2;
	private JScrollPane jsp;
	public String sendM;

	Socket socket;
	String id;
	String serverIp;

	Client(String id) {

		this.id = id;
		serverIp = "127.0.0.1";

		setLayout(new BorderLayout());
		setTitle(id + "님의 채팅방");
		setSize(500, 800);
		setResizable(false);
		setLocation(600, 100);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		panel = new JPanel();
		panel.setBackground(new Color(0, 133, 222));

		putInPanel(panel);
		add(panel);
		setVisible(true);

	}

	public void putInPanel(JPanel panel) {
		ImageIcon btnImage1 = new ImageIcon(".\\img\\btn_send.png");

		panel.setLayout(null);
		tf1 = new JTextField(30);
		tf1.setBounds(10, 700, 400, 49);
		panel.add(tf1);

		JToggleButton send = new JToggleButton(new ImageIcon(".\\img\\btn_send.png"));
		send.setBounds(412, 700, 80, 48);
		send.setPressedIcon(btnImage1);
		send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose();

			}

		});
		panel.add(send);

		tf2 = new JTextArea();
		// tf2.setBounds(10, 40, 400, 600);

		jsp = new JScrollPane(tf2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setBounds(10, 40, 400, 600);
		// panel.add(tf2);
		panel.add(jsp);

	}

	public void clientStart() {

		try {

			socket = new Socket(serverIp, 1001);
			System.out.println(" >>> 서버와 연결 성공 >>>");

			Thread clientSThread = new Thread(new ClientS(this));
			Thread clientRThread = new Thread(new ClientR(this));

			clientSThread.start();
			clientRThread.start();

		} catch (ConnectException e) {
			// e.printStackTrace();

		} catch (Exception e) {

			// e.printStackTrace();
		}
	}

}
