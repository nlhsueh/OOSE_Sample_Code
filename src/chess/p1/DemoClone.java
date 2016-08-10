package p1;

public class DemoClone {

	/**
	 * @param args
	 * @throws CloneNotSupportedException
	 */
	public static void main(String[] args) throws CloneNotSupportedException {
		// TODO Auto-generated method stub

		People x = new People("Jack");
		People y = (People) x.clone();
		System.out.println(x + ", " + x.name);
		System.out.println(y + ", " + y.name);

		Employee x2 = (Employee) new Employee("Jack");
		Employee y2 = x2.clone();
		System.out.println(x + ", " + x.name);
		System.out.println(y + ", " + y.name);

	}

}

class People implements Cloneable {
	String name;

	public People(String n) {
		name = n;
	}

	public People(People p) {
		this.name = p.name;
	}

	public Object clone() throws CloneNotSupportedException {
		return new People(name);
		// return (People) super.clone();
	}
}

class Employee extends People {

	public Employee(People p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	public Employee(Employee p) {
		super(p);
	}
}