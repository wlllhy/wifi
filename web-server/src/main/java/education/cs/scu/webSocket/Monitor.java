package education.cs.scu.webSocket;

import education.cs.scu.DBHelper.DataDBManager;
import education.cs.scu.entity.UserVisitBean;
import education.cs.scu.service.UserVisitService;
import education.cs.scu.webSocket.handler.WebSocketEndPointTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Monitor implements Runnable {

//    UserDiagramData userDiagramData = new UserDiagramData();;
//    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    int getRandomNumber(){
//        return (int)(Math.random() * 300);
//    }


    private static ResultSet rs = null;
    private static DataDBManager clockDataDBManager = null;
    private UserVisitBean userFlow = new UserVisitBean();
    List<Integer> shopList = new ArrayList<Integer>();
    @Override
    public void run() {
        try {
            ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{
                    "classpath:applicationContext-service.xml", "classpath:applicationContext-redis.xml", "classpath:applicationContext-dao.xml", "classpath:springMVC.xml"});

            UserVisitService userVisitService = (UserVisitService) ctx.getBean("userVisitService");
           // List<Integer> shopList = userVisitService.queryShopList("18996720676");
            shopList.add(1);

            List<UserVisitBean> userVisitBeanList = new ArrayList<UserVisitBean>();
            if(shopList !=null) {
                userVisitBeanList = userVisitService.queryUserVisit(shopList);
                if(userVisitBeanList != null && userVisitBeanList.size() > 0) {
                    WebSocketEndPointTest webSocketEndPointTest = new WebSocketEndPointTest();
                    webSocketEndPointTest.sendMsg(userVisitBeanList.get(0));
                }else{
                    //System.out.println("no data");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void sendMsg() {
        System.out.println("sendMsg");
        ScheduledExecutorService newScheduledThreadPool = Executors.newSingleThreadScheduledExecutor();
        Random random = new Random();
//        int delay = random.nextInt(2) + 1;
        int delay = 3;
        System.out.println("delay = " + delay);

        /*
        * 第一个command参数是任务实例，
        第二个initialDelay参数是初始化延迟时间，
        第三个period参数是间隔时间，
        第四个unit参数是时间单元。
        * */
        newScheduledThreadPool.scheduleWithFixedDelay(new Monitor(), 1, delay, TimeUnit.SECONDS);
    }
}

