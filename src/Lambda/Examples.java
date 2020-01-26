package Lambda;

public class Examples implements Name{
	
	// 1
	public void one() {
		Name name = new Name() {
			@Override
			public String name() {
				return "name";
			}
		};
		System.out.println(name.name());
		
		Name nameLambda = () -> "name lambda";
		System.out.println(nameLambda);
	}
	
	// 2
	public void two() {
		Add add = new Add() {
			@Override
			public int add(int a, int b) {
				return a + b;
			}
		};
		System.out.println(add.add(2, 2));
		
		Add addLambda = (a, b) -> a + b;
		System.out.println(addLambda.add(2, 5));
		
		Add addLambda2 = (a, b) -> {
			a = b * b;
			return a + b;
		};
		System.out.println(addLambda2.add(2, 5));
	}
	
	// 3
	public void three() {
		System.out.println(this.defaultName());
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

}
