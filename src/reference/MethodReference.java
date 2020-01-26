package reference;

import java.util.ArrayList;
import java.util.List;

public class MethodReference {

	public static void main(String[] args) {

		MethodReference THIS = new MethodReference();

		// 1_ reference to static method
		Work work = new Work() {
			@Override
			public void action() {
				User.methodReferenceStatic();
			}
		};
		
		Work workL = () -> User.methodReferenceStatic();
		
		Work workMR = User::methodReferenceStatic;
		workMR.action();
		
		// 2_ reference to instance method
		User user = new User("name");
		
		Work workL2 = () -> user.referenceInstanceMethod();
		
		Work workMR2 = user::referenceInstanceMethod;
		workMR2.action();
		
		// 3_ reference to instance method of particular object type
		WorkString workString = (name) -> name.toUpperCase();
		
		WorkString workStringRM = String::toUpperCase;
		System.out.println(workStringRM.action("name"));
	
		List<User> users = new ArrayList<>();
		users.add(new User("name1"));
		users.add(new User("name2"));
		users.add(new User("name3"));
		
		users.forEach(name -> name.showName());
		
		users.forEach(User::showName);
		
		// 4_ reference to constructor
		IUser iUser = new IUser() {
			@Override
			public User create(String name) {
				return new User(name);
			}
		};
		
		IUser userL = (name) -> new User(name);
		
		IUser userRM = User::new;
	}

}
