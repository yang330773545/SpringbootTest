package com.vinzor;

import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

/**
 * @Author: 33077
 * @Date: 2025-07-17 11:11
 * @Deprecated:
 */
public class CameraCapture {
    private VideoCapture capture;
    private Mat frame;

    public CameraCapture() {
        OpenCV.loadShared();
        capture = new VideoCapture(0);
        frame = new Mat();
    }

    public Mat captureFrame() {
        if(capture.isOpened()) {
            capture.read(frame);
        }
        return frame;
    }

    public void release() {
        capture.release();
    }
}
