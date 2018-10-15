package MiniProject3;

import java.awt.Color;
import java.awt.Font;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.*;

public class Server extends JFrame {

   HashMap<String, DataOutputStream> client;

   JFrame f;
   JPanel p;
   JTextArea ta;
   JLabel l;

   Server() {

      client = new HashMap<>();
      Collections.synchronizedMap(client); // �ؽ��ʿ� ���������� ������ �Ұ��ϰ�

      f = new JFrame("SERVER");
      f.setSize(300, 450);

      p = new JPanel();
      ta = new JTextArea(20, 20);
      l = new JLabel("Client List");
      p.setBackground(new Color(0, 133, 222));
      l.setFont(new Font("Serif", Font.BOLD, 15));
      p.add(l);
      p.add(ta);
      f.add(p);

      f.setVisible(true);
      f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);

   } // Server ������

   public static void main(String[] args) {

      new Server().server();
   } // static main

   public void server() {

      ServerSocket serverSocket = null;
      Socket clientSocket = null;

      InetAddress clientIP;
      int clientPort;

      try {

         serverSocket = new ServerSocket(1001); // �������ϻ���
         System.out.println("Server Ready...");

         while (true) {
            clientSocket = serverSocket.accept(); // Ŭ���̾�Ʈ ���ϰ� ����� ������ ���
            System.out.println(
                  "[" + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + "]" + "���� �����Ͽ����ϴ�.");

            
            
            //server�� clinet list�� ������ client ��� �����
            clientIP = clientSocket.getInetAddress();
            clientPort = clientSocket.getPort();
            this.ta.append(clientIP.toString() + " " + "[" + String.valueOf(clientPort) + "] ���� �����Ͽ����ϴ�\n.");

            ServerR serverR = new ServerR(clientSocket, client); // ����� Ŭ���̾�Ʈ�� ������ ������ 1:1 ��Ī
            serverR.start();
         }

      } catch (IOException e) {
        // e.printStackTrace();

      }

   }

}