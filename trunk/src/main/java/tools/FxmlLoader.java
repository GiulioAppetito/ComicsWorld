package tools;

import java.net.URL;


import com.example.comics.view1.HomeControllerG;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class FxmlLoader {
	
	private Pane view;
	
	public Pane getPage(String fileName) {
		
		
		try{
			URL fileUrl = HomeControllerG.class.getResource(fileName + ".fxml");
			if(fileUrl == null) {
				throw new java.io.FileNotFoundException("FXML not found");
			}
		new FXMLLoader();
		view = FXMLLoader.load(fileUrl);
			
		}catch(Exception e){
			System.out.println("No page found with fxml: " + fileName);
		}
		
		return view;
		
	}
	
}