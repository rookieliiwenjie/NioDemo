package BioServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by lwj32 on 2021/3/17.
 */
public class BioClient {
    private static final TimeServerHandlerExecutorPool timeServerHandlerExecutorPool = new TimeServerHandlerExecutorPool(100, 100);

    public static void main(String[] args) {
        Socket socket = null;
        OutputStream outputStream = null;
        try {
            socket = new Socket("127.0.0.1", 9092);
            //循环读
            timeServerHandlerExecutorPool.execute(new BioClientHandler(socket));
            outputStream = socket.getOutputStream();
            Scanner scanner = new Scanner(System.in);
            System.out.println(" 请输入要发送的数据");
            while (true) {
                String s = scanner.nextLine();
                if (s.trim().equals("by")) {
                    break;
                }
                outputStream.write(s.getBytes());
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
