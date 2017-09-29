package org.sikuli.core.cv;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;

public final class ImageConverter {

	public static void saveImage(IplImage image, String fileBaseName) {
		Java2DFrameConverter jConv = new Java2DFrameConverter();
		OpenCVFrameConverter.ToIplImage openCVConv = new OpenCVFrameConverter.ToIplImage();
		try {
			File f = new File("./target/" + fileBaseName + System.currentTimeMillis() + ".png");
			System.out.println("Saving image at " + f.getAbsolutePath());
			BufferedImage result = jConv.convert(openCVConv.convert(image.clone()));
			//System.out.println("Image: " + result +", " + result.getWidth() + "x" + result.getHeight());
			if (!ImageIO.write(result, "png", f)) {
				System.err.println("Could not write image.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveImage(BufferedImage image, String fileBaseName) {
		try {
			ImageIO.write(image, "png", new File("./target/" + fileBaseName + System.currentTimeMillis() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static IplImage convert(BufferedImage image) {
		if (image == null) {
	        return null;
	    }
		Java2DFrameConverter java2dConverter = new Java2DFrameConverter();
	    OpenCVFrameConverter.ToIplImage frameToImageConverter = new OpenCVFrameConverter.ToIplImage();
	    return frameToImageConverter.convert(java2dConverter.convert(image)).clone();
	}

	public static BufferedImage convert(IplImage image) {
		OpenCVFrameConverter.ToIplImage frameToImageConverter = new OpenCVFrameConverter.ToIplImage();
		Frame f = frameToImageConverter.convert(image);
		Java2DFrameConverter java2dConverter = new Java2DFrameConverter();
		return java2dConverter.convert(f);
	}

	public static Frame getFrame(BufferedImage image) {
		Java2DFrameConverter java2dConverter = new Java2DFrameConverter();
		Frame f = java2dConverter.convert(image);
		return f;
	}

	public static Frame getFrame(IplImage image) {
		OpenCVFrameConverter.ToIplImage frameToImageConverter = new OpenCVFrameConverter.ToIplImage();
		Frame f = frameToImageConverter.convert(image);
		return f;
	}

	public static Frame getFrame(Mat image) {
		OpenCVFrameConverter.ToIplImage frameToImageConverter = new OpenCVFrameConverter.ToIplImage();
		Frame f = frameToImageConverter.convert(image);
		return f;
	}

	public static Mat convertToMat(BufferedImage image) {
		OpenCVFrameConverter.ToMat frame2matConv = new OpenCVFrameConverter.ToMat();
		Mat mat = frame2matConv.convert(getFrame(image));
		return mat;
	}

	public static BufferedImage convertFromMat(Mat image) {
		OpenCVFrameConverter.ToMat frame2matConv = new OpenCVFrameConverter.ToMat();
		Frame f = frame2matConv.convert(image);
		Java2DFrameConverter java2dConverter = new Java2DFrameConverter();
		return java2dConverter.convert(f);
	}

	public static Mat convertToMat(IplImage image) {
		OpenCVFrameConverter.ToIplImage img2frameConv = new OpenCVFrameConverter.ToIplImage();
		OpenCVFrameConverter.ToMat frame2matConv = new OpenCVFrameConverter.ToMat();
		Frame frame = img2frameConv.convert(image);
		Mat mat = frame2matConv.convert(frame);
		return mat;
	}

	public static IplImage convertToIplImage(Mat image) {
		OpenCVFrameConverter.ToIplImage img2frameConv = new OpenCVFrameConverter.ToIplImage();
		OpenCVFrameConverter.ToMat frame2matConv = new OpenCVFrameConverter.ToMat();
		Frame frame = frame2matConv.convert(image);
		IplImage img = img2frameConv.convert(frame);
		return img;
	}
}
