public class TestPicture {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PictureList pictureList=new PictureList
				("D:\\OneDrive - 중앙대학교\\2학년 1학기\\소프트웨어프로젝트\\프로젝트3-배포자료\\picture-normal.data"
				.replace("\\", "/"));
		pictureList.add(new Picture("< m_2024-04-03_14:20:00:000 > < 2024-04-03_14:20:00:000 > < IMG2024-04-08_19:12:57:384; images/notfound.jpg; notfound; #friendroom > <[00000026; console; nintendoswitch; #OLED] [00000032; console; NDS; #NDSL]> <#nintendoswitch> <friend's nintendo switch>"));
		//pictureList.deletePicture(pictureList.get(2));
		//pictureList.deletePicture(new Picture("<m_2024-01-03_06:30:00:000> <2024-01-03_06:30:00:000> <IMG2024-01-07_15:28:42:083; images/morning-excercise.jpg; ; > <> <#happy> <new year>"));
		pictureList.sortByDate();
		System.out.println(pictureList.size());
		System.out.println(pictureList);
		/*System.out.println();
		pictureList.get(0).deleteStuff("[00000001; excercise; ; ]");
		pictureList.get(4).addStuff(pictureList.get(7).stuffList[0]);
		System.out.println(pictureList.get(4).tagLength());
		//pictureList.get(5).deleteTag("#Classroom");
		System.out.println(pictureList.get(4).ifTagExist(new Tag("#lecture #class")));
		pictureList.get(4).print();
		System.out.println(pictureList.get(6).ifStuffExist(new Stuff("[00000007; ; clo; ]")));
		System.out.println();
		System.out.println(pictureList);*/
		//StuffList.sortById();
		StuffList.print();
		//pictureList.get(0).print();
		//pictureList.savePictureToFile("picture-saved.data");
	}
}