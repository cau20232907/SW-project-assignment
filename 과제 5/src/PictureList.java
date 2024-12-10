import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PictureList {
	private List<Picture> pictureList;
	
	PictureList(){
		pictureList=new ArrayList<Picture>();
	}
	
	PictureList(String infoFileName){
		pictureList=new ArrayList<Picture>();
		savePictureByReadingFile(infoFileName);
	}
	
	PictureList(List<Picture> pictureList){
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
		return this.pictureList.size();
	}
	
	public Picture get(int i) {
		return pictureList.get(i); //0을 포함하는 것인가? (autotest에서 -1 넣었다 에러 나옴)
	}
	
	public void add(Picture pic) {
		addPicture(pic);
	}
	
	private void addPicture(Picture picture) {
		for(Picture savedPicture:pictureList) { //이미 존재하는 id에 대한 처리를 autotest에서 요구하여 추가
			if(picture.equalById(savedPicture))
				throw new IllegalStateException("Picture with same id already Exist");
		}
		pictureList.add(picture);
	}
	
	private void deletePicture(Picture picture) {
		pictureList.remove(picture);
	}
	/* 추가, 수정, 삭제, 검색은 이번에 요구하지 않았으므로 구현 없음
	private PictureList selectTags(Tag tag) {
		
	}
	
	private PictureList selectStuff(Stuff stuff) {
		
	}
	
	private void sortById() {
		throw new UnsupportedOperationException();
	}
	*/
	private void sortByDate() {
		pictureList.sort(null);
	}

	@Override
	public String toString() {
		String result="";
		for(Picture picture:pictureList)
			result+=picture+"\n";
		return result.trim();
	}

	List<Picture> getPictureList() {
		return pictureList;
	}
}