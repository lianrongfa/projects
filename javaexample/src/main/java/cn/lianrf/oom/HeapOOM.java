package cn.lianrf.oom;

import java.util.ArrayList;

/**
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * Created by lianrongfa on 2018/1/8.
 */
public class HeapOOM {
	static class OOMObject{}
	public static void main(String[] args) {
		ArrayList<OOMObject> list = new ArrayList<OOMObject>();
		while(true){
			list.add(new OOMObject());
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}
}
