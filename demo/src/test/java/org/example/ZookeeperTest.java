package org.example;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * PackageName: org.example
 *
 * @author: YW
 * @description:
 * @data: 2023/8/29 16:45
 *
 * 1.before没加要记得；
 * 2,create函数里面的path不能是已有的（我当时已经有了ydlclass和zookeeper，提示
 * java.lang.RuntimeException: org.apache.zookeeper.KeeperException$NodeExistsException: KeeperErrorCode = NodeExists for /ydlclass）
 * 3,version--乐观锁
 */
public class ZookeeperTest {
    ZooKeeper zooKeeper;
    @Before
    public void createZK()  {
        String connectString="127.0.0.1:2181";
        int timeout=10000;

        try {
            zooKeeper=new ZooKeeper(connectString,timeout,null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public  void testCreatePNode() {
        String result = null;
        try {
            result = zooKeeper.create("/ydlclass", "hello".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(zooKeeper!=null){
                    zooKeeper.close();
                }
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
        System.out.println(result);
    }
    @Test
    public void testDeletePNode() {
        try {
            zooKeeper.delete("/ydlclass1",-1);

        } catch (KeeperException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(zooKeeper!=null){
                    zooKeeper.close();
                }
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }
    @Test
    public void testExistPNode() {
        try {
            Stat s = zooKeeper.exists("/ydlclass", null);

            zooKeeper.setData("/ydlclass","hi".getBytes(),-1);
            //acl
            System.out.println(s.getAversion());
            //节点数据
            System.out.println(s.getCversion());
            //节点的版本
            System.out.println(s.getVersion());
        } catch (KeeperException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(zooKeeper!=null){
                    zooKeeper.close();
                }
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }
}












