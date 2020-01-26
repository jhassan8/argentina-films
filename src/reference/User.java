package reference;

public class User {

	private String name;

	public User(String name) {
		this.name = name;
	}

	public static void methodReferenceStatic() {
		System.out.println("Test reference to static method");
	}

	public void referenceInstanceMethod() {
		System.out.println("Test Reference to instance method");
	}

	public void showName() {
		System.out.println(name);
	}

}
