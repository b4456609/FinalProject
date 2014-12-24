package project.java2014.Bernie;

import java.io.File;
import java.util.ArrayList;

public class PicModel {

	//private String path = "D:\\我的圖片\\screenshots";
	private String path = "C:\\Users\\Bernie\\git\\FinalProject\\src\\project\\java2014\\Bernie\\img";
	private ArrayList<PicContainer> pics = new ArrayList<PicContainer>();

	// test constructor
	public PicModel() {
		setPic();
	}

	// constructor using path
	public PicModel(String path) {
		this.path = path;
		setPic();
	}


	// set picture form path
	private void setPic() {
		File picFinder = new File(path);
		File[] files = picFinder.listFiles();

		for (File picPath : files) {
			// add to array list
			pics.add(new PicContainer(picPath));
		}
	}

	// get picture list
	public ArrayList<PicContainer> getPic() {
		return pics;
	}

	// swap two item
	public boolean swap(int indexA, int indexB) {
		// check bound
		if ((indexA >= pics.size()) || (indexB >= pics.size()) || (indexA < 0)
				|| (indexB < 0))
			return false;
		PicContainer temp1 = pics.get(indexA);
		PicContainer temp2 = pics.get(indexB);

		pics.set(indexB, temp1);
		pics.set(indexA, temp2);

		return true;
	}

	// delete specific item
	public boolean deleteItem(int index) {
		// check bound
		if ((index > pics.size()) || (index < 0))
			return false;
		pics.remove(index);
		return true;
	}
	
	public void setText(int index, String comment){
		pics.get(index).setComment(comment);
	}
	
	public String getText(int index){
		return pics.get(index).getComment();
	}
}
