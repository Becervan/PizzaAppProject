package core;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import javafx.scene.text.Text;

public class TimeUpdater extends TimerTask {
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	private Text textField;
	
	public TimeUpdater(Text textField) {
		this.textField = textField;
	}
	
	@Override
	public void run() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					textField.setText(LocalTime.now().format(FORMATTER));
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
