package p1;

public class testInheritance {

	protected int x;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class A {
	protected int n;
}

class B extends A {
	public void m1() {
		A a = new A();
		a.n = 100;
	}
}