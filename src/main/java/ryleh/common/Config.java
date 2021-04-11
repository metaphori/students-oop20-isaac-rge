package ryleh.common;

import javafx.stage.Screen;

public class Config {
	
//	public static final int STANDARD_WIDTH = 854;
//	public static final int STANDARD_HEIGHT = 480;
	public static final int STANDARD_WIDTH = (int) Screen.getPrimary().getBounds().getWidth();
	public static final int STANDARD_HEIGHT = (int) Screen.getPrimary().getBounds().getHeight();
	
	public static double SCALE_MODIFIER = (854.0 / 1920.0);
	
}
