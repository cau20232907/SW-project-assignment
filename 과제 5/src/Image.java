public class Image {
	private String imageId;
	private String imageFileName;
	private String imageName;
	private Tag tags;
	
	Image(String imageInfo){
		String[] imageByString=(imageInfo+" ").split(";");
		if(imageByString.length!=4) {
			throw new IllegalStateException
			("Image \""+ imageInfo +"\" has something wrong with \";\".");
		}
		imageId=imageByString[0].trim();
		imageFileName=imageByString[1].trim();
		imageName=imageByString[2].trim();
		tags=new Tag(imageByString[3]);
	}

	@Override
	public String toString() {
		return imageId + "; " + imageFileName + "; " + imageName + "; " + tags;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		return imageId.equals(other.imageId)&&imageFileName.equals(other.imageFileName)
				&&imageName.equals(other.imageName)&&tags.equals(other.tags);
	}

	String getImageFileName() {
		return imageFileName;
	}
}