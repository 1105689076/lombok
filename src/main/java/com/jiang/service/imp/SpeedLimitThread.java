package com.jiang.service.imp;

import com.jiang.service.BusinessService;

public class SpeedLimitThread extends Thread{
    /**
     * 限制时间
     */
    private final Long limitSeconds;
    /**
     * 限制次数
     */
    private final Long count;

    public SpeedLimitThread(Long limitSeconds, Long count) {
        this.limitSeconds = limitSeconds;
        this.count = count;
    }

   private final BusinessService businessService = new BusinessServiceImpl();

    @Override
    public void run() {
        while (true){
            //void speedLimit(String id, String name, Long limitSeconds, Long count);
           businessService.speedLimit(String.valueOf(Thread.currentThread().getId()), currentThread().getName(), limitSeconds, count);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
