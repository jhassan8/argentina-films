package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamTest {

	private static List<User> users;

	private static void setUsers() {
		users = new ArrayList<>();
		users.add(new User(1, "name1"));
		users.add(new User(2, "name2"));
		users.add(new User(3, "name3"));
		users.add(new User(9, "name9"));
		users.add(new User(9, "name9"));
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
		flatMap();
		peek();
		count();
		skipAndLimit();
		sorted();
		minAndMax();
		distinct();
		allMatchAnyMatchAndNoneMatch();
		sumAverageAndRange();
		reduce();
		joining();
		toSet();
		summarizingDouble();
		partitioningBy();
		groupingBy();
		mapping();
		parallelStream();
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

	// 4_ findFirst
	public static void findFirst() {
		System.out.println("----------- findFirst");
		setUsers();
		User user = users.stream().filter(e -> e.getId() == 7).findFirst().orElse(null);
		System.out.println(user.getId() + " - " + user.getName());
	}

	// 5_ flatMap
	// junta varios arrays en uno *
	public static void flatMap() {
		System.out.println("----------- flatMap");
		List<List<String>> nameLists = new ArrayList<>(
				Arrays.asList(new ArrayList<>(Arrays.asList("name1", "name2", "name3")),
						new ArrayList<>(Arrays.asList("name4", "name5", "name6"))));
		List<String> names = nameLists.stream().flatMap(e -> e.stream()).collect(Collectors.toList());

		names.forEach(e -> System.out.println(e));
	}

	// 6_ peek
	// similar a foreach pero tiene la opcion de ser usada en el medio de methodos
	// stream, sin tener que ser el methodo final
	public static void peek() {
		System.out.println("----------- peek");
		setUsers();
		List<User> users2 = users.stream().peek(e -> e.setName(e.getName() + " surname" + e.getId()))
				.filter(e -> e.getId() > 5).collect(Collectors.toList());
		users2.forEach(e -> System.out.println(e.getId() + " - " + e.getName()));
	}

	// 7_ count
	public static void count() {
		System.out.println("----------- count");
		setUsers();
		long count = users.stream().filter(e -> e.getId() < 3).count();
		System.out.println(count);
	}

	// 8_ skip and limit
	public static void skipAndLimit() {
		System.out.println("----------- skip and limit");
		String[] abc = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
				"t", "u", "v", "w", "x", "y", "z" };
		List<String> abcFilter = Arrays.stream(abc).skip(2).limit(4).collect(Collectors.toList());
		abcFilter.forEach(e -> System.out.println(e));
	}

	// 9_ sorted
	public static void sorted() {
		System.out.println("----------- sorted");
		setUsers();
		users = users.stream().sorted(Comparator.comparing(User::getName)).collect(Collectors.toList());
		showUsers();
	}

	// 10_ min and max
	public static void minAndMax() {
		setUsers();
		System.out.println("----------- min and max");
		User userMin = users.stream().min(Comparator.comparing(User::getId)).orElse(null);
		System.out.println(userMin.getId());
		User userMax = users.stream().max(Comparator.comparing(User::getId)).orElse(null);
		System.out.println(userMax.getId());
	}

	// 11_ distinct
	public static void distinct() {
		System.out.println("----------- distinct");
		String[] abc = { "a", "a", "b", "c", "d", "e", "f", "f" };
		Arrays.stream(abc).distinct().collect(Collectors.toList()).forEach(e -> System.out.println(e));
	}

	// 12_ allMatch, anyMatch and noneMatch
	public static void allMatchAnyMatchAndNoneMatch() {
		System.out.println("----------- allMatch, anyMatch and noneMatch");
		List<Integer> numbers = Arrays.asList(100, 300, 400, 500, 700);

		boolean allMatch = numbers.stream().allMatch(e -> e > 300);
		System.out.println(allMatch);

		boolean anyMatch = numbers.stream().anyMatch(e -> e > 300);
		System.out.println(anyMatch);

		boolean noneMatch = numbers.stream().noneMatch(e -> e < 100);
		System.out.println(noneMatch);
	}

	// 13_ sum, average and range
	public static void sumAverageAndRange() {
		System.out.println("----------- sum, average and range");
		setUsers();

		double result = users.stream().mapToInt(User::getId).average().orElse(0);
		System.out.println(result);

		result = users.stream().mapToInt(User::getId).sum();
		System.out.println(result);

		System.out.println(IntStream.range(5, 10).sum());
	}

	// 14_ reduce
	public static void reduce() {
		System.out.println("----------- reduce");
		setUsers();

		int sum = users.stream().mapToInt(User::getId).reduce(0, Integer::sum);
		System.out.println(sum);
	}

	// 15_ joining
	public static void joining() {
		System.out.println("----------- joining");
		setUsers();

		String names = users.stream().map(User::getName).collect(Collectors.joining(", ")).toString();
		System.out.println(names);
	}

	// 16_ toSet
	public static void toSet() {
		System.out.println("----------- toSet");
		setUsers();

		Set<String> setNames = users.stream().map(User::getName).collect(Collectors.toSet());
		setNames.forEach(e -> System.out.println(e));
	}

	// 17_ summarizingDouble
	public static void summarizingDouble() {
		System.out.println("----------- summarizingDouble");
		setUsers();

		DoubleSummaryStatistics statistics = users.stream().collect(Collectors.summarizingDouble(User::getId));
		System.out.println(statistics.getAverage() + " " + statistics.getMax() + " " + statistics.getMin() + " "
				+ statistics.getCount() + " " + statistics.getSum());

		DoubleSummaryStatistics statistics1 = users.stream().mapToDouble(User::getId).summaryStatistics();
		System.out.println(statistics1.getAverage() + " " + statistics1.getMax() + " " + statistics1.getMin() + " "
				+ statistics1.getCount() + " " + statistics1.getSum());
	}

	// 18_ partitioningBy
	public static void partitioningBy() {
		System.out.println("----------- partitioningBy");

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		Map<Boolean, List<Integer>> greaterThanFive = numbers.stream().collect(Collectors.partitioningBy(e -> e > 5));

		greaterThanFive.get(true).forEach(e -> System.out.println(e));
		greaterThanFive.get(false).forEach(e -> System.out.println(e));
	}

	// 19_ groupingBy
	public static void groupingBy() {
		System.out.println("----------- groupingBy");
		List<String> names = Arrays.asList("Jose", "Adrian", "Pablo", "Pedro", "Alejandro", "Jhon", "Paraguayo");

		Map<Character, List<String>> alphabethic = names.stream()
				.collect(Collectors.groupingBy(e -> new Character(e.charAt(0))));

		alphabethic.get('A').forEach(e -> System.out.println(e));
		alphabethic.get('J').forEach(e -> System.out.println(e));
		alphabethic.get('P').forEach(e -> System.out.println(e));
	}

	// 20_ mapping
	public static void mapping() {
		System.out.println("----------- mapping");
		setUsers();

		List<String> persons = users.stream().collect(Collectors.mapping(User::getName, Collectors.toList()));
		persons.forEach(e -> System.out.println(e));
	}

	// 21_ parallelStream
	private static String toUpperCaseSleep(String name) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return name.toUpperCase();
	}

	public static void parallelStream() {
		System.out.println("----------- parallelStream");
		setUsers();

		long startTime = System.currentTimeMillis();
		users.stream().forEach(e -> toUpperCaseSleep(e.getName()));
		long endTime = System.currentTimeMillis();
		System.out.println("forEeach " + (endTime - startTime));

		startTime = System.currentTimeMillis();
		users.forEach(e -> toUpperCaseSleep(e.getName()));
		endTime = System.currentTimeMillis();
		System.out.println("stream forEeach " + (endTime - startTime));

		startTime = System.currentTimeMillis();
		users.parallelStream().forEach(e -> toUpperCaseSleep(e.getName()));
		endTime = System.currentTimeMillis();
		System.out.println("parallelStream forEeach " + (endTime - startTime));

	}

}
