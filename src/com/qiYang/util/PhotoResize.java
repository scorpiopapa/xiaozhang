package com.qiYang.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PhotoResize {
	/**
	 * 对图片进行缩�?
	 * 
	 * @param originalImage
	 *            原始图片
	 * @param times
	 *            缩小倍数
	 * @return 缩小后的Image
	 */

	public static BufferedImage zoomOutImage(BufferedImage originalImage,
			int Nwidth, int Nheight) {
		float width = 0.0f;
		float height = 0.0f;
		// System.out.println(originalImage.getWidth());
		// System.out.println(originalImage.getHeight());
		if ((originalImage.getWidth() / Nwidth) >= (originalImage.getHeight() / Nheight)) {
			width = Nwidth;
			height = (float) originalImage.getHeight()
					/ originalImage.getWidth() * Nwidth;
		} else {
			width = (float) originalImage.getWidth()
					/ originalImage.getHeight() * Nheight;
			height = Nheight;
		}
		int w = (int) width;
		int h = (int) height;
		// System.out.println(width);
		// System.out.println(height);
		// System.out.println(w);
		// System.out.println(h);
		// System.out.println(originalImage.getType());

		BufferedImage newImage = new BufferedImage(w, h, originalImage
				.getColorModel().getTransparency());

		Graphics g = newImage.getGraphics();
		g.drawImage(originalImage, 0, 0, w, h, null);
		g.dispose();
		return newImage;
	}

	/**
	 * 对图片进行缩�?
	 * 
	 * @param srcPath
	 *            源图片路径（绝对路径�?
	 * @param newPath
	 *            新图片路径（绝对路径�?
	 * @param times
	 *            缩小倍数
	 * @return 保存是否成功
	 */

	public static boolean zoomOutImage(String srcPath, String newPath,
			int Nwidth, int Nheight) {
		BufferedImage bufferedImage = null;
		try {
			File of = new File(srcPath);
			if (of.canRead()) {
				bufferedImage = ImageIO.read(of);
			}
		} catch (IOException e) {
			return false;
		}
		if (bufferedImage != null) {
			bufferedImage = zoomOutImage(bufferedImage, Nwidth, Nheight);
			try {
				// TODO: 这个保存路径�?��配置下子好一�?
				ImageIO.write(bufferedImage, "JPG", new File(newPath)); // 保存修改后的图像,全部保存为JPG格式
			} catch (IOException e) {
				// TODO 打印错误信息
				return false;
			}
		}
		return true;
	}
}
