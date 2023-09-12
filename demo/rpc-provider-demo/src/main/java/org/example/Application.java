package org.example;

import org.example.impl.HelloRpcImpl;

/**
 * Created with IntelliJ IDEA.
 * PackageName: org.example.impl.org.example
 *
 * @author: YW
 * @description:
 * @data: 2023/9/8 16:41
 */
public class Application {
    public static void main(String[] args) {
        ServiceConfig<HelloRPC> service =new ServiceConfig<>();
        service.setInterface(HelloRPC.class);
        service.setRef(new HelloRpcImpl() {
        });



        RpcBootstrap.getInstance()
                //应用名称
                .application("first-rpc-provider")
                //配置注册
                .registry(new RegistryConfig("zookeeper://127.0.0.1:2181"))
                .protocol(new ProtocolConfig("jdk"))
                //发布
                .publish(service)
                //启动
                .start();
                //还可以序列化协议，压缩方式
    }









}
