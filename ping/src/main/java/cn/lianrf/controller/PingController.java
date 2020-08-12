package cn.lianrf.controller;

import cn.lianrf.vo.Result;
import cn.lianrf.vo.ResultUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version: v1.0
 * @date: 2020/7/27
 * @author: lianrf
 */
@RestController
@RequestMapping
public class PingController {

    private static AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);

    @PostMapping("iframe.ashx")
    public String ping(String callback) throws InterruptedException {
            Thread.sleep(300L);
            String build = null;
            Random random = new Random();
            Result.Item item= new Result.Item();
            item.setIp("154.195.175.176");
            item.setIpaddress("南非");
            item.setTtl("51");
            item.setBytes("32");
            if (ATOMIC_INTEGER.get() < 100) {
                int i = random.nextInt(200) + 180;
                if(i>=280){
                    Thread.sleep(1000L);
                    build= ResultUtil.build(callback, Result.fail());
                }else {
                    if("jQuery111301668980712428878_1595901135983".equals(callback)){
                        item.setResponsetime(1);
                    }else {
                        item.setResponsetime(i);
                    }
                    build = ResultUtil.build(callback, Result.ok(item));
                }

            } else if (ATOMIC_INTEGER.get() < 170) {
                int i = random.nextInt(200) + 350;
                if(i>=450){
                    Thread.sleep(1000L);
                    build= ResultUtil.build(callback, Result.fail());
                }else {
                    item.setResponsetime(i);
                    build = ResultUtil.build(callback, Result.ok(item));
                }
            }else {
                Thread.sleep(2000L);
                build = ResultUtil.build(callback, Result.fail2(item));
            }
            ATOMIC_INTEGER.incrementAndGet();
            return build;
    }
}
