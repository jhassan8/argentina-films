package Lambda;

// functional interface, only one method
// this annotation is optional
@FunctionalInterface
public interface Name {

	String name();
	
	// default method if functional interface is not overwritten
	default String defaultName() {
		return "default name";
	}

}
