import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PictureList {
	private Picture[] pictureList;
	
	PictureList(){
		pictureList=new Picture[0];
	}
	
	PictureList(String infoFileName){
		pictureList=new Picture[0];
		savePictureByReadingFile(infoFileName);
	}
	
	PictureList(Picture[] pictureList){
		this.pictureList=pictureList;
	}
	
	//중간에 추가할 수 있기에 메소드 분리, 다만 아직 쓸 일 없으니 private(프로그램 확장 시 변경)
	private void savePictureByReadingFile(String infoFileName) {
		File file=new File(infoFileName);
		Scanner input;
		try {
			input=new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot find file :" + infoFileName);
			return;
		}
		Picture.setDateTimeFormat(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS"));
		while(input.hasNext()) {
			savePictureByString(input.nextLine());
		}
		input.close();
	}
	
	private void savePictureByString(String imageInfo) {
		String infoPicture=imageInfo.trim();
		if(infoPicture.startsWith("//")||infoPicture.isBlank())
			return;
		try {
		Picture picture=new Picture(infoPicture);
		addPicture(picture);
		} catch(IllegalStateException e) {
			System.out.println(e.getMessage());
			System.out.println("Failed to save picture "+ imageInfo);
		}
	}
	
	public void savePictureToFile(String infoFileName) {
		File file=new File(infoFileName);
		PrintWriter output;
		try {
			output = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot find file :" + infoFileName);
			return;
		}
		Picture.setDateTimeFormat(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS"));
		for(Picture picture:pictureList) {
			output.println(picture);
		}
		output.close();
	}
	
	public int size() {
		return this.pictureList.length;
	}
	
	public Picture get(int i) {
		return pictureList[i]; //0을 포함하는 것인가? (autotest에서 -1 넣었다 에러 나옴)
	}
	
	public void add(Picture pic) {
		addPicture(pic);
	}
	
	private void addPicture(Picture picture) {
		for(Picture savedPicture:pictureList) { //이미 존재하는 id에 대한 처리를 autotest에서 요구하여 추가
			if(picture.equalById(savedPicture))
				throw new IllegalStateException("Picture with same id already Exist");
		}
		Picture[] tempList=new Picture[pictureList.length+1];
		System.arraycopy(pictureList, 0, tempList, 0, pictureList.length);
		tempList[pictureList.length]=picture;
		pictureList=tempList;
	}
	
	private void deletePicture(Picture picture) {
		Integer positionToDelete=null;
		//삭제대상찾기
		for(int i=0;i<pictureList.length;i++) {
			if(pictureList[i].equals(picture)) {
				positionToDelete=i;
				break;
			}
		}
		if(positionToDelete==null) {
			throw new NullPointerException("Failed to find target to erase: "+picture);
		}
		//삭제 대상 빼고 새 배열에 옮기기
		Picture[] tempList=new Picture[pictureList.length-1];
		System.arraycopy(pictureList, 0, tempList, 0, positionToDelete);
		System.arraycopy(pictureList, positionToDelete+1, tempList, positionToDelete,
				pictureList.length-positionToDelete-1);
		pictureList=tempList;
	}
	/* 추가, 수정, 삭제, 검색은 이번에 요구하지 않았으므로 구현 없음
	private PictureList selectTags(Tag tag) {
		
	}
	
	private PictureList selectStuff(Stuff stuff) {
		
	}
	*/
	public void sortByDate() {
		Picture temp=null;
		for(int i=0;i<pictureList.length-1;i++)
			for(int j=i+1;j<pictureList.length;j++)
				if(pictureList[i].compareToByTime(pictureList[j])>0) {
					temp=pictureList[i];
					pictureList[i]=pictureList[j];
					pictureList[j]=temp;
				}
	}
	
	private void sortById() {
		Picture temp=null;
		for(int i=0;i<pictureList.length-1;i++)
			for(int j=i+1;j<pictureList.length;j++)
				if(pictureList[i].compareToById(pictureList[j])>0) {
					temp=pictureList[i];
					pictureList[i]=pictureList[j];
					pictureList[j]=temp;
				}
	}

	@Override
	public String toString() {
		String result="";
		for(Picture picture:pictureList)
			result+=picture+"\n";
		return result.trim();
	}
}