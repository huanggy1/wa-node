package com.universe.ads.node.socket;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  socket 客户端
 *  https://baijiahao.baidu.com/s?id=1767913015919523836&wfr=spider&for=pc
 */
@Slf4j
@Data
public class SocketClient {

    private static Map<String,SocketClient> CLIENT_MAP = new ConcurrentHashMap<>();

    public static void add(String serverIP,int port,SocketClient socketClient){
        CLIENT_MAP.put(serverIP+":"+port,socketClient);
    }

    public static void remove(String serverIP,int port){
        SocketClient socketClient = CLIENT_MAP.remove(serverIP + ":" + port);
        socketClient.close();
    }

    public static SocketClient getClient(String serverIP,int port){
        SocketClient socketClient = CLIENT_MAP.get(serverIP + ":" + port);
        if (socketClient == null) {
            socketClient = new SocketClient();
            socketClient.connection(serverIP,port);
        }
        add(serverIP,port,socketClient);
        return socketClient;
    }


    private BufferedReader in = null;
    private PrintWriter out = null;
    private Socket socket = null;

    public void connection(String serverIP,int port) {
        try {
            socket = new Socket(serverIP, port);
            log.info("已连接服务器：{}:{}",serverIP,port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            new Thread(()->{
                try {
                    while (true){
                        String msg = in.readLine();
                        log.info("收到服务端的消息msg={}",msg);
                    }
                } catch (IOException e) {
                   log.error("接受服务端消息报错");
                }
            }).start();
        }catch (Exception e){
            remove(serverIP,port);
            log.error("Socket客户端报错",e);
        }

    }

    public void send(String msg) {
        //第一次发消息
        out.println(msg);
    }

    private void close() {
        try {
            in.close();
        } catch (IOException e) {

        }
        try {
            out.close();
        } catch (Exception e) {

        }
        try {
            socket.close();
        } catch (IOException e) {

        }
    }

}