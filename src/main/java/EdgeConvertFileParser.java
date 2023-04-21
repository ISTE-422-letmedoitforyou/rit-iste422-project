import java.io.*;
import java.util.*;
import javax.swing.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class EdgeConvertFileParser {
   public static Logger logger = LogManager.getLogger(EdgeConvertFileParser.class);

   protected File parseFile;
   protected FileReader fr;
   protected BufferedReader br;
   protected String currentLine;
   protected ArrayList alTables;

   protected ArrayList alFields;

   protected ArrayList alConnectors;
   protected EdgeTable[] tables;
   protected EdgeField[] fields;
   protected EdgeField tempField;
   protected EdgeConnector[] connectors;
   protected String style;
   protected String text;
   protected String tableName;
   protected String fieldName;
   protected boolean isEntity;

   protected boolean isAttribute;

   protected boolean isUnderlined = false;
   protected int numFigure;

   protected int numConnector;

   protected int numFields;

   protected int numTables;

   protected int numNativeRelatedFields;
   protected int endPoint1;

   protected int endPoint2;
   protected int numLine;
   protected String endStyle1;

   protected String endStyle2;
   public static final String EDGE_ID = "EDGE Diagram File"; // first line of .edg files should be this
   public static final String SAVE_ID = "EdgeConvert Save File"; // first line of save files should be this
   public static final String DELIM = "|";

   public EdgeConvertFileParser(File constructorFile) {
      logger.info("Creating EdgeConvertFileParser with " + constructorFile);
      numFigure = 0;
      numConnector = 0;
      alTables = new ArrayList();
      alFields = new ArrayList();
      alConnectors = new ArrayList();
      isEntity = false;
      isAttribute = false;
      parseFile = constructorFile;
      numLine = 0;
   }

   protected void resolveConnectors() { // Identify nature of Connector endpoints
      logger.debug("Connector endpoints being resolved.");
      int endPoint1, endPoint2;
      int fieldIndex = 0, table1Index = 0, table2Index = 0;
      for (int cIndex = 0; cIndex < connectors.length; cIndex++) {
         endPoint1 = connectors[cIndex].getEndPoint1();
         endPoint2 = connectors[cIndex].getEndPoint2();
         fieldIndex = -1;
         for (int fIndex = 0; fIndex < fields.length; fIndex++) { // search fields array for endpoints
            if (endPoint1 == fields[fIndex].getNumFigure()) { // found endPoint1 in fields array
               connectors[cIndex].setIsEP1Field(true); // set appropriate flag
               fieldIndex = fIndex; // identify which element of the fields array that endPoint1 was found in
            }
            if (endPoint2 == fields[fIndex].getNumFigure()) { // found endPoint2 in fields array
               connectors[cIndex].setIsEP2Field(true); // set appropriate flag
               fieldIndex = fIndex; // identify which element of the fields array that endPoint2 was found in
            }
         }
         for (int tIndex = 0; tIndex < tables.length; tIndex++) { // search tables array for endpoints
            if (endPoint1 == tables[tIndex].getNumFigure()) { // found endPoint1 in tables array
               connectors[cIndex].setIsEP1Table(true); // set appropriate flag
               table1Index = tIndex; // identify which element of the tables array that endPoint1 was found in
            }
            if (endPoint2 == tables[tIndex].getNumFigure()) { // found endPoint1 in tables array
               connectors[cIndex].setIsEP2Table(true); // set appropriate flag
               table2Index = tIndex; // identify which element of the tables array that endPoint2 was found in
            }
         }

         if (connectors[cIndex].getIsEP1Field() && connectors[cIndex].getIsEP2Field()) { // both endpoints are fields,
                                                                                         // implies lack of
                                                                                         // normalization
            JOptionPane.showMessageDialog(null, "The Edge Diagrammer file\n" + parseFile
                  + "\ncontains composite attributes. Please resolve them and try again.");
            EdgeConvertGUI.setReadSuccess(false); // this tells GUI not to populate JList components
            logger.warn(
                  "The edge diagrammer file should not contain composite attributes. Composite attributes are included in "
                        + parseFile);
            break; // stop processing list of Connectors
         }

         if (connectors[cIndex].getIsEP1Table() && connectors[cIndex].getIsEP2Table()) { // both endpoints are tables
            if ((connectors[cIndex].getEndStyle1().indexOf("many") >= 0) &&
                  (connectors[cIndex].getEndStyle2().indexOf("many") >= 0)) { // the connector represents a many-many
                                                                              // relationship, implies lack of
                                                                              // normalization
               JOptionPane.showMessageDialog(null,
                     "There is a many-many relationship between tables\n\"" + tables[table1Index].getName()
                           + "\" and \"" + tables[table2Index].getName() + "\""
                           + "\nPlease resolve this and try again.");
               EdgeConvertGUI.setReadSuccess(false); // this tells GUI not to populate JList components
               logger.warn("Many-many relationship between tables\n\"" + tables[table1Index].getName() + "\" and \""
                     + tables[table2Index].getName());
               break; // stop processing list of Connectors
            } else { // add Figure number to each table's list of related tables
               tables[table1Index].addRelatedTable(tables[table2Index].getNumFigure());
               tables[table2Index].addRelatedTable(tables[table1Index].getNumFigure());
               continue; // next Connector
            }
         }

         if (fieldIndex >= 0 && fields[fieldIndex].getTableID() == 0) { // field has not been assigned to a table yet
            if (connectors[cIndex].getIsEP1Table()) { // endpoint1 is the table
               tables[table1Index].addNativeField(fields[fieldIndex].getNumFigure()); // add to the appropriate table's
                                                                                      // field list
               fields[fieldIndex].setTableID(tables[table1Index].getNumFigure()); // tell the field what table it
                                                                                  // belongs to
            } else { // endpoint2 is the table
               tables[table2Index].addNativeField(fields[fieldIndex].getNumFigure()); // add to the appropriate table's
                                                                                      // field list
               fields[fieldIndex].setTableID(tables[table2Index].getNumFigure()); // tell the field what table it
                                                                                  // belongs to
            }
         } else if (fieldIndex >= 0) { // field has already been assigned to a table
            JOptionPane.showMessageDialog(null, "The attribute " + fields[fieldIndex].getName()
                  + " is connected to multiple tables.\nPlease resolve this and try again.");
            EdgeConvertGUI.setReadSuccess(false); // this tells GUI not to populate JList components
            logger.warn("The attribute " + fields[fieldIndex].getName() + " is connected to multiple tables.");
            break; // stop processing list of Connectors
         }
      } // connectors for() loop
   } // resolveConnectors()

   protected void makeArrays() { // convert ArrayList objects into arrays of the appropriate Class type
      if (alTables != null) {
         tables = (EdgeTable[]) alTables.toArray(new EdgeTable[alTables.size()]);
         logger.debug("Array of tables made: " + Arrays.toString(tables));
      } else {
         logger.warn("Tables are null.");
      }
      if (alFields != null) {
         fields = (EdgeField[]) alFields.toArray(new EdgeField[alFields.size()]);
         logger.debug("Array of fields made: " + Arrays.toString(fields));
      } else {
         logger.warn("Fields are null.");
      }
      if (alConnectors != null) {
         connectors = (EdgeConnector[]) alConnectors.toArray(new EdgeConnector[alConnectors.size()]);
         logger.debug("Number of connectors made: " + connectors.length);
      } else {
         logger.warn("Connectors are null.");
      }
   }

   protected boolean isTableDup(String testTableName) {
      for (int i = 0; i < alTables.size(); i++) {
         EdgeTable tempTable = (EdgeTable) alTables.get(i);
         if (tempTable.getName().equals(testTableName)) {
            logger.warn("Table " + testTableName + "checked, is found as a duplicate.");
            return true;
         }
      }
      logger.info("Table " + testTableName + " checked, is not duplicate.");
      return false;
   }

   public EdgeTable[] getEdgeTables() {
      if (fields != null) {
         logger.info("EdgeTables found : " + Arrays.toString(tables));
      } else {
         logger.warn("EdgeTables are null.");
      }
      return tables;
   }

   public EdgeField[] getEdgeFields() {
      if (fields != null) {
         logger.info("EdgeFields found : " + Arrays.toString(fields));
      } else {
         logger.warn("EdgeFields are null.");
      }
      return fields;
   }

} // EdgeConvertFileHandler