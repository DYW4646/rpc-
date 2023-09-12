package org.example.utils.Zookeeper;

/**
 * Created with IntelliJ IDEA.
 * PackageName: org.example.utils.Zookeeper
 *
 * @author: YW
 * @description:
 * @data: 2023/9/11 19:41
 */
public class ZookeeperNode {
    private String nodePath;
    private byte[] data;


    public ZookeeperNode(String nodePath, byte[] data) {
        this.nodePath = nodePath;
        this.data = data;
    }


    public ZookeeperNode() {
    }

    public String getNodePath() {
        return nodePath;
    }

    public void setNodePath(String nodePath) {
        this.nodePath = nodePath;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
