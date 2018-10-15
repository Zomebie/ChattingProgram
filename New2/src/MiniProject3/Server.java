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
      Collections.synchronizedMap(client); // 해쉬맵에 동시적으로 접근이 불가하게

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

   } // Server 생성자

   public static void main(String[] args) {

      new Server().server();
   } // static main

   public void server() {

      ServerSocket serverSocket = null;
      Socket clientSocket = null;

      InetAddress clientIP;
      int clientPort;

      try {

         serverSocket = new ServerSocket(1001); // 서버소켓생성
         System.out.println("Server Ready...");

         while (true) {
            clientSocket = serverSocket.accept(); // 클라이언트 소켓과 연결될 때까지 대기
            System.out.println(
                  "[" + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + "]" + "에서 접속하였습니다.");

            
            
            //server의 clinet list에 접속한 client 목록 띄워줌
            clientIP = clientSocket.getInetAddress();
            clientPort = clientSocket.getPort();
            this.ta.append(clientIP.toString() + " " + "[" + String.valueOf(clientPort) + "] 에서 접속하였습니다\n.");

            ServerR serverR = new ServerR(clientSocket, client); // 연결된 클라이언트와 스레드 소켓을 1:1 매칭
            serverR.start();
         }

      } catch (IOException e) {
        // e.printStackTrace();

      }

   }

}