package org.example.netty;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.nio.file.Watchable;

/**
 * Created with IntelliJ IDEA.
 * PackageName: org.example
 *
 * @author: YW
 * @description:
 * @data: 2023/8/29 20:20
 */
public class MyWatcher implements Watcher {
    @Override
    public void process(WatchedEvent event) {
        //判断事件类型
        if(event.getType()== Event.EventType.None){
            if(event.getState()==Event.KeeperState.SyncConnected){
                System.out.println("zookeeper连接成功");
            } else if (event.getState()==Event.KeeperState.AuthFailed) {
                System.out.println("认证失败");
            }else if (event.getState()==Event.KeeperState.Disconnected){
                System.out.println("连接失败");
            }
        } else if (event.getType()==Event.EventType.NodeCreated) {
            System.out.println(event.getPath()+"创建");
        }else if (event.getType()==Event.EventType.NodeDeleted) {
            System.out.println(event.getPath()+"删除");
        }else if (event.getType()==Event.EventType.NodeDataChanged) {
            System.out.println(event.getPath()+"数据变化");
        }else if (event.getType()==Event.EventType.NodeChildrenChanged) {
            System.out.println(event.getPath()+"子节点变化");
        }
    }
}
