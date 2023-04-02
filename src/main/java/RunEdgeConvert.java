import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RunEdgeConvert {
   static {
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tF %1$tT %1$tL] [%4$-7s] %5$s %n");
        System.setProperty("java.util.logging.ConsoleHandler.level", "FILE");
    }

   public static void main(String[] args) {
      Logger logger = LogManager.getLogger(RunEdgeConvert.class);
      logger.info("running new edge convert GUI");
      EdgeConvertGUI edge = new EdgeConvertGUI();
   }
}