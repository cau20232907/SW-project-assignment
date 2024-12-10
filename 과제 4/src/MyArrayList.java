public class MyArrayList<E> {
	private E[] list;
	private int len;
	
	MyArrayList(){
		list=(E[]) new Object[1];
		len=0;
	}
	
	MyArrayList(E[] array){
		list=(E[]) new Object[array.length];
		System.arraycopy(array, 0, list, 0, array.length);
		len=array.length;
	}
	
	public int size() {
		return len;
	}
	
	public boolean isEmpty() {
		return len==0;
	}
	
	public E get(int index) {
		if(index>=len)
			throw new ArrayIndexOutOfBoundsException("Index "+index+" out of bounds for length "+len);
		return list[index];
	}
	
	public void add(int index, E o) {
		if(index>len)
			throw new ArrayIndexOutOfBoundsException("Index "+index+" out of bounds for length "+len);
		if(len==list.length) {
			E[] tmp=(E[]) new Object[list.length*2];
			System.arraycopy(list, 0, tmp, 0, index);
			System.arraycopy(list, index, tmp, index+1, list.length-index);
			list=tmp;
		}
		else
			System.arraycopy(list, index, list, index+1, len-index);
		list[index]=o;
		len++;
	}
	
	public void add(E o) {
		add(len,o);
	}
	
	public E remove(int index) {
		E target=get(index);
		System.arraycopy(list, index+1, list, index, len-index-1);
		len--;
		return target;
	}
}