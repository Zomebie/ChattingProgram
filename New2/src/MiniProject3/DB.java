package MiniProject3;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class DB extends JFrame {

	Connection conn = null;
	// DB�� ����� ����(����)�� ���� ��ü
	PreparedStatement pstm = null;
	// SQL���� ��Ÿ�� ��ü
	ResultSet rs = null;
	// �������� �����Ϳ� ���� ��ȯ���� ���� ��ü
	// SQL ������ ����� ���� ������ ���Ǿ�(SELECT��)���
	// �� ����� ���� ResulSet ��ü�� �غ��� �� �����Ų��.

	private JPanel panel;
	private JScrollPane scrollPane;

	/*
	 * private JLabel userLabel; private JLabel passLabel;
	 */

	private JTextField userText;
	private JPasswordField passText;

	private JButton loginButton;
	private JButton signupButton;

	private boolean bLoginCheck;

	DB() {
		ImageIcon bg = new ImageIcon(".\\img\\bg.png"); // �����ڿ� bg ȣ���ϰ� �̹��� ��� ����

		// �α��� â
		setTitle("Login");
		setSize(500, 800);
		setResizable(false);
		setLocation(500, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		panel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(bg.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};

		scrollPane = new JScrollPane(panel);
		setContentPane(scrollPane);

		putInPanel(panel);
		/*
		 * add(panel); setVisible(true);
		 */

	}

	public void putInPanel(JPanel panel) {
		// ��ư�̹���
		ImageIcon btnImage1 = new ImageIcon(".\\img\\btn_login.png");
		ImageIcon btnImage2 = new ImageIcon(".\\img\\btn_joinus.png");
		Font f1 = new Font("Consolas", Font.BOLD, 30);

		panel.setLayout(null);
		/*
		 * userLabel = new JLabel("ID"); userLabel.setBounds(60, 400, 450, 250);
		 * userLabel.setFont(f1); panel.add(userLabel);
		 * 
		 * passLabel = new JLabel("Pass"); passLabel.setBounds(60, 450, 450, 250);
		 * passLabel.setFont(f1); panel.add(passLabel);
		 */

		userText = new JTextField(20);
		userText.setBounds(56, 260, 385, 65);
		panel.add(userText);

		passText = new JPasswordField(20);
		passText.setBounds(56, 323, 385, 65);
		panel.add(passText);
		passText.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isLoginCheck();

			}

		});

		loginButton = new JButton("Login");
		JToggleButton loginButton = new JToggleButton(new ImageIcon(".\\img\\btn_login.png"));

		loginButton.setBounds(56, 395, 385, 44);
		loginButton.setPressedIcon(btnImage1);
		loginButton.setBorderPainted(false);

		panel.add(loginButton);
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isLoginCheck();

			}
		});

		signupButton = new JButton("Sign Up");
		JToggleButton signupButton = new JToggleButton(new ImageIcon(".\\img\\btn_joinus.png"));

		signupButton.setPressedIcon(btnImage2);
		signupButton.setBorderPainted(false);

		signupButton.setBounds(56, 450, 76, 20);
		panel.add(signupButton);
		signupButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SignUp();

			}

		});

	}

	public boolean isLogin() {
		return bLoginCheck;

	}

	public void isLoginCheck() {

		String id = userText.getText();
		String pass = new String(passText.getPassword());
		conn = DBcon.getConnection();
		// getConnection()�Լ��� static�̶� DBcon�� �������� �ʾƵ� �θ� �� �ִ�!��ȫ
		// ����Ȼ���(conn)�� ������ prepareStatement(query)�޼��带 ���� DB�� �������� ������

		String quary = "SELECT ID, PASS FROM users where id IN ('" + id + "')and pass in('" + pass + "')";

		try {

			pstm = conn.prepareStatement(quary);
			// executeQuery�� �������� �����Ű�� �ش� ����� rs�� ���
			// executeQuery()�� ��ȯ���� ResultSet�̴�

			rs = pstm.executeQuery();

			if (rs.next()) {

				JOptionPane.showMessageDialog(null, "Login Success");

				bLoginCheck = true;

				if (isLogin()) {

					System.out.println(" ä��â ����!");
					new Client(id).clientStart();

				}

			} else
				JOptionPane.showMessageDialog(null, "Login Failed");

		} catch (SQLException sqle) {

			System.out.println("SELECT������ ���� �߻�");
			sqle.printStackTrace();

		} finally {

			// DB ������ �����Ѵ�.
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}

		}

	}

	public static void main(String args[]) {
		new DB();
		DB frame = new DB();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 800);
		frame.setVisible(true);
		frame.setLayout(null);

	}
}
