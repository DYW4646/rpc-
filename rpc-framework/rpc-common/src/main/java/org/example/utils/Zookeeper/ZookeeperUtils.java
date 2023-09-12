package org.example.utils.Zookeeper;

import org.apache.zookeeper.*;
import org.example.Constant;
import org.example.exceptions.ZookeeperException;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created with IntelliJ IDEA.
 * PackageName: org.example.utils
 *
 * @author: YW
 * @description:
 * @data: 2023/9/9 16:08
 */
public class ZookeeperUtils {
    //默认配置连接
    public static ZooKeeper createZookeeper(){
        String connectString= Constant.DEFAULT_ZK_CONNECT;
        int timeout=Constant.TIME_OUT;
        return createZookeeper(connectString,timeout);
    }
    //自行配置

/*
 * @date: 2023/9/11 20:24
 * @author YW
 * @path org.example.utils.Zookeeper.ZookeeperUtils
 * 
 * @description:
 * @param: [java.lang.String, int] 
 * @return: org.apache.zookeeper.ZooKeeper 
*/ 
    public static ZooKeeper createZookeeper(String connectString,int timeout){

        CountDownLatch countDownLatch=new CountDownLatch(1);
        String result = null;
        try {
            //创建实例zookeeper，连接
            final ZooKeeper zooKeeper=new ZooKeeper(connectString, timeout, event -> {
                if(event.getState()== Watcher.Event.KeeperState.SyncConnected){
                    System.out.println("客户端连接成功");
                    countDownLatch.countDown();
                }
            });
            countDownLatch.await();
            return zooKeeper;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

        }
        return  null;
    }

/*
 * @date: 2023/9/11 20:53
 * @author YW
 * @path org.example.utils.Zookeeper.ZookeeperUtils
 * @description:6
 * @param:
org.apache.zookeeper.ZooKeeper,
org.example.utils.Zookeeper.ZookeeperNode,
org.apache.zookeeper.Watcher,
org.apache.zookeeper.CreateMode
 * @return: boolean false:异常或者已经存在
*/
    public  static  Boolean createNode(ZooKeeper zooKeeper,ZookeeperNode node,Watcher watcher,CreateMode createMode) {
        try {
            if (zooKeeper.exists(node.getNodePath(), watcher) == null) {
                String result = zooKeeper.create(node.getNodePath(), node.getData(),
                        ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode);

                System.out.println("根节点，成功创建");
                return true;
            }

        } catch (KeeperException | InterruptedException e) {
            throw new ZookeeperException();
            //                log.error()
        }
        return false;
    }
    /*
     * @author @date:  2023/9/11 21:06
     * @description:关闭zookeeper
     * @param:
    [org.apache.zookeeper.ZooKeeper]
     * @return: void
    */
    public static void close(ZooKeeper zooKeeper){
        try {
            zooKeeper.close();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    /*
     * @author @date:  2023/9/12 11:14
     * @description:判断节点是否存在
     * @param:
    [org.apache.zookeeper.ZooKeeper,zookeeper实例
    *  java.lang.String, 节点的路径
    * org.apache.zookeeper.Watcher 监视器
    * ]
     * @return: boolean 存在or不存在
    */
    public  static boolean exists(ZooKeeper zooKeeper,String node,Watcher watcher){
        try {
            return zooKeeper.exists(node,watcher)!=null;
        } catch (KeeperException |InterruptedException e) {
            throw new ZookeeperException(e);
        }
}}
