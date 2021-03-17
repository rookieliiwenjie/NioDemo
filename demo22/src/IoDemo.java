import javax.management.monitor.Monitor;
import java.util.concurrent.TimeUnit;

/**
 * Created by lwj32 on 2021/3/17.
 */
public class IoDemo {
    public static void main(String[] args) {
        //异步阻塞就是通过回调或者事件的机制，等待返回结果这个期间是可以做其他事情的
       /* synchronizedMehtod(new Mointor() {
            @Override
            public int returnReusult(int x) {
                System.out.println("x = " + x);
                return x;
            }
        }, 1, 2);
        new Mointor2();*/
        //异步阻塞2
        Mointor2 mointor2 = new Mointor2();
        synchronizedMehtod(mointor2, 1, 2);
        System.out.println(mointor2.returnReusult() + "---异步阻塞2");

    }

    static class Mointor2 {
        private Object returnData;
        private boolean isDone;

        public Object getReturnData() {
            return returnData;
        }

        public void setReturnData(Object returnData) {
            this.returnData = returnData;
        }

        public void setDone(boolean done) {
            isDone = done;
        }

        public Object returnReusult() {
            while (!isDone) {
                //System.out.println(" =-" );
                return returnReusult();

            }
            return getReturnData();
        }
    }

    private static void synchronizedMehtod(Mointor monitor, int i, int i1) {
        int c = i + i1;
        System.out.println("synchronizedMehtod");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        monitor.returnReusult(c);
    }

    private static void synchronizedMehtod(Mointor2 monitor, int a, int b) {
        int c = a + b;
        monitor.setReturnData(c);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        monitor.setDone(true);
    }

    interface Mointor {
        int returnReusult(int x);
    }
}
