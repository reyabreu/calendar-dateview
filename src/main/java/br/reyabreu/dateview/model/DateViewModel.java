package br.reyabreu.dateview.model;

import java.beans.PropertyChangeListener;
import java.util.Date;
import javax.swing.event.ChangeListener;

/**
 * This is the standard interface of the model. It offers a limited set of
 * operations such as date getters and setters, month addition and a selection
 * flag. It is assumed that implementations will use Gregorian Dates.
 *
 * @author Reynaldo
 *
 */
public interface DateViewModel {

    /**
     * Adds a Change Listener. Change listeners will be notified of state
     * changes in selected date.
     *
     * @param changeListener
     */
    public void addChangeListener(ChangeListener changeListener);

    /**
     * Add or subtract a number of months.
     *
     * @param add
     */
    public void addMonth(int delta);

    /**
     * Adds a property listener for all property changes in target Java bean.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener);

    /**
     * Getter for current date
     *
     * @return Date
     */
    public Date getDate();

    /**
     * Getter for current day of the month
     *
     * @return
     */
    public int getDay();

    /**
     *
     * Getter for current month
     *
     * @return
     */
    public int getMonth();

    /**
     * Getter for current year
     *
     * @return
     */
    public int getYear();

    /**
     * Informs if there is an active date selection.
     *
     * @return boolean
     */
    public boolean isSelected();

    /**
     * Removes a Change Listener. Change listeners will be notified of state
     * changes in selected date.
     *
     * @param changeListener
     */
    public void removeChangeListener(ChangeListener changeListener);

    /**
     * Removes property listener for all property changes in target java bean
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener);

    /**
     * Setter for current date
     *
     * @param date
     * @see Date
     */
    public void setDate(Date date);

    /**
     * Sets current date as specified by provided year, month and day.
     * Parameters are expected to be consistent with Calendar rules.
     *
     * @param year
     * @param month
     * @param day
     *
     * @see Calendar
     */
    public void setDate(int year, int month, int day);

    /**
     * Setter for current day of the month
     *
     * @return
     */
    public void setDay(int day);

    /**
     * Setter for current month
     *
     * @return
     */
    public void setMonth(int month);

    /**
     * Makes current date selection active or inactive.
     *
     * @param selected
     */
    public void setSelected(boolean selected);

    /**
     * Setter for current year
     *
     * @return
     */
    public void setYear(int year);
}
