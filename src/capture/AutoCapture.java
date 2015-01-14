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
		//mySignal();
		getautoSecond();

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				if (mysignal) { // pause or not
					while (mysignal)
						try {
							Thread.sleep(1000);// �q���Ȱ��@����
							System.out.println("stop");
						} catch (Exception e) {
						}
				} else {
					boolean a = setting.getAutoOption();
					System.out.println(a);
					if (a) {
						while (!mysignal) {
							try {
								catcher();
								Thread.sleep(autoSecond * 1000);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					} else { // �H�I��
						new HandScreenShot(setting);
					}
				}
			}
		});
		t2.start();
	}

}
