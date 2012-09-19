package br.reyabreu.dateview;

import br.reyabreu.dateview.model.DateViewModel;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.HashSet;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


/**
 * This model is a facade for the JTable, JSpinner and DateViewModel
 * allowing concentrated handling
 *
 * @author Reynaldo
 *
 */
class DateViewModelHelper implements TableModel, ChangeListener,
		SpinnerModel {

	/**
	 *
	 */
	private final JDateView view;

	private static final int DAYS_PER_WEEK = 7;

	private static final int MAX_WEEKS_PER_MONTH = 5;

	private Calendar calendar;

	private DateViewModel model;

	private HashSet<TableModelListener> tableModelListeners;

	private HashSet<ChangeListener> spinnerModelListeners;

	public DateViewModelHelper(JDateView dateView, DateViewModel model) {
		view = dateView;
		this.calendar = Calendar.getInstance();
		this.tableModelListeners = new HashSet<TableModelListener>();
		this.spinnerModelListeners = new HashSet<ChangeListener>();
		this.model = model;
		model.addChangeListener(this);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.SpinnerModel#addChangeListener(javax.swing.event.
	 * ChangeListener)
	 */
	public void addChangeListener(ChangeListener l) {
		spinnerModelListeners.add(l);
	}

	public void addTableModelListener(TableModelListener l) {
		tableModelListeners.add(l);
	}

	private void fireValueChanged() {
		for (ChangeListener l : spinnerModelListeners) {
			l.stateChanged(new ChangeEvent(this));
		}
		view.updateHeaderLabel();
		for (TableModelListener l : tableModelListeners) {
			l.tableChanged(new TableModelEvent(this));
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.table.TableModel#getColumnClass(int)
	 */
	public Class<?> getColumnClass(int columnIndex) {
		return Integer.class;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return DAYS_PER_WEEK;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.table.TableModel#getColumnName(int)
	 */
	public String getColumnName(int columnIndex) {
		DateFormatSymbols dateFmtSymbols = new DateFormatSymbols();
		String[] days = dateFmtSymbols.getShortWeekdays();
		return days[columnIndex + 1];
	}

	public DateViewModel getModel() {
		return model;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.SpinnerModel#getNextValue()
	 */
	public Object getNextValue() {
		return Integer.toString(model.getYear() + 1);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.SpinnerModel#getPreviousValue()
	 */
	public Object getPreviousValue() {
		return Integer.toString(model.getYear() - 1);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return MAX_WEEKS_PER_MONTH + 1;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.SpinnerModel#getValue()
	 */
	public Object getValue() {
		return Integer.toString(model.getYear());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		// sets calendar to first day of the month
		calendar.set(model.getYear(), model.getMonth(), 1);

		int weekDay = calendar.get(Calendar.DAY_OF_WEEK);

		// calculate offset to find value
		int value = (columnIndex + 1 - weekDay)
				+ (rowIndex * DAYS_PER_WEEK + 1);

		return new Integer(value);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.table.TableModel#isCellEditable(int, int)
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.SpinnerModel#removeChangeListener(javax.swing.event.
	 * ChangeListener)
	 */
	public void removeChangeListener(ChangeListener l) {
		spinnerModelListeners.remove(l);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * javax.swing.table.TableModel#removeTableModelListener(javax.swing
	 * .event.TableModelListener)
	 */
	public void removeTableModelListener(TableModelListener l) {
		tableModelListeners.remove(l);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.SpinnerModel#setValue(java.lang.Object)
	 */
	public void setValue(Object value) {
		model.setYear(new Integer((String) value));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.table.TableModel#setValueAt(java.lang.Object, int,
	 * int)
	 */
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// nothing to do here, setting values not allowed
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.
	 * ChangeEvent)
	 */
	public void stateChanged(ChangeEvent e) {
		fireValueChanged();
	}

}