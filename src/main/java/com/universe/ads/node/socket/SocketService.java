package com.universe.ads.node.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.ServerSocket;
import java.net.Socket;


@Service
@Slf4j
public class SocketService {



    @Value("${socket.port:18806}")
    private int port;

    @PostConstruct
    public void startSocketServer() throws Exception {
        new Thread(()->{
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                log.info("服务器已经启动：127.0.0.1:" + port);
                while (true) {
                    Socket socket = serverSocket.accept();   // 接收客户端请求
                    new ServerThread(socket).start();  // 创建新线程发送信息
                }
            }catch (Exception e){
                log.error("socket 服务报错",e);
            }

        }).start();
    }


}
