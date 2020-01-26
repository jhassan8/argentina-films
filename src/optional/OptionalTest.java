package optional;

import java.util.Optional;

public class OptionalTest {

	public static void main(String[] args) {
		isPresent(null);
	}
	
	public static void testOptional(String name) {
		System.out.println(name.length());
	};
	
	public static void createOptional() {
		Optional<String> optional = Optional.empty();
		optional.get();
	}
	
	// 1_ orElse
	public static void orElseOptional(String name) {
		Optional<String> optional = Optional.ofNullable(name);
		//Optional<String> optional1 = Optional.of(name);
		
		String nameOfNulleable = optional.orElse("empty");
		//String nameOf = optional1.orElse("empty");
		
		System.out.println(nameOfNulleable);
		//System.out.println(nameOf);
	}
	
	// 2_ orElseThrow
	public static void orElseThrow(String name) {
		Optional<String> optional = Optional.ofNullable(name);
		optional.orElseThrow(NullPointerException::new);
	}
	
	// 3_ isPresent
	public static void isPresent(String name) {
		Optional<String> optional = Optional.ofNullable(name);
		System.out.println(optional.isPresent());
	}
}
