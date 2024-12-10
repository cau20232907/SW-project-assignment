public class ObjectArrayList extends ObjectArrayListLimitedCapacity{
	
	@Override
	public void add(int index, Object o) {
		if(index>len)
			throw new ArrayIndexOutOfBoundsException("Index "+index+" out of bounds for length "+len);
		if(len==list.length) {
			Object[] tmp=new Object[list.length*2];
			System.arraycopy(list, 0, tmp, 0, index);
			System.arraycopy(list, index, tmp, index+1, list.length-index);
			list=tmp;
		}
		else
			System.arraycopy(list, index, list, index+1, len-index);
		list[index]=o;
		len++;
	}
}

class ObjectArrayListLimitedCapacity {
	protected Object[] list;
	protected int len;
	
	ObjectArrayListLimitedCapacity(){
		this(1);
	}
	
	ObjectArrayListLimitedCapacity(int capacity){
		list=new Object[capacity];
		len=0;
	}
	
	public int size() {
		return len;
	}
	
	public boolean isEmpty() {
		return len==0;
	}
	
	public Object get(int index) {
		if(index>=len)
			throw new ArrayIndexOutOfBoundsException("Index "+index+" out of bounds for length "+len);
		return list[index];
	}
	
	public void add(int index, Object o) {
		if(len==list.length)
			throw new ArrayIndexOutOfBoundsException("Cannot add more Objects to array");
		if(index>len)
			throw new ArrayIndexOutOfBoundsException("Index "+index+" out of bounds for length "+len);
		System.arraycopy(list, index, list, index+1, len-index);
		list[index]=o;
		len++;
	}
	
	public void add(Object o) {
		add(len,o);
	}
	
	public Object remove(int index) {
		Object target=get(index);
		System.arraycopy(list, index+1, list, index, len-index-1);
		len--;
		return target;
	}
}