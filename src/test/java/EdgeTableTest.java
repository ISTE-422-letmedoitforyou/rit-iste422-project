
//Catherine Liu


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EdgeTableTest {
	EdgeTable testObj;

	@Before
	public void setUp() throws Exception {
		testObj = new EdgeTable("1|testEdgeTable");
	}

	@Test
	public void testGetNumFigure() {
		assertEquals("numFigure was intialized to 1 so it should be 1",1,testObj.getNumFigure());
	}

	@Test
	public void testGetName() {
		assertEquals("Name was initialized to testEdgeTable","testEdgeTable",testObj.getName());
	}

    @Test
	public void testGetAlRelatedTables() {
        testObj.makeArrays();
		assertEquals("AlRelatedTables was inistialized and empty",0,testObj.getRelatedTablesArray().length);
	}

    @Test
	public void testGetAlNativeFields() {
		testObj.makeArrays();
		assertEquals("AlNavtiveFields was inistialized and empty",0,testObj.getNativeFieldsArray().length);
	}

	@Test
	public void testAddRelatedTable() {
        testObj.addRelatedTable(2);
        testObj.makeArrays();
		assertEquals("Related Table 2 was added. 2 should be the first element in the table",2,testObj.getRelatedTablesArray()[0]);
	}


	@Test
	public void testSetRelatedField() {
        testObj.addNativeField(1);
        testObj.makeArrays();
        testObj.setRelatedField(0,3);
		assertEquals("Related Field is set to 3 and should be 3",3,testObj.getRelatedFieldsArray()[0]);
	}


	@Test
	public void testAddNativeField() {
        testObj.addNativeField(1);
        testObj.makeArrays();
		assertEquals("Native Field of 1 is added and should be 1",1,testObj.getNativeFieldsArray()[0]);
	}

	@Test
	public void testMoveFieldUpNativeField() {
        //[4,5,6,7]
        testObj.addNativeField(4);
        testObj.addNativeField(5);
        testObj.addNativeField(6);
        testObj.addNativeField(7);
        testObj.makeArrays();
        //[4,5,7,6]
        testObj.moveFieldUp(3);
		assertArrayEquals("Field at index 3 is moved up, [4,5,7,6] should be the new arraylist",new int[]{4,5,7,6},testObj.getNativeFieldsArray());
	}

    @Test
	public void testMoveFirstFieldUpNativeField() {
        //[4,5,6,7]
        testObj.addNativeField(4);
        testObj.addNativeField(5);
        testObj.addNativeField(6);
        testObj.addNativeField(7);
        testObj.makeArrays();
        testObj.moveFieldUp(0);
		assertArrayEquals("Field at index 0 is moved up, nothing should change",new int[]{4,5,6,7},testObj.getNativeFieldsArray());
	}

    @Test
	public void testMoveFieldDownNativeField() {
        //[4,5,6,7]
        testObj.addNativeField(4);
        testObj.addNativeField(5);
        testObj.addNativeField(6);
        testObj.addNativeField(7);
        testObj.makeArrays();
        //[4,5,7,6]
        testObj.moveFieldDown(2);
		assertArrayEquals("Field at index 3 is moved up, [4,5,7,6] should be the new arraylist",new int[]{4,5,7,6},testObj.getNativeFieldsArray());
	}

    @Test
	public void testMoveLastFieldDownNativeField() {
        //[4,5,6,7]
        testObj.addNativeField(4);
        testObj.addNativeField(5);
        testObj.addNativeField(6);
        testObj.addNativeField(7);
        testObj.makeArrays();
        testObj.moveFieldDown(3);
		assertArrayEquals("Field at index 0 is moved up, nothing should change",new int[]{4,5,6,7},testObj.getNativeFieldsArray());
	}

   


}
