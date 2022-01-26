package com.jiang.test;

import com.jiang.mapper.EmployeeMapper;
import com.jiang.pojo.Employee;
import com.jiang.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;



@SpringBootTest
public class SpringbootApplicationTests {



   /* public static void main(String[] args) {

        SpeedLimitThread t1 = new SpeedLimitThread(60L, 10L);
        t1.setName("A");
        t1.start();

        SpeedLimitThread t2 = new SpeedLimitThread(30L, 10L);
        t2.setName("B");
        t2.start();
    }*/

    private Jedis jedis;

    @BeforeEach
    public void before(){
        jedis = new Jedis("127.0.0.1",6379);
    }

    /**
     * ① 生成验证码
     *
     * @return
     */
    public String getCode() {
        StringBuilder sb = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i=0;i<6;i++){
            //nextInt(int n) 返回 0 到 n 之间的均匀分布的 int （包括 0，不包括 n）
            int randomNum = secureRandom.nextInt(10);
        }
        return sb.toString();
    }

    /**
     * ② 发送验证码：每个手机号每天只能发送 3 次，并将验证码存储到 Redis 中
     *
     * @param phone       手机号
     * @param phoneExpire 手机号过期时间
     * @param codeExpire  验证码过期时间
     */
    public void verifyPhone(String phone, int phoneExpire, int codeExpire) {
        // 手机发送次数的 key
        String phoneCountKey = "verifyCode:" + phone + ":count";
        // 每个手机号每天只能发送 3 次
        String phoneCount = jedis.get(phoneCountKey);
        if (null == phoneCount) {// 第一次发送
            jedis.setex(phoneCountKey, phoneExpire, "1");
        } else if (Integer.parseInt(phoneCount) <= 2) {
            jedis.incr(phoneCountKey);
        } else {
            throw new RuntimeException(phone + "，您今天发送验证码次数已经超过了 3 次");
        }
        // 验证码的 key
        String codeKey = "verifyCode:" + phone + ":code";
        // 保存验证码
        String vCode = jedis.get(codeKey);
        if (null == vCode) {
            jedis.setex(codeKey, codeExpire, getCode());
        }
    }

    /**
     * ③ 校验验证码
     *
     * @param phone     手机号
     * @param inputCode 用户输入的验证码
     */
    public void verifyCode(String phone, String inputCode) {
        // 验证码的 key
        String codeKey = "verifyCode:" + phone + ":code";
        // 保存验证码
        String vCode = jedis.get(codeKey);
        if (inputCode.equalsIgnoreCase(vCode)) {
            System.out.println("验证码校验成功");
        } else {
            throw new RuntimeException(phone + "，您输入的验证码错误，请重新检查后再输入");
        }
    }




    @Test
    public void close() {
        if (jedis != null) {
            jedis.close();
        }
    }




    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void test1(){
        System.out.println("employeeRepository="+employeeRepository);
        /*Employee employee = new Employee();

        employee.setGender("女");
        employee.setName("网文");

        employeeRepository.save(employee);*/
        List<Employee> employeeList = employeeRepository.findAll();

        System.out.println("employeeList = "+employeeList);
    }

    @Test
    public void test(){
        Employee employee = new Employee();

        employee.setGender("男");
        employee.setName("张三");

        employeeMapper.save(employee);

        System.out.println("employee = " + employee);


    }

    @Test
    public void test2(){
        String generatedFileName = UUID.randomUUID().toString().replace("-", "");
        System.out.println(generatedFileName);
    }




}
