package BioServer;/**
 * Created by lwj32 on 2021/3/22.
 */

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BioServerHandler implements Runnable {

    private Socket socket;

    public BioServerHandler(Socket scoket) {
        this.socket = scoket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            int count = 0;
            String content = null;
            byte[] bytes = new byte[1024];
            while ((count = inputStream.read(bytes)) != -1) {
                String s = new String(bytes, 0, count, "utf-8");
                System.out.println("s = " + s);
                content = s.trim().equalsIgnoreCase("SJ") ? new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) : "你发的啥？";
                outputStream.write(content.getBytes());
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
