import java.util.StringTokenizer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EdgeField {
   public static Logger logger = LogManager.getLogger(EdgeField.class);

   private int numFigure, tableID, tableBound, fieldBound, dataType, varcharValue;
   private String name, defaultValue;
   private boolean disallowNull, isPrimaryKey;
   private static String[] strDataType = { "Varchar", "Boolean", "Integer", "Double" };
   public static final int VARCHAR_DEFAULT_LENGTH = 1;

   public EdgeField(String inputString) {
      logger.debug("Creating EdgeField with " + inputString);

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
      return numFigure;
   }

   public String getName() {
      return name;
   }

   public int getTableID() {
      return tableID;
   }

   public void setTableID(int value) {
      tableID = value;
   }

   public int getTableBound() {
      return tableBound;
   }

   public void setTableBound(int value) {
      tableBound = value;
   }

   public int getFieldBound() {
      return fieldBound;
   }

   public void setFieldBound(int value) {
      fieldBound = value;
   }

   public boolean getDisallowNull() {
      return disallowNull;
   }

   public void setDisallowNull(boolean value) {
      disallowNull = value;
   }

   public boolean getIsPrimaryKey() {
      return isPrimaryKey;
   }

   public void setIsPrimaryKey(boolean value) {
      isPrimaryKey = value;
   }

   public String getDefaultValue() {
      return defaultValue;
   }

   public void setDefaultValue(String value) {
      defaultValue = value;
   }

   public int getVarcharValue() {
      return varcharValue;
   }

   public void setVarcharValue(int value) {
      if (value > 0) {
         varcharValue = value;
         logger.info("setting varcharValue to " + value);
      } else {
         logger.warn("Varchar value " + value + " not above 0.");
      }
   }

   public int getDataType() {
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
