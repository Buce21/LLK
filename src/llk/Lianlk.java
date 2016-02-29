package llk;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;

import sample.Controller;
import win32.Mouse;
import win32.Window;

public class Lianlk implements Runnable {

	private String hd ;
	private String timeout ;

	public Lianlk(String hd, String timeout) {
		this.hd = hd;
		this.timeout = timeout;
	}

	@Override
	public void run() {
		Controller controller = new Controller();
		// ȡ��Ϸ���ھ��
		int hwnd = Integer.parseInt(this.hd);
		if (hwnd <= 0) {
			return;
		}
		try {
			// ��Ϸ��ͼ( ��Ϸ���ڲ��ܱ����������ڵ� .�����ͼ��ص��������� )
			File f = File.createTempFile("qqllk", ".bmp");
			System.out.println(f.getAbsolutePath());
			// д��λͼ
			BufferedImage buffImage = Window.getImage(hwnd);
			if (buffImage == null) {
				return;
			}
			ImageIO.write(buffImage, "bmp", new FileOutputStream(f));
			// ����λͼ
			BMP bmp = new BMP(f.getAbsolutePath());

			// QQ�������� 11 * 19 ����
			int[][] n = new int[11][19];
			FkSet set = new FkSet();
			for (int i = 0; i < 11; i++) {
				for (int j = 0; j < 19; j++) {
					// ��ȡһС�� 15 *15 ������
					int x = 17 + j * 31;
					int y = 187 + i * 35;
					Fangkuai fk = new Fangkuai(bmp.getData(x, y, 15, 15));
					if (bmp.getColor(x, y) != 7359536) {// ���ֵ�ǿհ�������ɫֵ
						int type = set.add(fk);
						if (type != 0) {
							n[i][j] = type;
						}
					}

				}
			}
			System.out.println(set.size());
			// ����Զ����
			DepthFirst df = new DepthFirst(n);
			while (df.search()) {
				Thread.sleep(Integer.parseInt(this.timeout));
				Point a = df.getA();
				Point b = df.getB();
				Mouse.click(hwnd, a.getY() * 31 + 17, a.getX() * 35 + 187);
				Mouse.click(hwnd, b.getY() * 31 + 17, b.getX() * 35 + 187);
				df.setValue(a.getX(), a.getY(), 0);
				df.setValue(b.getX(), b.getY(), 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
