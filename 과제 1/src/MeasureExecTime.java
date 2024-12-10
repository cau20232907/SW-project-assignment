public class MeasureExecTime{
	public static void main(String[] args){
		long startTime, endTime, execTime;
		int rpt = 1<<20;
		startTime = System.nanoTime();
		for(int i=0;i<rpt;i++){
			numCommonDivisor(20349,2747115);
		}
		endTime = System.nanoTime();
		execTime = endTime-startTime;

		System.out.println("Execution Time in nano seconds = "+((double)execTime/rpt));

		// 함수 정상작동 테스트용
		// System.out.println("numCommonDivisor(20349,2747115) : "+ numCommonDivisor(20349,2747115));
		}
	public static int numCommonDivisor(int num1, int num2){
		int tmp;
		if(num1<num2){
			tmp=num1;
			num1=num2;
			num2=tmp;
		}

		// Euclidean Algorithm 사용, num1은 둘 중 큰 수, num2는 둘 중 작은 수
		while(num2!=0){
			tmp=num1%num2;
			num1=num2;
			num2=tmp;
		}

		//아래의 코드가 최대공약수가 1인 상태를 가정하지 않은 바, 이에 대한 처리를 별도로 진행
		if(num1==1){
			return 1;
		}

		//cnt는 divisor 갯수, 2부터 num1의 제곱근까지 하나하나 약수인지 계산(약수를 작은 것부터 나열했을 때, 첫 번째 약수와 마지막 약수의 곱은 원래 수 자신, 두 번째 약수와 마지막에서 두 번째 약수의 곱은 원래 수 자신... 이라는 점을 이용함), 이때 1과 자기 자신은 계산에서 제외되므로 cnt에 2를 넣어주고 시작
		int cnt = 2, i;
		for(i=2;i*i<num1;i++){
			if(num1%i==0){
				cnt+=2;
			}
		}
		if(num1%i==0){ //제곱근이 자연수면 약수이나 위와는 달리 두 번 세면 안 되므로 별도로 뺌
			cnt++;
		}
		return cnt;
	}
}