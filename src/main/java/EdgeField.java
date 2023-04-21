import java.util.Arrays;
import java.util.StringTokenizer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EdgeField {
   public static Logger logger = LogManager.getLogger(EdgeField.class.getName());

   private int numFigure, tableID, tableBound, fieldBound, dataType, varcharValue;
   private String name, defaultValue;
   private boolean disallowNull, isPrimaryKey;
   private static String[] strDataType = { "Varchar", "Boolean", "Integer", "Double" };
   public static final int VARCHAR_DEFAULT_LENGTH = 1;

   public EdgeField(String inputString) {
      logger.info("Creating EdgeField " + inputString);

      StringTokenizer st = new StringTokenizer(inputString, EdgeConvertFileParser.DELIM);
      numFigure = Integer.parseInt(st.nextToken());
      name = st.nextToken();
      tableID = 0;
      tableBound = 0;
      fieldBound = 0;
      disallowNull = false;
      isPrimaryKey = false;
      defaultValue = "";
      varcharValue = VARCHAR_DEFAULT_LENGTH;
      dataType = 0;
   }

   public int getNumFigure() {
      logger.info("Retrieving numFigure " + numFigure);
      return numFigure;
   }

   public String getName() {
      logger.info("Retrieving name " + name);
      return name;
   }

   public int getTableID() {
      logger.info("Retrieving tableID " + tableID);
      return tableID;
   }

   public void setTableID(int value) {
      logger.info("Setting table id");
      logger.debug("Table id for field is now " + name + ": " + value);
      tableID = value;
   }

   public int getTableBound() {
      logger.info("Table Bound : " + tableBound);
      return tableBound;
   }

   public void setTableBound(int value) {
      logger.info("Setting Table Bound");
      logger.debug("Field " + name + " is now bound to table " + value);
      tableBound = value;
   }

   public int getFieldBound() {
      logger.info("Retrieving Field Bound : " + fieldBound);
      return fieldBound;
   }

   public void setFieldBound(int value) {
      logger.info("Setting Field Bound");
      logger.debug("Field " + name + " is now bound to " + value);
      fieldBound = value;
   }

   public boolean getDisallowNull() {
      logger.info("Current disallow null value : " + disallowNull);
      return disallowNull;
   }

   public void setDisallowNull(boolean value) {
      logger.info("Setting disallow null : " + value);
      logger.debug("Disallow null is now set to " + value);
      disallowNull = value;
   }

   public boolean getIsPrimaryKey() {
      logger.info("Primary key : " + isPrimaryKey);
      return isPrimaryKey;
   }

   public void setIsPrimaryKey(boolean value) {
      logger.info("Setting primary key to " + value);
      logger.debug("Primary key now set to " + value);
      isPrimaryKey = value;
   }

   public String getDefaultValue() {
      String reporting = defaultValue.equals("") ? defaultValue : "none";
      logger.info("Default value is set " + reporting);
      return defaultValue;
   }

   public void setDefaultValue(String value) {
      logger.info("Setting default value");
      logger.debug("Default value is now " + value);
      defaultValue = value;
   }

   public int getVarcharValue() {
      logger.info("varcharValue is " + varcharValue);
      return varcharValue;
   }

   public void setVarcharValue(int value) {
      if (value > 0) {
         varcharValue = value;
         logger.debug("setting varcharValue to " + value);
      } else {
         logger.warn("Varchar value " + value + " not above 0.");
      }
   }

   public int getDataType() {
      logger.info("Field data type : " + dataType);
      return dataType;
   }

   public void setDataType(int value) {
      if (value >= 0 && value < strDataType.length) {
         dataType = value;
         logger.info("setting dataType to " + value);

      } else {
         logger.warn("Data type " + value + " not between 0 and data type length " + value);
      }
   }

   public static String[] getStrDataType() {
      logger.debug("String data types : " + strDataType[0] + " " + strDataType[1] + " " + strDataType[2] + " "
            + strDataType[3]);
      return strDataType;
   }

   public String toString() {
      return numFigure + EdgeConvertFileParser.DELIM +
            name + EdgeConvertFileParser.DELIM +
            tableID + EdgeConvertFileParser.DELIM +
            tableBound + EdgeConvertFileParser.DELIM +
            fieldBound + EdgeConvertFileParser.DELIM +
            dataType + EdgeConvertFileParser.DELIM +
            varcharValue + EdgeConvertFileParser.DELIM +
            isPrimaryKey + EdgeConvertFileParser.DELIM +
            disallowNull + EdgeConvertFileParser.DELIM +
            defaultValue;
   }
}
