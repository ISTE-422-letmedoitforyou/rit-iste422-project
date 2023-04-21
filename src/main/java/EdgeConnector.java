import java.util.StringTokenizer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EdgeConnector {

   public static Logger logger = LogManager.getLogger(EdgeConnector.class.getName());
   public static Logger timeLogger = LogManager.getLogger("timer." + EdgeConnector.class.getName());

   private int numConnector, endPoint1, endPoint2;
   private String endStyle1, endStyle2;
   private boolean isEP1Field, isEP2Field, isEP1Table, isEP2Table;
      
   public EdgeConnector(String inputString) {
      timeLogger.info("start _init");
      logger.info("inputString: " + inputString);
      StringTokenizer st = new StringTokenizer(inputString, EdgeConvertFileParser.DELIM);
      numConnector = Integer.parseInt(st.nextToken());
      endPoint1 = Integer.parseInt(st.nextToken());
      endPoint2 = Integer.parseInt(st.nextToken());
      endStyle1 = st.nextToken();
      endStyle2 = st.nextToken();
      isEP1Field = false;
      isEP2Field = false;
      isEP1Table = false;
      isEP2Table = false;
      timeLogger.info("end _init");
   }
   
   public int getNumConnector() {
      logger.info("Getting Num Connector" + numConnector);
      return numConnector;
   }
   
   public int getEndPoint1() {
      logger.info("Getting EndPoint1: " + endPoint1);
      return endPoint1;
   }
   
   public int getEndPoint2() {
      logger.info("Getting EndPoint2: " + endPoint2);
      return endPoint2;
   }
   
   public String getEndStyle1() {
      logger.info("Getting EndStyle1: " + endStyle1);
      return endStyle1;
   }
   
   public String getEndStyle2() {
      logger.info("Getting EndStyle2: " + endStyle2);
      return endStyle2;
   }
   public boolean getIsEP1Field() {
      logger.info("Getting IsEP1Field: " + isEP1Field);
      return isEP1Field;
   }
   
   public boolean getIsEP2Field() {
      logger.info("Getting IsEP2Field: " + isEP2Field);
      return isEP2Field;
   }

   public boolean getIsEP1Table() {
      logger.info("Getting IsEP1Table: " + isEP1Table);
      return isEP1Table;
   }

   public boolean getIsEP2Table() {
      logger.info("Getting IsEP2Table: " + isEP2Table);
      return isEP2Table;
   }

   public void setIsEP1Field(boolean value) {
      logger.info("Setting IsEP1Field ");
      logger.debug("Setting to ... " + value);
      isEP1Field = value;
   }
   
   public void setIsEP2Field(boolean value) {
      logger.info("Setting IsEP2Field ");
      logger.debug("Setting to ... " + value);
      isEP2Field = value;
   }

   public void setIsEP1Table(boolean value) {
      logger.info("Setting IsEP1Table ");
      logger.debug("Setting to ... " + value);
      isEP1Table = value;
   }

   public void setIsEP2Table(boolean value) {
      logger.info("Setting IsEP2Table ");
      logger.debug("Setting to ... " + value);
      isEP2Table = value;
   }
}
