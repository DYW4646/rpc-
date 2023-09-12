package org.example.exceptions;

/**
 * Created with IntelliJ IDEA.
 * PackageName: org.example.exceptions
 *
 * @author: YW
 * @description:
 * @data: 2023/9/9 16:16
 */
public class NetworkException extends RuntimeException{
    public NetworkException(){}
    public NetworkException(Throwable cause){
        super(cause);
    }
}
