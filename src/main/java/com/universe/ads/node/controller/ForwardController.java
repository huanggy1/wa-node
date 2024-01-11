package com.universe.ads.node.controller;

import cn.hutool.core.net.Ipv4Util;
import com.universe.ads.node.common.Ip;
import com.universe.ads.node.common.R;
import com.universe.ads.node.socket.SocketClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/forward")
public class ForwardController {

    @PostMapping("/action")
    public R action(@RequestBody Ip dto){
        SocketClient client = SocketClient.getClient(dto.getIpAddress(), dto.getPort());
        client.send(dto.getMsg());
        return R.ok();
    }

    @GetMapping("/ping")
    public R<String> ping(HttpServletRequest request){
        String clientIP = request.getRemoteAddr();
        log.info("ping = {}" , clientIP);
        return R.success("pong");
    }

}
