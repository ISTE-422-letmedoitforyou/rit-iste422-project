import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EdgeFieldTest {
    EdgeField testEdgeField;

    @Before
    public void setUp() throws Exception {
        testEdgeField = new EdgeField("3|Grade|0|0|0|0|1|false|false|");
        /*
         * number figure | name | tableID | tableBound | fieldBound | disallowNull |
         * PrimaryKey | DefaultValue | varcharValue | dataType
         */
        /*
         * initialized to input number, name, tableID/tableBound/fieldBound are 0,
         * disallowNull/isPrimaryKey are false, defaultValue is "", varcharvalue is
         * default length (1), dataType is 0
         */
    }

    @Test
    public void testGetNumFigure() {
        assertEquals("testEdgeField was initialized with the numFigure 3", 3, testEdgeField.getNumFigure());
    }

    @Test
    public void testGetName() {
        assertEquals("testEdgeField was initialized with the name 'Grade'", "Grade", testEdgeField.getName());
    }

    @Test
    public void testGetTableID() {
        assertEquals("testEdgeField was initialized with the table ID 0'", 0, testEdgeField.getTableID());
    }

    @Test
    public void testGetTableBound() {
        assertEquals("testEdgeField was initialized with the table bound 0", 0, testEdgeField.getTableBound());
    }

    @Test
    public void testGetFieldBound() {
        assertEquals("testEdgeField was initialized with the field bound 0", 0, testEdgeField.getFieldBound());
    }

    @Test
    public void testGetDisallowNull() {
        assertEquals("testEdgeField was initialized with disallow null set to false", false,
                testEdgeField.getDisallowNull());
    }

    @Test
    public void testGetIsPrimaryKey() {
        assertEquals("testEdgeField was initialized with primary key set to false", false,
                testEdgeField.getIsPrimaryKey());
    }

    @Test
    public void testGetDefaultValue() {
        assertEquals("testEdgeField was initialized with default value set to ''", "", testEdgeField.getDefaultValue());
    }

    @Test
    public void testGetVarcharValue() {
        assertEquals("testEdgeField was initialized with varchar value 1", 1, testEdgeField.getVarcharValue());
    }

    @Test
    public void testGetDataType() {
        assertEquals("testEdgeField was initialized with data type set to 0", 0, testEdgeField.getDataType());
    }

    @Test
    public void testGetStrDataType() {
        String[] checkDataType = { "Varchar", "Boolean", "Integer", "Double" };
        assertArrayEquals("testEdgeField was initialized with data type options", checkDataType,
                testEdgeField.getStrDataType());
    }

    @Test
    public void testToString() {
        assertEquals("testEdgeField was initialized to be reported in format | | | | with correct values",
                "3|Grade|0|0|0|0|1|false|false|",
                testEdgeField.toString());
    }

    /*---testing setters ----*/
    @Test
    public void testSetTableID() {
        testEdgeField.setTableID(12);
        assertEquals("testEdgeField table ID set to 12'", 12, testEdgeField.getTableID());
    }

    @Test
    public void testSetTableBound() {
        testEdgeField.setTableBound(1);
        assertEquals("testEdgeField table bound set to 1", 1, testEdgeField.getTableBound());
    }

    @Test
    public void testSetFieldBound() {
        testEdgeField.setFieldBound(1);
        assertEquals("testEdgeField was initialized with the field bound 1", 1, testEdgeField.getFieldBound());
    }

    @Test
    public void testSetDisallowNull() {
        testEdgeField.setDisallowNull(true);
        assertEquals("testEdgeField disallow set to true", true, testEdgeField.getDisallowNull());
    }

    @Test
    public void testSetIsPrimaryKey() {
        testEdgeField.setIsPrimaryKey(true);
        assertEquals("testEdgeField primary key set to true", true, testEdgeField.getIsPrimaryKey());
    }

    @Test
    public void testSetDefaultValue() {
        testEdgeField.setDefaultValue("empty");
        assertEquals("testEdgeField default value set to 'empty'", "empty", testEdgeField.getDefaultValue());
    }

    @Test
    public void testSetVarcharValue() {
        testEdgeField.setVarcharValue(1);
        assertEquals("testEdgeField varchar value set to 1", 1, testEdgeField.getVarcharValue());
    }

    @Test
    public void testSetDataType() {
        testEdgeField.setDataType(2);
        assertEquals("testEdgeField set to data type 2", 2, testEdgeField.getDataType());
    }

    /*---testing valid data types --- */
    @Test
    public void testSetVarcharValueOutOfBounds() {
        testEdgeField.setVarcharValue(-1);
        assertEquals("testEdgeField varchar value should not be set to -1", 1, testEdgeField.getVarcharValue());
    }

    @Test
    public void testSetDataTypeOutOfBounds() {
        testEdgeField.setDataType(600);
        assertEquals("testEdgeField data type should not be set to 600", 0, testEdgeField.getDataType());
    }

}
