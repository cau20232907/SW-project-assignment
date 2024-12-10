
public class Factorization { //소인수분해를 사용하기 위한 클래스(int의 2차원 배열로 하려 했으나 어려워져 분리)
	public int base;
	public int power;
	
	Factorization(){
		base=0;
		power=0;
	}
	Factorization(int base){
		this.base=base;
		this.power=0;
	}
	Factorization(int base, int power){
		this.base=base;
		this.power=power;
	}
	
	public void setBase(int base) {
		this.base=base;
	}
	
	public void raisePower() {
		power++;
	}
}
