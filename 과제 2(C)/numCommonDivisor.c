#include <stdio.h>
int numCommonDivisors(int num1, int num2) {
	int tmp, cnt = 0;
	if (num1 < num2) {
		tmp = num1;
		num1 = num2;
		num2 = tmp;
	}

	// Euclidean Algorithm 사용, num1은 둘 중 큰 수, num2는 둘 중 작은 수
	while (num2 != 0) {
		tmp = num1 % num2;
		num1 = num2;
		num2 = tmp;
	}

	for (tmp = 1; tmp <= num1; tmp++)
		if (num1 % tmp == 0) cnt++;

	return cnt;
}

void main() {
	const int number1 = 16, number2 = 81;
	int num1 = number1, num2 = number2, tmp;
	int cnt = 0;
	if (num1 < num2) {
		tmp = num1;
		num1 = num2;
		num2 = tmp;
	}

	// Euclidean Algorithm 사용, num1은 둘 중 큰 수, num2는 둘 중 작은 수
	while (num2 != 0) {
		tmp = num1 % num2;
		num1 = num2;
		num2 = tmp;
	}

	for (tmp = 1; tmp <= num1; tmp++)
		if (num1 % tmp == 0) cnt++;

	printf("numCommonDivisor(%d, %d) : %d", number1, number2, cnt);
}