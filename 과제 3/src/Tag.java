public class Tag {
	private String[] tags; //절대 null이 되지 않음
	
	Tag(String tagInfo){
		tags=new String[0];
		String[] splitResult=tagInfo.replace(" ", "").split("#");
		//중간의 공백도 삭제하기 위해 trim이 아닌 replace 사용
		//바로 넣지 않는 이유는 맨 앞에 빈 문자열이 생기기 때문
		for(String tag:splitResult) {
			if(!tag.isBlank())
				addTag(tag);
		}
	}
	
	boolean ifTagExist(String tagToCompare) {
		tagToCompare=tagToCompare.replace("#", "").trim();
		for(String tag:tags) {
			if(tagToCompare.equalsIgnoreCase(tag))
				return true;
		}
		return false;
	}
	
	boolean ifTagExist(Tag tagToCompare) {
		boolean flag=false;
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
		return tags.length;
	}
	
	void addTag(String tag) {
		String[] tempList;
		tempList=new String[getLength()+1];
		System.arraycopy(tags, 0, tempList, 0, getLength());
		tempList[getLength()]=tag.replace("#", "").trim();
		tags=tempList;
	}
	
	void deleteTag(String tag) {
		tag=tag.trim();
		if(tag.startsWith("#"))
			tag=tag.substring(1).trim();
		
		Integer positionToDelete=null;
		//삭제대상찾기
		for(int i=0;i<tags.length;i++) {
			if(tags[i].equalsIgnoreCase(tag)) {
				positionToDelete=i;
				break;
			}
		}
		if(positionToDelete==null) {
			throw new NullPointerException("Failed to find target to erase: #"+tag);
		}
		//삭제 대상 빼고 새 배열에 옮기기
		String[] tempList=new String[getLength()-1];
		System.arraycopy(tags, 0, tempList, 0, positionToDelete);
		System.arraycopy(tags, positionToDelete+1, tempList, positionToDelete,
				tags.length-positionToDelete-1);
		tags=tempList;
	}
	
	int compareTo(Tag tag) {
		int result=this.getLength()-tag.getLength();
		for(int i=0;result==0&&i<this.getLength();i++) {
			result=this.tags[i].compareTo(tag.tags[i]);
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