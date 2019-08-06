package cn.lianrf.springevent;

import org.springframework.context.ApplicationEvent;

import java.util.List;

/**
 * Created by lianrongfa on 2017/12/20.
 */
public class MyEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */

    private List<String> resource;

    public MyEvent(List<String> source) {
        super(source);
        this.resource=source;
    }



    public synchronized List<String> getResource() {
        return this.resource;
    }
}
