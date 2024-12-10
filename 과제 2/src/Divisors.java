
public class Divisors {
	private int divisors[];
	
	Divisors(){
	}
	
	Divisors(int number){
		calculateDivisors(number);
	}
	
	Divisors(int number1, int number2){
		calculateDivisors(number1, number2);
	}
	
	private void calculateDivisors(int number) {
		int[] divisors=new int[100];
		int numDivisors=0;
		for(int i=1;i<=number;i++) {
			if(number%i==0) {
				divisors[numDivisors]=i;
				numDivisors++;
			}
		}
		this.divisors=new int[numDivisors];
		System.arraycopy(divisors, 0, this.divisors, 0, numDivisors);
	}
	
	private void calculateDivisors(int number1, int number2) {
		int tmp;
		if(number1<number2){
			tmp=number1;
			number1=number2;
			number2=tmp;
		}

		// Euclidean Algorithm 사용, number1은 둘 중 큰 수, number2는 둘 중 작은 수
		while(number2!=0){
			tmp=number1%number2;
			number1=number2;
			number2=tmp;
		}
		
		calculateDivisors(number1);
	}
	
	public boolean isDivisor(int number) {
		for(int i=0;i<divisors.length;i++) {
			if(divisors[i]==number) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isPrime() {
		if(divisors.length==2) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		if(divisors==null||divisors.length==0) {
			//원소가 없을 때를 대비한 예외 처리
			return "No divisor";
		}
		String string = "Divisors: ";
		string+=divisors[0];
		for(int i=1;i<divisors.length;i++) {
			//','때문에 [0]의 코드 분리, 소수의 개수가 1이면 for문을 진입하지 않음
			string+=", "+divisors[i];
		}
		
		return string;
	}
	
	public int numberOfDivisors() {
		return divisors.length;
	}
	
	//구현을 요해서 별도로 작성하긴 했으나, JAVA 스타일은 전혀 아님
	public static int numCommonDivisors(int number1, int number2) {
		return new Divisors(number1, number2)
				.numberOfDivisors();
	}

	/*
	public Divisors calculateDivisors(int number) {
		if(number==1) {
			divisors[0]=1;
			numDivisors=1;
			return this;
		}
		Factorization factorization[]=new Factorization[7];
		int factorizationLength=0;
		for(int i=2;i*i<=number;i++) {
			if(number%i==0) {
				factorization[factorizationLength] = new Factorization(i); //소인수 저장
				//new 안 했더니 에러났음(NullPointerException)
				while(number%i==0&&number!=0) {
					factorization[factorizationLength].raisePower(); //소인수의 제곱(승수) 저장
					number/=i;
				}
				factorizationLength++; //소인수 종류 1 증가
			}
		}
		if(number!=0) {
			factorization[factorizationLength] = new Factorization(number); //소인수 저장
			factorization[factorizationLength].raisePower(); //소인수의 제곱(승수) 저장(1)
			factorizationLength++; //소인수 종류 1 증가
		}
		
		
		FactorizationCalculate(factorization, factorizationLength, 0, 1);
		
		return this;
		
	}
	
	private void FactorizationCalculate(Factorization factorization[], int factorizationLength, int factorizationOrder, int previousResult) {
		int result=previousResult;
		for(int i=0;i<=factorization[factorizationOrder].power;i++) {
			if(factorizationOrder+1==factorizationLength) { //더 이상 소인수가 없어서 여기서 저장해야 함
				divisors[numDivisors]=result;
				numDivisors++;
			} else {
				FactorizationCalculate(factorization, factorizationLength, factorizationOrder+1, result); //재귀함수(for문을 반복하기 위한 장치)
			}
			result*=factorization[factorizationOrder].base; //소인수만큼 곱하기(어떤 수, 어떤 수의 제곱, 어떤 수의 세제곱,...을 인수로 가지는 숫자를 표현하기 위한 장치, 0제곱도 해야 하므로 반복문 마지막에
		}
	}*/
}