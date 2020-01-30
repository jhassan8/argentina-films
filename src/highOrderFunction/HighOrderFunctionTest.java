package highOrderFunction;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HighOrderFunctionTest implements SumI {

	public static void main(String[] args) {

		HighOrderFunctionTest highOrderTest = new HighOrderFunctionTest();

		// as function
		System.out.println(highOrderTest.sum(2, 2));
		// as interface
		System.out.println(highOrderTest.apply(2, 3));
		// as highOrderFunction
		SumI sumI = (a, b) -> a + b;
		System.out.println(highOrderTest.sumHighOrderFunction(sumI, 1, 5));

		// functional interface Function<T, R>
		Function<String, String> convertToMayus = e -> e.toUpperCase();
		highOrderTest.printMayus(convertToMayus, "test name");

		// functional interface BiFunction<T, U, R>
		// functional interface Predicate<T>
		List<Integer> numbers = Arrays.asList(-1, -2, -3, -4, -5, 6, 7, 8, 9, 10);
		BiFunction<List<Integer>, Predicate<Integer>, List<Integer>> filter = (l, p) -> l.stream()
				.filter(e -> p.test(e)).collect(Collectors.toList());
		System.out.println(filter.apply(numbers, e -> e > 0));

		// functional interface Consumer<T>
		List<String> names = Arrays.asList("Jhon", "Pepito", "Wilbert", "Alfred", "Jurio");
		highOrderTest.filter(names, e -> System.out.println(e), 6);
	}

	public int sum(int a, int b) {
		return a + b;
	}

	@Override
	public int apply(int a, int b) {
		return a + b;
	}

	public int sumHighOrderFunction(SumI sumI, int a, int b) {
		return sumI.apply(a, b);
	}

	public void printMayus(Function<String, String> function, String name) {
		System.out.println(function.apply(name));
	}

	public void filter(List<String> list, Consumer<String> consumer, int maxCharacters) {
		list.stream().filter(predicateLogic(maxCharacters)).forEach(consumer);
	}

	public Predicate<String> predicateLogic(int maxCharacters) {
		return e -> e.length() < maxCharacters;
	}

}
