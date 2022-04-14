package com.lianrf.tierexp.examples;

import com.lianrf.tierexp.RunEnvironment;

/**
 * TODO
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/4/8 6:00 下午
 */
public class ThDemo {
    public static void main(String[] args) {


        RunEnvironment runEnvironment = RunEnvironment.get();

        System.out.println(runEnvironment);

        RunEnvironment runEnvironment2 = RunEnvironment.get();

        System.out.println(runEnvironment2);



        RunEnvironment.clear();

        RunEnvironment ss = RunEnvironment.get();

        System.out.println(ss)

        ;
        
    }
}
