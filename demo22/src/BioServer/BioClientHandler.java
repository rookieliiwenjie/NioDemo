package BioServer;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Created by lwj32 on 2021/3/22.
 */

public class BioClientHandler  implements Runnable{

    private Socket socket;
    public BioClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream in = null;
        try {
            in = socket.getInputStream();
            int count = 0;
            byte[] bytes = new byte[1024];
            while ((count=in.read(bytes))!=-1){
                System.out.println("\n收到服务器消息：  "+new String(bytes,0,count,"utf-8"));
                System.out.print("请输入要发送的消息：");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
