package com.universe.ads.node.controller;

import com.universe.ads.node.common.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping("/test001")
    public R list() {
        return R.ok(1111);
    }

}
