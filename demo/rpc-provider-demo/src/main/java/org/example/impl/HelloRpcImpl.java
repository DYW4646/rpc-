package org.example.impl;

import org.example.HelloRPC;

/**
 * Created with IntelliJ IDEA.
 * PackageName: org.example.impl
 *
 * @author: YW
 * @description:
 * @data: 2023/9/8 15:35
 */
public class HelloRpcImpl implements HelloRPC {
    @Override
    public String sayHi(String msg) {
        return "hi consumer"+msg;
    }
}
