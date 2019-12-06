package gui.components;

import core.Resource;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuButton extends Button {
	private String[] texts;
	private Image[] icons;
	private boolean[] disableStatus;
	private boolean[] visibleStatus;
	
	public MenuButton(String[] texts, String[] iconPaths, boolean[] disableStatus, boolean[] visibleStatus) {
		this.texts = texts;
		this.icons = new Image[iconPaths.length];
		this.disableStatus = disableStatus;
		this.visibleStatus = visibleStatus;
		
		for(int i = 0; i < icons.length; i++) {
			this.icons[i] = new Image(new Resource(iconPaths[i]).getStream());
		}
	}
	
	public void update(int level) {
		if(level <= icons.length) {
			ImageView graphic = (ImageView)getGraphic();
			graphic.setImage(icons[level-1]);
			setGraphic(graphic);
		}
		if(level <= texts.length) setText(texts[level-1]);
		if(level <= disableStatus.length) setDisable(disableStatus[level-1]);
		if(level <= visibleStatus.length) setDisable(visibleStatus[level-1]);
	}
	
	
}
