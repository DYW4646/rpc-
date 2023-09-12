package org.example;

/**
 * Created with IntelliJ IDEA.
 * PackageName: org.example
 *
 * @author: YW
 * @description:
 * @data: 2023/9/8 18:50
 */
public class Application {
    public static void main(String[] args) {
        //想尽办法获得代理对象
        ReferenceConfig<HelloRPC> reference=new ReferenceConfig;
        reference.setInterface(HelloRPC.class);
        //链接注册中心
        //拉去服务列表
        //选择服务并选择
        //发送请求
        //


        RpcBootstrap.getInstance().application("firdt-rpc-consumer")
                .registry(new RegistryConfig("zookeeper://127.0.0.1:2181")).reference(reference);

        HelloRPC helloRPC=reference.get();
        helloRPC.sayHi("你好");

    }
}
