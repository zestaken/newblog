package com.zestaken.malldemo.api;

/**
 *常用api返回对象接口
 * @author zestaken
 */
public interface IErrorCode {
    /**
     * 返回码
     */
    long getCode();

    /**
     * 返回信息
     */
    String getMessage();
}
