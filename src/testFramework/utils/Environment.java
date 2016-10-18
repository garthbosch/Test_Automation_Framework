package testFramework.utils;

/**
 *
 * @author Garth Bosch
 */   
public class Environment {

//    public static final String ENV = "DEV";
//    public static final String ENV = "QA";
//    public static final String ENV = "TRG";
//public static final String ENV = "PROD";        

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//    OneFM Environments
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public static String getOneFM(String env) throws Exception {        
        if (env.equals("DEV")) {
            return "http://omgs.intranet.dev/OneFM/";
        } else if (env.equals("QA")) {
            return "http://omgs.intranet.qai/OneFM/";
        } else if (env.equals("PROD")) {
            return "No Access!!!";
        }
        throw new Exception("what the heck!!!");
    }            
}
