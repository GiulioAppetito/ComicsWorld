package tools;

import java.net.URL;


import com.example.comics.view1.ReaderHomeControllerG;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class FxmlLoader {
	
	private Pane view;
	
	public Pane getPage(String fileName) {
		
		
		try{
			URL fileUrl = ReaderHomeControllerG.class.getResource(fileName + ".fxml");
			if(fileUrl == null) {
				throw new java.io.FileNotFoundException("FXML not found");
			}
		new FXMLLoader();
		view = FXMLLoader.load(fileUrl);
			
		}catch(Exception e){
			//to-do
		}
		
		return view;
		
	}
	
}
