package org.example;

/**
 * Created with IntelliJ IDEA.
 * PackageName: PACKAGE_NAME
 *
 * @author: YW
 * @description:
 * @data: 2023/9/8 15:30
 */
public interface HelloRPC {
    //通用接口，server和client都需要依赖
    String sayHi(String msg);
}
