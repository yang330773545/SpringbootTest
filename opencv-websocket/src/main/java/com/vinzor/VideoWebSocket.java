package com.vinzor;

import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import java.nio.ByteBuffer;

import static org.opencv.imgcodecs.Imgcodecs.imencode;

/**
 * @Author: 33077
 * @Date: 2025-07-17 11:14
 * @Deprecated:
 */
@Component
@ServerEndpoint("/video")
public class VideoWebSocket {
    private CameraCapture cameraCapture = new CameraCapture();

    @OnOpen
    public void onOpen(Session session) {
        new Thread(() -> {
            while (true) {
                Mat frame = cameraCapture.captureFrame();
                if (frame != null) {
                    try {
                        session.getBasicRemote().sendBinary(ByteBuffer.wrap(this.encodeFrameToJPEG(frame)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private byte[] encodeFrameToJPEG(Mat frame) {
        MatOfByte matOfByte = new MatOfByte();
        imencode(".jpg", frame, matOfByte);
        return matOfByte.toArray();
    }
    @OnClose
    public void onClose() {
        cameraCapture.release();
    }
}
