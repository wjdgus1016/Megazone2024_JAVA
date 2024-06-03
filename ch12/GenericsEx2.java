package ch12;

import java.util.ArrayList;

/**
 * 제한된 지네릭 클래스  => 지넥릭에 다형성 적용
 * 
 * 
 * 1. 클래스 관계에 대한 제한
 * 
 * Box<T> b = new Box<T>();
 * 
 * T 대신에 어떤것이라도 올 수 있음. Apple, Grape, Toy 등....
 * 제한을 두고 싶은데, 클래스 간의 관계를 통해서 제한을 둘 수 있음.
 * 
 * Object
 * Fruit
 * Aplle
 * 
 * 현재는 타입변수 T에 하나만 올 수 있고, 뭐든지 올 수 있음.
 * Fruit 를 상속 받는 것들만 T에 올 수 있도록 하고 싶다.( 제한 => 관계 제한 ) 
 * 
 * 
 * 타입변수 T에 하나만 올 수 있고, 뭐든지 올 수 있음.
 * class FruitBox<T> {
 * 		ArrayList<T> list = new ArrayList<T>();
 * }
 * 
 * 
 * Fruit 를 상속 받는 것들만 T에 올 수 있도록
 * ( T가 어떤 것이 올지는 모르겠지만, Fruit 를 상속 받은 것만 올 수 있음.
 * 	 따라서, 제한이 생김. Fruit 를 상속 받은 것만. => Apple, Grape
 *  )
 * class FruitBox<T extends Fruit> { => 다형성
 * 		컴파일 전
 * 		ArrayList<T> list = new ArrayList<T>();
 * 		컴파일 후
 * 		ArrayList<Fruit> list = new ArrayList<Fruit>();
 * }
 * 
 * 제한 적용 전의 기존 방법
 * class FruitBox<Apple> {
 * 		ArrayList<T> list = new ArrayList<T>();
 * }
 * 
 * 제한 적용 전의 기존 방법
 * class FruitBox<Grape> {
 * 		ArrayList<T> list = new ArrayList<T>();
 * }
 * 
 * 제한 적용 전의 기존 방법
 * class FruitBox<Fruit> {
 * 		ArrayList<T> list = new ArrayList<T>();
 * }
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 2. 인터페이스 구현에 대한 제한
 * 
 * T extends Fruit
 * T 에 온는 실제 타입 클래스에 인터페이스 구현을 강제시키고 싶음.
 * 
 * - 인터페이스의 작성시 구성 요소
 *   상수, 추상메소드(강제성 임.), 디폴트 메소드(강제성 아님.)
 *   
 * Fruit, Apple, Grape => 공통적인 요소인 먹다를 추상메소드로 강제성을 부여하고 싶음.
 * 
 * interface Eatable {}
 * 
 * T extends Fruit & Eatable
 * - Fruit 자신과 Fruit 를 상속 받은 것만 올 수 있도록 제한 
 * and
 * - Eatable interface 를 구현한 것만 올 수 있도록 제한.
 * 
 * 주의사항 : interface 를 구현시 implements 키워드 사용했지만,
 *         제네릭에서는 implements 사용하지 않고, extends 를 사용.
 *         
 * T extends Fruit : Fruit 자신과 Fruit 를 상속 받은 것만
 * T extends Eatable : Eatable interface 를 구현한 것만
 * 
 */

public class GenericsEx2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		/**
		 * 1. 클래스 관계에 대한 제한 실습 코드
		 */
		
		// 제네릭에 다형성이 적용되었다는 점을 고려함.
		// FruitBox2 에는 Apple, Grape 를 담을 수 있음.
		FruitBox2<Fruit2> fruitBox = new FruitBox2<Fruit2>();

		/**
		 *컴파일 전
		 * ArrayList<T> list = new ArrayList<T>();
		 * 
		 * 컴파일 후 => Apple, Grape 도 담을 수 있게 됨.
		 * ArrayList<Fruit2> list = new ArrayList<Fruit2>();
		 * 
		 */
		
		
		// Apple2 Box
		FruitBox2<Apple2> appleBox = new FruitBox2<Apple2>();
		/**
		 *컴파일 전
		 * ArrayList<T> list = new ArrayList<T>();
		 * 
		 * 컴파일 후 => Apple 만 담을 수 있게 됨. => 다형성이 적용 되지 않음.
		 * => ArrayList 에 저장할 수 있는 타입은 파생 클래스 타입.
		 * - Fruit를 담을 수 없고, Grape와 Apple 은 서로 관계가 없음.
		 * => 그래서, ArrayList 에는 Apple 만 담을 수 있음.
		 * ArrayList<Apple2> list = new ArrayList<Apple2>();
		 * 
		 */
		
		
		// Grape2 Box
		
		
		// 제네릭 제한이 다형성으로 적용되도록 했으므로,
		// 과일을 담을 때, 다형성이 적용되도록 담아야 함.
		// 각 Box 에 과일 담기
		fruitBox.add(new Fruit2());
		
		// 지네릭 제한 => 다형성 적용된 것을 확인
		fruitBox.add(new Apple2());
		fruitBox.add(new Grape2());
		
		// Apple 만 담을 수 있는 Box
		// 제네릭 클래스에서는 다형성을 적용 해지만,
		// 실제 T 에 대한 타입이 기반 클래스 타입이 아닌,
		// 파생 클래스 타입으로 지정했으므로, 
		// 정확하게는 다형성을 활용하고 있지 못하는 상태임.
		appleBox.add(new Apple2());
		//appleBox.add(new Fruit2());
		//appleBox.add(new Grape2());
		
		
		
		
		System.out.println("fruitBox - " + fruitBox);
		
		
		/**
		 * 2. 인터페이스 구현에 대한 제한 관련 코드
		 */
		
	}

}


// Box class : row type class => 제네릭 클래스를 적용.
class Box2<T> {
	ArrayList<T> list = new ArrayList<T>();
	void add(T item) { list.add(item); }
	T get(int i) { return list.get(i); }
	
	int size() { return list.size(); }
	public String toString() { return list.toString(); }
}

// 과일 Box : 클래스 간의 관계 제한 => 제네릭을 활용.
class FruitBox2<T extends Fruit2 & Eatable> extends Box2<T> {}


// 제네릭 제한용 인터페이스
interface Eatable {
	abstract void eat();
}


// Fruit 의 공통 기능(추상 메소드)을 강제 구현 => 제네릭을 활용
class Fruit2 implements Eatable {
	public String toString() { return "Fruit2"; }

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		
	}
}

// Fruit 의 파생 클래스
class Apple2 extends Fruit2 {
	public String toString() { return "Apple2"; }
}

class Grape2 extends Fruit2 {
	public String toString() { return "Grape2"; }
}

class Toy2 {
	public String toString() { return "Toy2"; }
}



