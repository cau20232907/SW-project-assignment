public class Stuff implements Comparable{
	private String id;
	private String type;
	private String name;
	private Tag tags;
	
	Stuff(){
		//empty stuff
	}
	
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

	Stuff(String type, String name, String tags) {
		this.id = null;
		this.type = type;
		this.name = name;
		this.tags = new Tag(tags);
		if(type.isBlank()&&id.isBlank()) {
			throw new IllegalStateException("both type and name of Stuff cannot be empty");
		}
	}

	Stuff(String id, String type, String name, Tag tags) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.tags = tags;
		if(type.isBlank()&&id.isBlank()) {
			throw new IllegalStateException("both type and name of Stuff cannot be empty");
		}
	}
	
	boolean equalByTypeAndName(Stuff stuff) {
		if(stuff.type.equals(type)&&stuff.name.equals(name)) {
			return true;
		}
		return false;
	}
	
	boolean equalExceptId(Stuff stuff) {
		if(stuff.type.equals(type)&&stuff.name.equals(name)&&stuff.tags.equals(tags)) {
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
	}

	void setId(Stuff stuff) {
		if(this.id==null)
			this.id = stuff.id;
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