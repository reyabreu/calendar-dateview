/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.reyabreu.dateview.model;

import java.util.Calendar;
import java.util.Date;
import junit.framework.TestCase;

/**
 *
 * @author Reynaldo
 */
public class DateViewModelImplTest extends TestCase {

    public DateViewModelImplTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of addDay method, of class DateViewModelImpl.
     */
    public void testAddDay() {
        System.out.println("addDay");
        int delta = 0;
        DateViewModelImpl instance = new DateViewModelImpl();
        instance.addDay(delta);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of addMonth method, of class DateViewModelImpl.
     */
    public void testAddMonth() {
        System.out.println("addMonth");
        int delta = 0;
        DateViewModelImpl instance = new DateViewModelImpl();
        instance.addMonth(delta);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of addYear method, of class DateViewModelImpl.
     */
    public void testAddYear() {
        System.out.println("addYear");
        int delta = 0;
        DateViewModelImpl instance = new DateViewModelImpl();
        instance.addYear(delta);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getDate method, of class DateViewModelImpl.
     */
    public void testGetDate() {
        System.out.println("getDate");
        DateViewModelImpl instance = new DateViewModelImpl();
        Date expResult = null;
        Date result = instance.getDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getDay method, of class DateViewModelImpl.
     */
    public void testGetDay() {
        System.out.println("getDay");
        DateViewModelImpl instance = new DateViewModelImpl();
        Calendar cal = Calendar.getInstance();
        int expResult = cal.get(Calendar.DATE);
        int result = instance.getDay();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMonth method, of class DateViewModelImpl.
     */
    public void testGetMonth() {
        System.out.println("getMonth");
        DateViewModelImpl instance = new DateViewModelImpl();
        Calendar cal = Calendar.getInstance();
        int expResult = cal.get(Calendar.MONTH);
        int result = instance.getMonth();
        assertEquals(expResult, result);
    }

    /**
     * Test of getYear method, of class DateViewModelImpl.
     */
    public void testGetYear() {
        System.out.println("getYear");
        DateViewModelImpl instance = new DateViewModelImpl();
        Calendar cal = Calendar.getInstance();
        int expResult = cal.get(Calendar.YEAR);
        int result = instance.getYear();
        assertEquals(expResult, result);
    }

    /**
     * Test of isSelected method, of class DateViewModelImpl.
     */
    public void testIsSelected() {
        System.out.println("isSelected");
        DateViewModelImpl instance = new DateViewModelImpl();
        boolean expResult = false;
        boolean result = instance.isSelected();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDate method, of class DateViewModelImpl.
     */
    public void testSetDate_Date() {
        System.out.println("setDate");
        Date date = null;
        DateViewModelImpl instance = new DateViewModelImpl();
        instance.setDate(date);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setDate method, of class DateViewModelImpl.
     */
    public void testSetDate_3args() {
        System.out.println("setDate");
        int year = 0;
        int month = 0;
        int day = 0;
        DateViewModelImpl instance = new DateViewModelImpl();
        instance.setDate(year, month, day);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setDay method, of class DateViewModelImpl.
     */
    public void testSetDay() {
        System.out.println("setDay");
        int day = 0;
        DateViewModelImpl instance = new DateViewModelImpl();
        instance.setDay(day);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setMonth method, of class DateViewModelImpl.
     */
    public void testSetMonth() {
        System.out.println("setMonth");
        int delta = 0;
        DateViewModelImpl instance = new DateViewModelImpl();
        instance.setMonth(delta);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setSelected method, of class DateViewModelImpl.
     */
    public void testSetSelected() {
        System.out.println("setSelected");
        boolean selected = false;
        DateViewModelImpl instance = new DateViewModelImpl();
        instance.setSelected(selected);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setYear method, of class DateViewModelImpl.
     */
    public void testSetYear() {
        System.out.println("setYear");
        int year = 0;
        DateViewModelImpl instance = new DateViewModelImpl();
        instance.setYear(year);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
}
