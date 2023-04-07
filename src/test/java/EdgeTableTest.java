
//Catherine Liu


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EdgeTableTest {
	EdgeTable testEdgeTable;

//initialized EdgeTable object
	@Before
	public void setUp() throws Exception {
		testEdgeTable = new EdgeTable("1|testEdgeTable");
	}

//testing getting fields of the object
	@Test
	public void testGetNumFigure() {
		assertEquals("numFigure was intialized to 1 so it should be 1",1,testEdgeTable.getNumFigure());
	}

	@Test
	public void testGetName() {
		assertEquals("Name was initialized to testEdgeTable","testEdgeTable",testEdgeTable.getName());
	}

    @Test
	public void testGetAlRelatedTables() {
        testEdgeTable.makeArrays();
		assertEquals("AlRelatedTables was initialized and empty",0,testEdgeTable.getRelatedTablesArray().length);
	}

    @Test
	public void testGetAlNativeFields() {
		testEdgeTable.makeArrays();
		assertEquals("AlNavtiveFields was initialized and empty",0,testEdgeTable.getNativeFieldsArray().length);
	}

//testing add related table
	@Test
	public void testAddRelatedTable() {
        testEdgeTable.addRelatedTable(2);
        testEdgeTable.makeArrays();
		assertEquals("Related Table 2 was added. 2 should be the first element in the table",2,testEdgeTable.getRelatedTablesArray()[0]);
	}

//testing setting related field
	@Test
	public void testSetRelatedField() {
        testEdgeTable.addNativeField(1);
        testEdgeTable.makeArrays();
        testEdgeTable.setRelatedField(0,3);
		assertEquals("Related Field is set to 3 and should be 3",3,testEdgeTable.getRelatedFieldsArray()[0]);
	}

//testing add related native field
	@Test
	public void testAddNativeField() {
        testEdgeTable.addNativeField(1);
        testEdgeTable.makeArrays();
		assertEquals("Native Field of 1 is added and should be 1",1,testEdgeTable.getNativeFieldsArray()[0]);
	}

//testing move field up
	@Test
	public void testMoveFieldUpNativeField() {
        //[4,5,6,7]
        testEdgeTable.addNativeField(4);
        testEdgeTable.addNativeField(5);
        testEdgeTable.addNativeField(6);
        testEdgeTable.addNativeField(7);
        testEdgeTable.makeArrays();
        //[4,5,7,6]
        testEdgeTable.moveFieldUp(3);
		assertArrayEquals("Field at index 3 is moved up, [4,5,7,6] should be the new arraylist",new int[]{4,5,7,6},testEdgeTable.getNativeFieldsArray());
	}

//testing move field up when index input is the first one
    @Test
	public void testMoveFirstFieldUpNativeField() {
        //[4,5,6,7]
        testEdgeTable.addNativeField(4);
        testEdgeTable.addNativeField(5);
        testEdgeTable.addNativeField(6);
        testEdgeTable.addNativeField(7);
        testEdgeTable.makeArrays();
        testEdgeTable.moveFieldUp(0);
		assertArrayEquals("Field at index 0 is moved up, nothing should change",new int[]{4,5,6,7},testEdgeTable.getNativeFieldsArray());
	}
//testing move field down
    @Test
	public void testMoveFieldDownNativeField() {
        //[4,5,6,7]
        testEdgeTable.addNativeField(4);
        testEdgeTable.addNativeField(5);
        testEdgeTable.addNativeField(6);
        testEdgeTable.addNativeField(7);
        testEdgeTable.makeArrays();
        //[4,5,7,6]
        testEdgeTable.moveFieldDown(2);
		assertArrayEquals("Field at index 3 is moved down, [4,5,7,6] should be the new arraylist",new int[]{4,5,7,6},testEdgeTable.getNativeFieldsArray());
	}

//testing move field down when index input is the last one
    @Test
	public void testMoveLastFieldDownNativeField() {
        //[4,5,6,7]
        testEdgeTable.addNativeField(4);
        testEdgeTable.addNativeField(5);
        testEdgeTable.addNativeField(6);
        testEdgeTable.addNativeField(7);
        testEdgeTable.makeArrays();
        testEdgeTable.moveFieldDown(3);
		assertArrayEquals("Field at index 0 is moved down, nothing should change",new int[]{4,5,6,7},testEdgeTable.getNativeFieldsArray());
	}

//testing make array

    @Test
    public void testMakeArrays_NativeField(){
        testEdgeTable.addNativeField(1);
        testEdgeTable.addNativeField(2);
        testEdgeTable.addNativeField(3);
        testEdgeTable.makeArrays();
        assertArrayEquals("Making an Integer ArrayList ",new int[]{1,2,3},testEdgeTable.getNativeFieldsArray());
    }

    @Test
    public void testMakeArrays_RelatedTable(){
        testEdgeTable.addRelatedTable(8);
        testEdgeTable.addRelatedTable(9);
        testEdgeTable.addRelatedTable(10);
        testEdgeTable.makeArrays();
        assertArrayEquals("Making an Integer ArrayList ",new int[]{8,9,10},testEdgeTable.getRelatedTablesArray());
    }

//testing toString

// Table: 1
// TableName: testEdgeTable
// NativeFields: 1|2|3
// RelatedTables: 8|9|10
// RelatedFields: 4|5|6

    @Test
    public void testtoString() {
        testEdgeTable.addNativeField(1);
        testEdgeTable.addNativeField(2);
        testEdgeTable.addNativeField(3);
        testEdgeTable.addRelatedTable(8);
        testEdgeTable.addRelatedTable(9);
        testEdgeTable.addRelatedTable(10);
        testEdgeTable.makeArrays();
        testEdgeTable.setRelatedField(0, 4);
        testEdgeTable.setRelatedField(1, 5);
        testEdgeTable.setRelatedField(2, 6);
    assertEquals("String should look like", new String("Table: 1\r\n{\r\nTableName: testEdgeTable\r\nNativeFields: 1|2|3\r\nRelatedTables: 8|9|10\r\nRelatedFields: 4|5|6\r\n}\r\n"), testEdgeTable.toString());
  }




}
