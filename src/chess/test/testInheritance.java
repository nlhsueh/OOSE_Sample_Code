package test;

public class testInheritance {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee e = new Employee();
		e.setName("nick");
		System.out.println(e.getName());

	}

}

class People {
	private String name;
	public void setName(String n) {
		name = n;
	}
	public String getName() {
		return name;
	}
	
	public People(){
		System.out.println("People is called");
	}
}

class Employee extends People {
}
