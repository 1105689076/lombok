package com.jiang.service;

public interface BusinessService {

    /**
     * 控制单元
     *
     * @param id
     */
    void speedLimit(String id, String name, Long limitSeconds, Long count);


    /**
     * 业务操作
     *
     * @param id
     * @param name
     * @param incr
     */
    void business(String id, String name, Long incr);
}
