package education.cs.scu.javautils;

import java.util.Random;

public class VerifyCodeUtil {
    private static int max=999999;
    private static int min=100000;
    public static int createVerifyCode(){
        Random random = new Random();
        return random.nextInt(max)%(max-min+1) + min;
    }
}
