package com.universe.ads.node.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class Ip implements Serializable {

    private String ipAddress;
    private Integer port;
    private String msg ;

}
