public class Stuff implements Comparable{
	private String id;
	private String type;
	private String name;
	private Tag tags;
	
	Stuff(String stuff){
		stuff=stuff.trim();
		if(stuff.startsWith("[")&&stuff.endsWith("]")) { //괄호가 섞여 들어오면 제거
			stuff=stuff.substring(1, stuff.length()-1);
		}
		String stuffs[]=(stuff+" ").split(";");
		if(stuffs.length!=4) {
			throw new IllegalStateException
			("Stuff \""+ stuff +"\" has something wrong with number of \";\".");
		}
		id=null;
		type=stuffs[1].trim();
		name=stuffs[2].trim();
		if(type.isBlank()&&name.isBlank()) {
			throw new IllegalStateException
			("both type and name of Stuff \""+ stuff +"\" cannot be empty");
		}
		tags=new Tag(stuffs[3].trim());
	}

	Stuff(String type, String name, String tags, boolean forObject) {
		if(!forObject) createStuffForSearch(type,name,tags);
		else {
			this.id = null;
			this.type = type;
			this.name = name;
			this.tags = new Tag(tags);
			if(type.isBlank()&&name.isBlank()) {
				throw new IllegalStateException("both type and name of Stuff cannot be empty");
			}
		}
	}
	
	private void createStuffForSearch(String type, String name, String tags) {
		this.id = null;
		if(type.isBlank()) this.type=null;
		else this.type = type;
		if(name.isBlank()) this.name=null;
		else this.name = name;
		if(tags.isBlank()) this.tags=null;
		else this.tags = new Tag(tags);
		if(type.isBlank()&&name.isBlank()&&tags.isBlank()) {
			throw new IllegalStateException("Stuff is created only with null");
		}
	}
	
	boolean equalByTypeAndName(Stuff stuff) {
		//this는 null 필드를 가지면 안됨(인자는 가능)
		if((type.equals(stuff.type)||stuff.type==null)&&
				(name.equals(stuff.name)||stuff.name==null)) {
			return true;
		}
		return false;
	}
	
	boolean equalExceptId(Stuff stuff) {
		if(type.equals(stuff.type)&&name.equals(stuff.name)&&tags.equals(stuff.tags)) {
			return true;
		}
		return false;
	}
	
	boolean equalAndSearch(Stuff stuff) {
		if((type.equals(stuff.type)||stuff.type==null)&&
				(name.equals(stuff.name)||stuff.name==null)&&
				(tags.ifTagExist(stuff.tags)||stuff.tags==null)) {
			return true;
		}
		return false;
	}
	
	boolean equalOrSearch(Stuff stuff) {
		if(type.equals(stuff.type)||name.equals(stuff.name)||tags.ifTagExist(stuff.tags)) {
			return true;
		}
		return false;
	}
	
	@Override
	public int compareTo(Object o) {
		if(!(o instanceof Stuff)) throw new IllegalStateException(o+" is not Stuff");
		Stuff stuff=(Stuff) o;
		int result = id.compareTo(stuff.id);
		if(result==0) {
			result = tags.compareTo(stuff.tags);
		}
		return result;
	}

	@Override
	public String toString() {
		return "[" + id + "; " + type + "; " + name + "; " + tags + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stuff other = (Stuff) obj;
		return id.equalsIgnoreCase(other.id);
	}

	void setId(String id) {
		if(this.id==null)
			this.id = id;
		assert !(this.name==null||this.type==null||this.tags==null):
			"there is null in Stuff for non-search" ;
	}

	void setId(Stuff stuff) {
		if(this.id==null)
			this.id = stuff.id;
		assert !(this.name==null||this.type==null||this.tags==null):
			"there is null in Stuff for non-search" ;
	}

	String getType() {
		return type;
	}

	String getName() {
		return name;
	}

	Tag getTags() {
		return tags;
	}
}