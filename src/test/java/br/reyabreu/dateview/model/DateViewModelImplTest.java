package br.reyabreu.dateview.model;

import java.util.Calendar;
import java.util.Date;
import junit.framework.TestCase;

/**
 * Tests the DateViewModel implementation, as it holds the DateView logic. For
 * simplicity, getters and setters will be tested together.
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

        DateViewModelImpl instance = new DateViewModelImpl();

        //get ourseleves a calendar
        Calendar cal = Calendar.getInstance();
        //calendar and instance have the same date
        instance.setSelected(true);
        cal.setTime(instance.getDate());

        //add same delta to both
        int delta = (int) (Math.random() * 30) + 1;
        instance.addDay(delta);
        cal.add(Calendar.DATE, delta);
        assertEquals(cal.getTime(), instance.getDate());

        //test negatives too
        delta *= -1;
        instance.addDay(delta);
        cal.add(Calendar.DATE, delta);
        assertEquals(cal.getTime(), instance.getDate());
    }

    /**
     * Test of addMonth method, of class DateViewModelImpl.
     */
    public void testAddMonth() {
        System.out.println("addMonth");

        DateViewModelImpl instance = new DateViewModelImpl();

        //get ourseleves a calendar
        Calendar cal = Calendar.getInstance();
        //calendar and instance have the same date
        instance.setSelected(true);
        cal.setTime(instance.getDate());

        int delta = (int) (Math.random() * 12) + 1;
        //add same delta to both
        instance.addMonth(delta);
        cal.add(Calendar.MONTH, delta);
        assertEquals(cal.getTime(), instance.getDate());

        //test negatives too
        delta *= -1;
        instance.addMonth(delta);
        cal.add(Calendar.MONTH, delta);
        assertEquals(cal.getTime(), instance.getDate());
    }

    /**
     * Test of addYear method, of class DateViewModelImpl.
     */
    public void testAddYear() {
        System.out.println("addYear");
        DateViewModelImpl instance = new DateViewModelImpl();

        //get ourseleves a calendar
        Calendar cal = Calendar.getInstance();

        //calendar and instance have the same date
        instance.setSelected(true);
        cal.setTime(instance.getDate());

        int delta = (int) (Math.random() * 10) + 1;
        //add same delta to both
        instance.addYear(delta);
        cal.add(Calendar.YEAR, delta);
        assertEquals(cal.getTime(), instance.getDate());

        //test negatives too
        delta *= -1;
        instance.addYear(delta);
        cal.add(Calendar.YEAR, delta);
        assertEquals(cal.getTime(), instance.getDate());
    }

    /**
     * Test of getDate and setDate methods, of class DateViewModelImpl.
     */
    public void testGetSetDate() {
        System.out.println("getDate & setDate");
        DateViewModelImpl instance = new DateViewModelImpl();

        Calendar cal = Calendar.getInstance();

        //set date on calendar instance at midnight
        cal.set(1974, 1, 4, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        //get the date from the calendar instance
        Date expResult = cal.getTime();

        instance.setDate(expResult);
        instance.setSelected(true);
        Date result = instance.getDate();
        assertEquals("getDate & setDate", expResult, result);

        instance.setSelected(false);
        result = instance.getDate();
        assertNull("getDate & setDate", result);
    }

    /**
     * Test of getDay and setDay methods, of class DateViewModelImpl.
     */
    public void testGetSetDay() {
        System.out.println("getDay & setDay");
        DateViewModelImpl instance = new DateViewModelImpl();

        //get the calendar instance
        Calendar cal = Calendar.getInstance();

        //set date on calendar instance
        cal.set(Calendar.DATE, (int) (Math.random() * 20) + 1);
        int expResult = cal.get(Calendar.DATE);

        instance.setDay(expResult);
        int result = instance.getDay();
        assertEquals("getDay & setDay", expResult, result);
    }

    /**
     * Test of getMonth and setMonth methods, of class DateViewModelImpl.
     */
    public void testGetSetMonth() {
        System.out.println("getMonth & setMonth");
        DateViewModelImpl instance = new DateViewModelImpl();

        //get the calendar instance
        Calendar cal = Calendar.getInstance();

        //set date on calendar instance
        cal.set(Calendar.MONTH, (int) (Math.random() * 12) + 1);
        int expResult = cal.get(Calendar.MONTH);

        instance.setMonth(expResult);
        int result = instance.getMonth();
        assertEquals("getMonth & setMonth", expResult, result);
    }

    /**
     * Test of getYear and setYear methods, of class DateViewModelImpl.
     */
    public void testGetSetYear() {
        System.out.println("getYear & setYear");
        DateViewModelImpl instance = new DateViewModelImpl();

        //get the calendar instance
        Calendar cal = Calendar.getInstance();

        //set date on calendar instance
        cal.set(Calendar.YEAR, (int) (Math.random() * 100) + 1912);
        int expResult = cal.get(Calendar.YEAR);

        instance.setYear(expResult);
        int result = instance.getYear();

        assertEquals("getYear & setYear", expResult, result);
    }

    /**
     * Test of isSelected and setSelected methods, of class DateViewModelImpl.
     */
    public void testSetIsSelected() {
        System.out.println("isSelected & setSelected");
        DateViewModelImpl instance = new DateViewModelImpl();

        boolean expResult = false;
        boolean result = instance.isSelected();
        assertEquals("isSelected & setSelected", expResult, result);

        expResult = true;
        instance.setSelected(true);
        result = instance.isSelected();
        assertEquals("isSelected & setSelected", expResult, result);
    }

    /**
     * Test of setDate method with 3 arguments, of class DateViewModelImpl.
     */
    public void testSetDate_3args() {
        System.out.println("setDate3args");
        Calendar cal = Calendar.getInstance();

        //set date on calendar instance at midnight
        cal.set(1974, 1, 4);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);

        DateViewModelImpl instance = new DateViewModelImpl();
        instance.setDate(year, month, day);

        Date expResult = cal.getTime();
        instance.setSelected(true);
        Date result = instance.getDate();
        assertEquals("setDate3args", expResult, result);

        instance.setSelected(false);
        result = instance.getDate();
        assertNull("setDate3args", result);

    }
}
