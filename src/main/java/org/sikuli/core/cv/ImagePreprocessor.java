/*******************************************************************************
 * Copyright 2011 sikuli.org
 * Released under the MIT license.
 * 
 * Contributors:
 *     Tom Yeh - initial API and implementation
 ******************************************************************************/
package org.sikuli.core.cv;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

public class ImagePreprocessor {
	
	public static IplImage createLab(BufferedImage input){
		IplImage color = ImageConverter.convert(input);
		IplImage rgb = IplImage.create(cvGetSize(color), 8, 3);
		cvCvtColor(color,rgb,CV_BGRA2RGB);
		IplImage lab = IplImage.createCompatible(rgb);
		cvCvtColor(rgb, lab, CV_RGB2Lab );
		return lab;
	}

	public static IplImage createHSV(BufferedImage input){
		IplImage color = ImageConverter.convert(input);
		IplImage rgb = IplImage.create(cvGetSize(color), 8, 3);
		cvCvtColor(color,rgb,CV_BGRA2RGB);
		IplImage hsv = IplImage.createCompatible(rgb);
		cvCvtColor(rgb, hsv, CV_RGB2HSV );
		return hsv;
	}

	public static IplImage createGrayscale(BufferedImage input) {
		return VisionUtils.createGrayImageFrom(input);
	}

	public static IplImage createGrayscale(IplImage input) {
		return VisionUtils.createGrayImageFrom(input);		
	}
}
