package br.reyabreu.dateview.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This is the default model implementation for our Date View.
 *
 * @author rizzor
 *
 */
public class DateViewModelImpl implements DateViewModel {

	private boolean selected;

	private Calendar calendar;

	private HashSet<ChangeListener> changeListeners;

	private HashSet<PropertyChangeListener> propertyChangeListeners;

	public DateViewModelImpl() {
		this(null);
	}

	public DateViewModelImpl(Date date) {
		this.calendar = Calendar.getInstance();
		this.changeListeners = new HashSet<ChangeListener>();
		this.propertyChangeListeners = new HashSet<PropertyChangeListener>();
		this.selected = date != null;
		if (this.selected) {
			this.calendar = asCalendar(date);
			setDefaultTime();
		}
	}

	public synchronized void addChangeListener(ChangeListener changeListener) {
		changeListeners.add(changeListener);
	}

	public void addDay(int delta) {
		int oldDayValue = this.calendar.get(Calendar.DATE);
		Date oldDate = getDate();
		calendar.add(Calendar.DATE, delta);
		fireChangeEvent();
		firePropertyChange("day", oldDayValue, this.calendar.get(Calendar.DATE));
		firePropertyChange("date", oldDate, getDate());
	}

	public void addMonth(int delta) {
		int oldMonth = this.calendar.get(Calendar.MONTH);
		Date oldDate = getDate();
		calendar.add(Calendar.MONTH, delta);
		fireChangeEvent();
		firePropertyChange("month", oldMonth, this.calendar.get(Calendar.MONTH));
		firePropertyChange("date", oldDate, getDate());
	}

	public synchronized void addPropertyChangeListener(
			PropertyChangeListener listener) {
		propertyChangeListeners.add(listener);
	}

	public void addYear(int delta) {
		int oldYear = this.calendar.get(Calendar.YEAR);
		Date oldDate = getDate();
		calendar.add(Calendar.YEAR, delta);
		fireChangeEvent();
		firePropertyChange("year", oldYear, this.calendar.get(Calendar.YEAR));
		firePropertyChange("date", oldDate, getDate());
	}

	private Calendar asCalendar(Date date) {
		Calendar result = Calendar.getInstance();
		result.setTime(date);
		return result;
	}

	private Date asDate(Calendar cal) {
		return new Date(cal.getTimeInMillis());
	}

	protected synchronized void fireChangeEvent() {
		for (ChangeListener changeListener : changeListeners) {
			changeListener.stateChanged(new ChangeEvent(this));
		}
	}

	protected synchronized void firePropertyChange(String propertyName,
			Object oldValue, Object newValue) {
		if (oldValue != null && newValue != null && oldValue.equals(newValue)) {
			return;
		}

		for (PropertyChangeListener listener : propertyChangeListeners) {
			listener.propertyChange(new PropertyChangeEvent(this, propertyName,
					oldValue, newValue));
		}
	}

	public Date getDate() {
		if (!selected) {
			return null;
		}
		Date value = asDate(calendar);
		return value;
	}

	public int getDay() {
		return calendar.get(Calendar.DATE);
	}

	public int getMonth() {
		return calendar.get(Calendar.MONTH);
	}

	public int getYear() {
		return calendar.get(Calendar.YEAR);
	}

	public boolean isSelected() {
		return selected;
	}

	public synchronized void removeChangeListener(ChangeListener changeListener) {
		changeListeners.remove(changeListener);
	}

	public synchronized void removePropertyChangeListener(
			PropertyChangeListener listener) {
		propertyChangeListeners.remove(listener);
	}

	public void setDate(Date date) {
		int oldYear = this.calendar.get(Calendar.YEAR);
		int oldMonth = this.calendar.get(Calendar.MONTH);
		int oldDay = this.calendar.get(Calendar.DATE);
		Date oldDate = getDate();
		boolean oldSelectedValue = isSelected();

		this.selected = date != null;
		if (this.selected) {
			this.calendar = asCalendar(date);
			setDefaultTime();
		}

		fireChangeEvent();
		firePropertyChange("year", oldYear, this.calendar.get(Calendar.YEAR));
		firePropertyChange("month", oldMonth, this.calendar.get(Calendar.MONTH));
		firePropertyChange("day", oldDay, this.calendar.get(Calendar.DATE));
		firePropertyChange("date", oldDate, getDate());
		firePropertyChange("selected", oldSelectedValue, this.selected);
	}

	public void setDate(int year, int month, int day) {
		int oldYear = this.calendar.get(Calendar.YEAR);
		int oldMonth = this.calendar.get(Calendar.MONTH);
		int oldDay = this.calendar.get(Calendar.DATE);
		Date oldDate = getDate();

		calendar.set(year, month, day);

		fireChangeEvent();
		firePropertyChange("year", oldYear, this.calendar.get(Calendar.YEAR));
		firePropertyChange("month", oldMonth, this.calendar.get(Calendar.MONTH));
		firePropertyChange("day", oldDay, this.calendar.get(Calendar.DATE));
		firePropertyChange("date", oldDate, getDate());
	}

	public void setDay(int day) {
		int oldDayValue = this.calendar.get(Calendar.DATE);
		Date oldDate = getDate();
		calendar.set(Calendar.DATE, day);
		fireChangeEvent();
		firePropertyChange("day", oldDayValue, this.calendar.get(Calendar.DATE));
		firePropertyChange("date", oldDate, getDate());
	}

	/**
	 * The date returned by our model should have standard time
	 */
	private void setDefaultTime() {
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}

	public void setMonth(int delta) {
		int oldMonth = this.calendar.get(Calendar.MONTH);
		Date oldDate = getDate();
		calendar.set(Calendar.MONTH, delta);
		fireChangeEvent();
		firePropertyChange("month", oldMonth, this.calendar.get(Calendar.MONTH));
		firePropertyChange("date", oldDate, getDate());
	}

	public void setSelected(boolean selected) {
		Date oldDate = getDate();
		boolean oldSelectedValue = isSelected();
		this.selected = selected;
		fireChangeEvent();
		firePropertyChange("date", oldDate, getDate());
		firePropertyChange("selected", oldSelectedValue, this.selected);
	}

	public void setYear(int year) {
		int oldYear = this.calendar.get(Calendar.YEAR);
		Date oldDate = getDate();
		calendar.set(Calendar.YEAR, year);
		fireChangeEvent();
		firePropertyChange("year", oldYear, this.calendar.get(Calendar.YEAR));
		firePropertyChange("date", oldDate, getDate());
	}

}