
public class TestDivisors {
	public static void main(String[] args) {
		int numberToTest=2747115;
		Divisors divisors = new Divisors(numberToTest);
		System.out.println(divisors);
		System.out.println("The number of divisors of " + numberToTest
				+ ": " + divisors.numberOfDivisors());
		System.out.println("7 divides " + numberToTest + ": " + divisors.isDivisor(7));
		System.out.println("24 divides " + numberToTest + ": " + divisors.isDivisor(24));
		System.out.println(numberToTest + " is prime: " + divisors.isPrime());
		
		System.out.println("number of common divisors between 20349 and 2747115: "
				+ new Divisors(20349, 2747115).numberOfDivisors());
	}
}