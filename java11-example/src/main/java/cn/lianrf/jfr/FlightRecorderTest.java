package cn.lianrf.jfr;

import jdk.jfr.Description;
import jdk.jfr.Event;
import jdk.jfr.Label;

/**
 *
 * vm: -XX:StartFlightRecording=duration=1s,filename=recording.jfr
 *
 * @version: v1.0
 * @date: 2020/11/27
 * @author: lianrf
 */
public class FlightRecorderTest extends Event {
    @Label("Hello World")
    @Description("Helps the programmer getting started")
    static class HelloWorld extends Event {
        @Label("Message")
        String message;
    }

    public static void main(String[] args) {
        HelloWorld event = new HelloWorld();
        event.message = "hello, world!";
        event.commit();
    }
}
