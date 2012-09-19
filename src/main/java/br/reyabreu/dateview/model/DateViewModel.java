package br.reyabreu.dateview.model;

import java.beans.PropertyChangeListener;
import java.util.Date;
import javax.swing.event.ChangeListener;

/**
 * This is the standard interface of the model. It is assumed that implementations
 * will use Gregorian Dates.
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
	 * Getter for the current date as a Date.
	 *
	 * @return java.util.Date
	 */
	public Date getDate();

	/**
	 *
	 * @return
	 */
	public int getDay();

	/**
	 *
	 * @return
	 */
	public int getMonth();

	/**
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
	 * Setter for the current date as a java.util.Date.
	 *
	 * @param date
	 * @see #java.util.Date
	 */
	public void setDate(Date date);

	/**
	 * Sets current date as specified by supplied parameters. Parameters are
	 * expected to be consistent with java.util.Calendar.
	 *
	 * @param year
	 * @param month
	 * @param day
	 *
	 * @see #java.util.Calendar
	 */
	public void setDate(int year, int month, int day);

	/**
	 *
	 * @return
	 */
	public void setDay(int day);

	/**
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
	 *
	 * @return
	 */
	public void setYear(int year);

}
