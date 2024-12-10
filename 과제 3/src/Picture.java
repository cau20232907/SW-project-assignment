import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Picture {
	private String id;
	private LocalDateTime timeStamp;
	private Image pictureInfo;
	private Stuff[] stuffList;
	private Tag tags;
	private String comment;
	
	private static DateTimeFormatter dateTimeFormat=
			DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS");
	
	Picture(String imageFile){
		String[] pictureByString=imageFile.trim().split(">");
		if(pictureByString[pictureByString.length-1].isBlank()){
			String[] tempPictureByString=new String[pictureByString.length-1];
			System.arraycopy(pictureByString, 0, tempPictureByString, 0, pictureByString.length-1);
			pictureByString=tempPictureByString;
		}
		if(pictureByString.length!=6) {
			throw new IllegalStateException
			("Picture \""+ imageFile +"\" has something wrong with number of \"<\" and \">\".");
		}
		
		pictureByString[0]=pictureByString[0].trim();
		if(pictureByString[0].startsWith("<")) {
			id=pictureByString[0].substring(1).trim();
		} else {
			throw new IllegalStateException
			("Picture \""+ imageFile +"\" has something wrong with number of \"<\" and \">\".");
		}
		if(id.isBlank()) {
			throw new IllegalStateException("id of Picture \""+ imageFile +"\" cannot be empty");
		}
		
		pictureByString[1]=pictureByString[1].trim();
		if(pictureByString[1].startsWith("<")) {
			pictureByString[1]=pictureByString[1].substring(1).trim();
			try {
			timeStamp=LocalDateTime.parse(pictureByString[1],dateTimeFormat);
			} catch(DateTimeParseException e) {
				throw new IllegalStateException
				("Cannot parse \"" + pictureByString[1] + "\" to DateTimeFormat");
			}
		} else {
			throw new IllegalStateException
			("Picture \""+ imageFile +"\" has something wrong with number of \"<\" and \">\".");
		}
		
		pictureByString[2]=pictureByString[2].trim();
		if(pictureByString[2].startsWith("<")) {
			pictureInfo=new Image(pictureByString[2].substring(1).trim());
		} else {
			throw new IllegalStateException
			("Picture \""+ imageFile +"\" has something wrong with number of \"<\" and \">\".");
		}
		if(id.isBlank()) {
			throw new IllegalStateException
			("pictureInfo of Picture \""+ imageFile +"\" cannot be empty");
		}
		
		pictureByString[3]=pictureByString[3].trim();
		if(pictureByString[3].startsWith("<")) {
			stuffList=StuffList.newStuffs(pictureByString[3].substring(1).trim());
		} else {
			throw new IllegalStateException
			("Picture \""+ imageFile +"\" has something wrong with number of \"<\" and \">\".");
		}
		
		pictureByString[4]=pictureByString[4].trim();
		if(pictureByString[4].startsWith("<")) {
			tags=new Tag(pictureByString[4].substring(1).trim());
		} else {
			throw new IllegalStateException
			("Picture \""+ imageFile +"\" has something wrong with number of \"<\" and \">\".");
		}
		
		pictureByString[5]=pictureByString[5].trim();
		if(pictureByString[5].startsWith("<")) {
			comment=pictureByString[5].substring(1).trim();
		} else {
			throw new IllegalStateException
			("Picture \""+ imageFile +"\" has something wrong with number of \"<\" and \">\".");
		}
	}

	//Eclipse의 자동 생성자 생성 이용//

	Picture(String id, LocalDateTime timeStamp, Image pictureInfo, Stuff[] stuffList,
			Tag tags, String comment) {
		this.id = id;
		this.timeStamp = timeStamp;
		this.pictureInfo = pictureInfo;
		this.stuffList = stuffList;
		this.tags = tags;
		this.comment = comment;
	}
	
	public void print() {
		System.out.println(this.toString());
	}

	private boolean ifStuffExist(Stuff stuff) {
		for(Stuff savedStuff:stuffList) {
			if(stuff.equalByTypeAndName(savedStuff))
				return true;
		}
		return false;
	}
	
	private void addStuff(String stuff) {
		Stuff[] tempList=new Stuff[stuffList.length+1];
		System.arraycopy(stuffList, 0, tempList, 0, stuffList.length);
		tempList[stuffList.length]=StuffList.addStuff(stuff);
		stuffList=tempList;
	}
	
	private void addStuff(Stuff stuff) {
		Stuff[] tempList=new Stuff[stuffList.length+1];
		System.arraycopy(stuffList, 0, tempList, 0, stuffList.length);
		tempList[stuffList.length]=stuff;
		stuffList=tempList;
	}
	
	private void deleteStuff(String stuff) {
		deleteStuff(new Stuff(stuff));
		//stuffList 등록을 피하기 위해 Stuff를 바로 씀
	}
	
	private void deleteStuff(Stuff stuff) {
		//StuffList 클래스에서는 삭제되지 않음
		Integer positionToDelete=null;
		//삭제대상찾기
		for(int i=0;i<stuffList.length;i++) {
			if(stuffList[i].equalByTypeAndName(stuff)) {
				positionToDelete=i;
				break;
			}
		}
		if(positionToDelete==null) {
			throw new NullPointerException("Failed to find target to erase: "+stuff);
		}
		//삭제 대상 빼고 새 배열에 옮기기
		Stuff[] tempList=new Stuff[stuffList.length-1];
		System.arraycopy(stuffList, 0, tempList, 0, positionToDelete);
		System.arraycopy(stuffList, positionToDelete+1, tempList, positionToDelete,
				stuffList.length-positionToDelete-1);
		stuffList=tempList;
	}
	
	private boolean ifTagExist(String tag) {
		return tags.ifTagExist(tag);
	}
	
	private boolean ifTagExist(Tag tag) {
		return tags.ifTagExist(tag);
	}
	
	private void addTag(String tag) {
		tags.addTag(tag);
	}
	
	private void deleteTag(String tag) {
		tags.deleteTag(tag);
	}
	//비교대상이 greater->negative
	int compareToByTime(Picture picture) {
		return this.timeStamp.compareTo(picture.timeStamp);
	}
	
	int compareToById(Picture picture) {
		return id.compareTo(picture.id);
	}
	
	private int tagLength() {
		return tags.getLength();
	}
	
	boolean equalById(Picture picture) {
		return this.id.equals(picture.id);
	}
	
	@Override
	public String toString() {
		String stuffListString="";
		for(Stuff stuff:stuffList) {
			stuffListString+=stuff+" ";
		}
		return "<" + id + "> <" + timeStamp.format(dateTimeFormat) + "> <" + pictureInfo
				+ "> <" + stuffListString.trim() + "> <" + tags + "> <" + comment + ">";
	}

	static void setDateTimeFormat(DateTimeFormatter dateTimeFormat) {
		Picture.dateTimeFormat = dateTimeFormat;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Picture other = (Picture) obj;
		boolean result = id.equals(other.id)&&timeStamp.equals(other.timeStamp)
				&&pictureInfo.equals(other.pictureInfo)
				&&tags.equals(other.tags)&&comment.equals(other.comment);
		if(result==false) return false;
		//이하는 stuffList 비교, 둘 중 한 쪽만 null이거나, 개수가 다르면 false
		//else if(stuffList==null&&other.stuffList==null) return true;
		//else if(stuffList==null||other.stuffList==null) return false;
		else if(stuffList.length!=other.stuffList.length) return false;
		//하나하나 비교
		//other의 중점에서 비교
		boolean flag=false;
		for(Stuff compareStuff:other.stuffList) {
			flag=false;
			for(Stuff savedStuff:stuffList)
				if(compareStuff.equals(savedStuff)) {
					flag=true;
					break;
				}
			if(flag==false) return false;
		}
		//this의 중점에서 비교
		for(Stuff savedStuff:stuffList) {
			flag=false;
			for(Stuff compareStuff:other.stuffList)
				if(savedStuff.equals(compareStuff)) {
					flag=true;
					break;
				}
			if(flag==false) return false;
		}
		return true;
	}
}