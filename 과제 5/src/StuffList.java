import java.util.ArrayList;
import java.util.List;

public class StuffList {
	private static List<Stuff> stuffList=new ArrayList<Stuff>();
	private static int stuffId=1;
	
	static List<Stuff> newStuffs(String stuff) {
		String[] stuffListByString=stuff.split("]");
		if(stuffListByString[stuffListByString.length-1].isBlank()) {
			String[] tempStuffList=new String[stuffListByString.length-1];
			System.arraycopy(stuffListByString, 0, tempStuffList, 0, tempStuffList.length);
			stuffListByString=tempStuffList;
		}
		
		List<Stuff> resultStuffList=new ArrayList<Stuff>();
		for(int i=0;i<stuffListByString.length;i++) {
			stuffListByString[i]=stuffListByString[i].trim();
			if(stuffListByString[i].startsWith("[")) {
				stuffListByString[i]=stuffListByString[i].substring(1);
				resultStuffList.add(addStuff(stuffListByString[i]));
			}
			else {
				throw new IllegalStateException
				("Stuffs \""+ stuff +"\" has something wrong with \"[\" and \"]\".");
			}
		}
		return resultStuffList;
	}
	
	private static Stuff ifStuffExistByNameAndType(Stuff stuffToCompare) {
		for(Stuff stuff: stuffList) {
			if(stuffToCompare.equalByTypeAndName(stuff))
				return stuff;
		}
		return null;
	}
	
	private static Stuff ifStuffExistExceptId(Stuff stuffToCompare) {
		for(Stuff stuff: stuffList) {
			if(stuffToCompare.equalExceptId(stuff))
				return stuff;
		}
		return null;
	}
	
	//Id 할당은 필요함
	static Stuff addStuff(String stuffInfo) {
		stuffInfo=stuffInfo.trim();
		if(stuffInfo.startsWith("[")&&stuffInfo.endsWith("]")) {
			stuffInfo=stuffInfo.substring(1,stuffInfo.length()-1).trim();
		}
		Stuff newStuff=new Stuff(stuffInfo);
		Stuff existingStuff=ifStuffExistByNameAndType(newStuff);
		if(existingStuff!=null) {
			Stuff existingSameStuff=ifStuffExistExceptId(newStuff);
			if(existingSameStuff!=null) {
				newStuff=existingSameStuff;
				//전체출력시 같은 거 여러 번 나오는 불편한 상황 방지
			} else {
				newStuff.setId(existingStuff);
				stuffList.add(newStuff);
			}
		} else {
			newStuff.setId(String.format("%08d", stuffId));
			//00000001부터 차례대로 ID를 주기 위한 유일한 방법
			stuffId++;
			stuffList.add(newStuff);
		}
		return newStuff;
	}
	
	private static void sortById() {
		stuffList.sort(null);
	}
	
	public static void print() {
		System.out.println(allStuffToString());
	}
	
	private static String allStuffToString() {
		String stuffListByString="All Stuffs now saved: < ";
		for(Stuff stuff: stuffList) {
			stuffListByString+=stuff+" ";
		}
		return stuffListByString+">";
	}
}