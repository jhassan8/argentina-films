package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

	private static List<User> users;

	private static void setUsers() {
		users = new ArrayList<>();
		users.add(new User(1, "name1"));
		users.add(new User(2, "name2"));
		users.add(new User(3, "name3"));
		users.add(new User(4, "name4"));
		users.add(new User(5, "name5"));
		users.add(new User(6, "name6"));
		users.add(new User(7, "name7.1"));
		users.add(new User(7, "name7.2"));
	}

	private static void showUsers() {
		users.forEach(e -> System.out.println(e.getId() + " - " + e.getName()));
	}

	public static void main(String[] args) {
		forEeach();
		map();
		filter();
		findFirst();
	}

	// 1_ forEach
	public static void forEeach() {
		System.out.println("----------- forEach");
		setUsers();
		users.stream().forEach(e -> e.setName(e.getName() + " surname" + e.getId()));
		showUsers();
	}

	// 2_ map
	public static void map() {
		System.out.println("----------- map");
		setUsers();
		List<String> userString = users.stream().map(e -> e.getName()).collect(Collectors.toList());
		userString.forEach(e -> System.out.println(e));
	}

	// 3_ filter
	public static void filter() {
		System.out.println("----------- filter");
		setUsers();
		List<User> userFilter = users.stream().filter(e -> !e.getName().equals("name3")).filter(e -> e.getId() < 7)
				.collect(Collectors.toList());
		showUsers();
		System.out.println("---");
		userFilter.forEach(e -> System.out.println(e.getId() + " - " + e.getName()));
	}
	
	// 3_ findFirst
	public static void findFirst() {
		System.out.println("----------- findFirst");
		setUsers();
		User user = users.stream().filter(e -> e.getId() == 7).findFirst().orElse(null);
		System.out.println(user.getId() + " - " + user.getName());
	}

}
