import java.util.ArrayList;
import java.util.List;

public class Tag implements Comparable{
	private List<String> tags;
	
	Tag(String tagInfo){
		tags=new ArrayList<String>();
		if(tagInfo==null) tagInfo="";
		String[] splitResult=tagInfo.replace(" ", "").split("#");
		//중간의 공백도 삭제하기 위해 trim이 아닌 replace 사용
		//바로 넣지 않는 이유는 맨 앞에 빈 문자열이 생기기 때문
		for(String tag:splitResult) {
			if(!tag.isBlank())
				addTag(tag);
		}
	}
	
	boolean ifTagExist(String tagToCompare) {
		if(tagToCompare==null) return false;
		tagToCompare=tagToCompare.replace("#", "").trim();
		for(String tag:tags) {
			if(tagToCompare.equalsIgnoreCase(tag))
				return true;
		}
		return false;
	}
	
	boolean ifTagExist(Tag tagToCompare) {
		boolean flag=false;
		if(tagToCompare==null) return false;
		for(String compareTag:tagToCompare.tags) {
			flag=false;
			for(String savedTag:tags)
				if(compareTag.equalsIgnoreCase(savedTag)) {
					flag=true;
					break;
				}
			if(flag==false) return false;
		}
		return true;
	}
	
	int getLength() {
		return tags.size();
	}
	
	void addTag(String tag) {
		tags.add(tag);
	}
	
	void deleteTag(String tag) {
		tag=tag.trim();
		if(tag.startsWith("#"))
			tag=tag.substring(1).trim();
		tags.remove(tag);
	}
	
	@Override
	public int compareTo(Object o) {
		if(!(o instanceof Tag)) throw new IllegalStateException(o+" is not Tag");
		Tag tag=(Tag) o;
		int result=this.getLength()-tag.getLength();
		for(int i=0;result==0&&i<this.getLength();i++) {
			result=this.tags.get(i).compareTo(tag.tags.get(i));
		} //길이가 다르면 for문 진입조차 하지 않음
		return result;
	}
	
	@Override
	public String toString() {
		String result="";
		for(String tag:tags) {
			result+="#"+tag+" ";
		}
		return result.trim();
	}
	
	//이하는 Eclipse의 자동 생성
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		//하나하나 비교
		//other의 중점에서 비교
		boolean flag=false;
		for(String compareTag:other.tags) {
			flag=false;
			for(String savedTag:this.tags)
				if(compareTag.equals(savedTag)) {
					flag=true;
					break;
				}
			if(flag==false) return false;
		}
		//this의 중점에서 비교
		for(String savedTag:this.tags) {
			flag=false;
			for(String compareTag:other.tags)
				if(savedTag.equals(compareTag)) {
					flag=true;
					break;
				}
			if(flag==false) return false;
		}
		return true;
	}
}