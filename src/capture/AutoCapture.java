package capture;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import setting.export.SettingParameter;

public class AutoCapture extends Capture {

	public AutoCapture(SettingParameter setting) {
		super(setting);
		// TODO Auto-generated constructor stub
	}

	void start() {
		catchTime();
        setting.setFolderName(sdate);
		createFolder();
		mySignal();
		getautoSecond();

		if (mysignal) { // pause or not
			while (mysignal)
				try {
					Thread.sleep(1000);// 電腦暫停一秒鐘
					System.out.println("stop");
				} catch (Exception e) {
				}
		} else {
			if (setting.getAutoOption()) {
				while (true) {
					try {
						catcher();
						Thread.sleep(autoSecond * 1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else { // 以點擊
				new HandScreenShot(setting);
			}
		}
	}

}
