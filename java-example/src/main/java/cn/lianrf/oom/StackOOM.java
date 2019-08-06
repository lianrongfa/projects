package cn.lianrf.oom;

/**
 * -Xss1m
 * 栈溢出
 */
public class StackOOM {
	private int a=0;
	public void test(){
		a++;
		test();
	}
	public static void main(String[] args) {
		StackOOM s = new StackOOM();
		try {
			s.test();
		} catch (Exception e) {
			System.out.println(s.a);
			e.printStackTrace();
		}
		
	}
}
