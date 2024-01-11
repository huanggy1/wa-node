package com.universe.ads.node.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        System.out.println("连接成功");

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String receive = in.readLine();  // 接收客户端信息
                System.out.println("收到客户端消息：" + receive);

                if ("exit".equals(receive)) {
                    break;
                }
                out.println("服务端：i love u too ");  // 发送消息至客户端
            }

            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
