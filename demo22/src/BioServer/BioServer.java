package BioServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by lwj32 on 2021/3/22.
 */
public class BioServer {
    ServerSocket serverSocket = null;
    TimeServerHandlerExecutorPool timeServerHandlerExecutorPool = new TimeServerHandlerExecutorPool(100,100);
    private BioServer() {

        try {
            serverSocket = new ServerSocket(9092);
            while (true){
                //阻塞
                Socket scoket = serverSocket.accept();
                System.out.println("客户端连接来了 "+ scoket.getRemoteSocketAddress().toString() );
              //  new Thread(new BioServerHandler(scoket)).start();
                timeServerHandlerExecutorPool.execute(new BioServerHandler(scoket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        BioServer bioServer = new BioServer();
    }
}

