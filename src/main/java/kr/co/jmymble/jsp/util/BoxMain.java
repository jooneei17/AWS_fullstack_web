package kr.co.jmymble.jsp.util;

import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import lombok.ToString;

public class BoxMain {
	public static void main(String[] args) {
//		Box<String> box = new Box<>();
//		box.push("1");
//		box.push("2");
//		box.push("3");
//		
//		box.unshift("4");
//		box.unshift("5");
//		box.unshift("6");
//		System.out.println(box);
//		
//		System.out.println(box.pop());
//		System.out.println(box.pop());
//		System.out.println(box.pop());
//		
//		
//		System.out.println(box.shift());
//		System.out.println(box.shift());
//		System.out.println(box.shift());
//		box.setItem("00");
//		
//		System.out.println(box);
//		System.out.println(box.getItem());
//		
//		Box<Fruit> fruitBox = new Box<>();
//		fruitBox.setItem(new Apple());
//		
//		Scanner scan = new Scanner(System.in);
//		int[] n = new int[12];
//		int[] num = new int[4];
//		
//		//12비트 입력받기
//		String number = scan.next(); 
//		
//		for(int i = 0; i < 12; i/=3) {
//			for(int j = 0; j < 3; j++) {
//				n[i] = number.charAt(i);
//				num[i] = 2^j * n[i];
//			}
//		}
//		System.out.println(num);
		
		
		
		

//		int num;
//		int octal; 
//		
//		
//		String binary = Integer.toBinaryString(num);
//		
//		System.out.print("12비트(bit)의 이진수를 입력하세요: ");
//		binary = scan.next();
//		
//		n0 = binary.charAt(0);
//		n1 = binary.charAt(1);
//		n2 = binary.charAt(2);
//		one = 2^2 * n0 + 2 * n1 + 1* n2;
//		
//		n3 = binary.charAt(3);
//		n4 = binary.charAt(4);
//		n5 = binary.charAt(5);
//		two= 2^2 * n3 + 2 * n4 + 1* n5;
//		
//		n6 = binary.charAt(6);
//		n7 = binary.charAt(7);
//		n8 = binary.charAt(8);
//		three= 2^2 * n6 + 2 * n7 + 1* n8;
//		
//		n9 = binary.charAt(9);
//		n10 = binary.charAt(10);
//		n11 = binary.charAt(11);
//		four =  2^2 * n9 + 2 * n10 + 1 * n11;
//		
//		octal = one + two + three + four;
//		
////		octal = Integer.parseInt(num, 2);
//		System.out.println("이진수 " + binary + "에 대응하는 8진수는  " + octal + " 이다. ");
			
	}

}

//@ToString
class Box<T> {
//	private T item;
//	
//	public void setItem(T item) {
//		this.item = item;
//	}
//	
//	public T getItem() {
//		return item;
//	}
	
	private List<T> items = new ArrayList<>();
	
	public void push(T item) {
		items.add(item);
	}
	
	public T pop() {
		return items.remove(items.size() - 1);
	}
	
	//0번째에 추가
	public void unshift(T item) {
		items.add(0, item);
	}
	
	//0번째 빼기
	public T shift() {
		return items.remove(0);
	}
	
	
}

abstract class Fruit{
	
}

class Apple extends Fruit {
	
}

class Grape extends Fruit {
	
}

