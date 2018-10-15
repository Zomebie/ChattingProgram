package MiniProject3;

// 회원가입 UI
import java.sql.Statement;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.*;

public class SignUp extends JFrame {

	JScrollPane scrollPane;
	Connection conn;
	Statement stmt;

	private ImageIcon bg;
	/*
	 * private JPanel panel;
	 * 
	 * private JLabel guide; private JLabel nameL; private JLabel idL; private
	 * JLabel passL;
	 */

	private JTextField name;
	private JTextField id;
	private JPasswordField pass;

	// private JButton commit;

	SignUp() {

		bg = new ImageIcon(".\\img\\join_bg.png"); // 생성자에 bg 호출하고 이미지 경로 지정

		setTitle("Sign Up");
		setSize(500, 630);
		setResizable(false);
		setLocation(600, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(bg.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
				setOpaque(true);
			}
		};

//      name = new JTextField(20);
//      name.setBounds(160, 60, 160, 25);
//      panel.add(name);

		scrollPane = new JScrollPane(panel);
		setContentPane(scrollPane);

		putInPanel(panel);

		// panel = new JPanel();
		// putInPanel(panel);

		add(new JLabel(new ImageIcon(".\\img\\join_bg.png")));
		setVisible(true);

	}

	public void putInPanel(JPanel panel) {
		panel.setLayout(null);

		// 닉네임
		name = new JTextField(20);
		name.setBounds(130, 180, 313, 50);
		panel.add(name);

		// ID
		id = new JTextField(20);
		id.setBounds(130, 242, 313, 50);
		panel.add(id);

		// 비밀번호
		pass = new JPasswordField(20);
		pass.setBounds(130, 305, 313, 50);
		pass.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addUser();

			}
		});

		panel.add(pass);
		// commit = new JButton("OK");
		JToggleButton commit = new JToggleButton(new ImageIcon(".\\img\\btn_ok.png"));
		commit.setBounds(130, 360, 313, 30);
		commit.setBorderPainted(false);
		commit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addUser();
				setVisible(false);

			}

		});

		panel.add(commit);

	}

	public void addUser() {

		String name1 = name.getText();
		String id1 = id.getText();
		String pass1 = new String(pass.getPassword());

		conn = DBcon.getConnection();
		String insert = "Insert into users values('" + name1 + "','" + id1 + "','" + pass1 + "')";

		try {
			stmt = conn.createStatement();
			int rs = stmt.executeUpdate(insert);
			System.out.println(rs + "명 삽입");
			JOptionPane.showMessageDialog(null, "가입 완료");

			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

	}

}