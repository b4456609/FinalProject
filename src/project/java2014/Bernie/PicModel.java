package project.java2014.Bernie;

import java.io.File;
import java.util.ArrayList;

public class PicModel {

	private String path = "D:\\我的圖片\\screenshots";
	private ArrayList<PicContainer> pics = new ArrayList<PicContainer>();
	
	//test constructor
	public PicModel() {
		setPic();
	}

	public PicModel(String path) {
		this.path = path;
		setPic();
		
	}	
	
	private void setPic(){
		File picFinder = new File(path);
		File[] files =picFinder.listFiles();
		
		for(File picPath:files){			
			//add to array list
			pics.add(new PicContainer(picPath));			
		}		
	}
	
	public ArrayList<PicContainer> getPic(){
		return pics;
	}
	
	
}
