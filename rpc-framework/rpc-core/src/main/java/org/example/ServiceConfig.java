package org.example;

/**
 * Created with IntelliJ IDEA.
 * PackageName: org.example
 *
 * @author: YW
 * @description:
 * @data: 2023/9/8 20:18
 */
public class ServiceConfig<T> {
    //接口
    private Class<T> interfaceProvider;
//    具体实现
    private Object ref;

    public Class<T> getInterface() {
        return interfaceProvider;
    }

    public void setInterface(Class<T> interfaceProvider) {
        this.interfaceProvider = interfaceProvider;
    }

    public Object getRef() {
        return ref;
    }

    public void setRef(Object ref) {
        this.ref = ref;
    }
}
