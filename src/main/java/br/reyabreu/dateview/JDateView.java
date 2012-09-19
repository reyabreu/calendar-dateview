package br.reyabreu.dateview;

import br.reyabreu.dateview.model.DateViewModel;
import br.reyabreu.dateview.model.DateViewModelImpl;
import br.reyabreu.dateview.ui.JNavigationButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 * This is the actual Date View Component. It follows an MVC pattern, separating
 * the model that consolidates the different internal component models (table,
 * spinner) from the actual Date Model. It also allows for other controller.
 *
 * @author Reynaldo
 *
 */
public class JDateView extends JPanel {

    protected class InternalTableCellRenderer extends DefaultTableCellRenderer {

        private static final int WEEKDAY_ROW = -1;
        /**
         *
         */
        private static final long serialVersionUID = 6796187709655566353L;

        /**
         * @version 1.0
         */
        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table,
                    value, isSelected, hasFocus, row, column);
            label.setHorizontalAlignment(JLabel.CENTER);

            // paint weekdays
            if (row == WEEKDAY_ROW) {
                label.setForeground(SystemColor.textHighlightText);
                label.setBackground(SystemColor.textHighlight);
                label.setHorizontalAlignment(JLabel.CENTER);
                return label;
            }

            // both get today's date
            Calendar todayCal = Calendar.getInstance();
            Calendar selectedDateCal = Calendar.getInstance();

            // set Calendar instance with model's selected date
            DateViewModel model = internalModel.getModel();
            selectedDateCal.set(model.getYear(), model.getMonth(),
                    model.getDay());

            int cellDayValue = (Integer) value;

            // the selected day matches current cell day
            boolean isCurrentDate = selectedDateCal.get(Calendar.DATE) == cellDayValue;

            // get last day of current month
            int lastDayOfMonth = selectedDateCal.getActualMaximum(Calendar.DAY_OF_MONTH);

            // day is not in current month range
            if (cellDayValue < 1 || cellDayValue > lastDayOfMonth) {
                label.setForeground(SystemColor.textInactiveText);
                label.setBackground(SystemColor.info);
                label.setFont(table.getFont().deriveFont(Font.ITALIC));

                // day is beyond end of month
                if (cellDayValue > lastDayOfMonth) {
                    label.setText(Integer.toString(cellDayValue
                            - lastDayOfMonth));
                } // day is before start of month
                else {
                    Calendar lastMonth = Calendar.getInstance();
                    lastMonth.set(selectedDateCal.get(Calendar.YEAR),
                            selectedDateCal.get(Calendar.MONTH) - 1, 1);
                    int lastDayLastMonth = lastMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
                    label.setText(Integer.toString(lastDayLastMonth
                            + cellDayValue));
                }
            } // Current month days
            else {
                label.setForeground(SystemColor.infoText);
                label.setBackground(SystemColor.info);

                // Display Today with a different color scheme
                if (todayCal.get(Calendar.DATE) == cellDayValue
                        && todayCal.get(Calendar.MONTH) == model.getMonth()
                        && todayCal.get(Calendar.YEAR) == model.getYear()) {
                    label.setFont(table.getFont().deriveFont(Font.BOLD));

                    // today is selected
                    if (model.isSelected() && isCurrentDate) {
                        label.setForeground(SystemColor.textHighlightText);
                        label.setBackground(SystemColor.textHighlight);
                    }
                } else {
                    // selected dates
                    if (model.isSelected() && isCurrentDate) {
                        label.setForeground(SystemColor.textHighlightText);
                        label.setBackground(SystemColor.textHighlight);
                    }
                }
            }

            return label;
        }
    }
    /**
     *
     */
    private static final long serialVersionUID = -8078428176664867059L;
    private HashSet<ActionListener> actListeners;
    private JPanel centerPanel;
    private JPanel centralHeaderPanel;
    private JPanel headerPanel;
    private JPanel statusBarPanel;
    private JTable dayTable;
    private JTableHeader dayTableHeader;
    private InternalTableCellRenderer dayTableCellRenderer;
    private DateViewController internalController;
    private JLabel monthLabel;
    private JLabel currentDateLabel;
    private JLabel unselectLabel;
    private JPopupMenu monthPopupMenu;
    private JMenuItem[] monthPopupMenuItems;
    private JButton nextButton;
    private JButton previousButton;
    private JSpinner yearSpinner;
    private Properties uiStrings;

    DateViewModelHelper internalModel;

    public JDateView(DateViewModel model) {
        // load this first
        uiStrings = new Properties(getDefaultStrings());

        actListeners = new HashSet<ActionListener>();
        internalModel = new DateViewModelHelper(this, (model != null) ? model
                : createDefaultModel());
        internalController = new DateViewController(this);
        buildCalendarView();
    }

    public void addActionListener(ActionListener actListener) {
        actListeners.add(actListener);
    }

    private void buildCalendarView() {
        this.setLayout(new BorderLayout());
        this.setSize(640, 240);
        this.setPreferredSize(new Dimension(640, 240));
        this.setBackground(SystemColor.activeCaptionText);
        this.setOpaque(false);
        this.add(getHeaderView(), BorderLayout.NORTH);
        this.add(getCenterView(), BorderLayout.CENTER);
        this.add(getStatusBarView(), BorderLayout.SOUTH);
    }

    private DateViewModel createDefaultModel() {
        return new DateViewModelImpl();
    }

    void fireSelectionPerformed() {
        for (ActionListener actionListener : actListeners) {
            actionListener.actionPerformed(new ActionEvent(this,
                    ActionEvent.ACTION_PERFORMED, "selection"));
        }
    }

    Component getCenterView() {
        if (centerPanel == null) {
            centerPanel = new JPanel();
            centerPanel.setLayout(new BorderLayout());
            centerPanel.setOpaque(false);
            centerPanel.add(getDayTableViewHeader(), BorderLayout.NORTH);
            centerPanel.add(getDayTableView(), BorderLayout.CENTER);
        }
        return centerPanel;
    }

    Component getCentralHeaderView() {
        if (centralHeaderPanel == null) {
            centralHeaderPanel = new JPanel();
            centralHeaderPanel.setLayout(new BorderLayout());
            centralHeaderPanel.setBorder(BorderFactory.createEmptyBorder(0, 5,
                    0, 5));
            centralHeaderPanel.setOpaque(false);
            centralHeaderPanel.add(getMonthView(), BorderLayout.CENTER);
            centralHeaderPanel.add(getYearView(), java.awt.BorderLayout.EAST);
        }
        return centralHeaderPanel;
    }

    Component getCurrentDateView() {
        if (currentDateLabel == null) {
            currentDateLabel = new JLabel();
            currentDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
            currentDateLabel.addMouseListener(internalController);

            DateFormat df = SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM);
            currentDateLabel.setText(uiStrings.getProperty("dateview.today")
                    + ": " + df.format(new Date()));

        }
        return currentDateLabel;
    }

    private InternalTableCellRenderer getDayTableCellRenderer() {
        if (dayTableCellRenderer == null) {
            dayTableCellRenderer = new InternalTableCellRenderer();
        }
        return dayTableCellRenderer;
    }

    Component getDayTableView() {
        if (dayTable == null) {
            dayTable = new JTable();
            dayTable.setModel(internalModel);
            dayTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            dayTable.setRowHeight(26);
            // dayTable.setOpaque(false);
            dayTable.setPreferredSize(new Dimension(100, 80));
            dayTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            dayTable.setCellSelectionEnabled(true);
            dayTable.setRowSelectionAllowed(true);
            dayTable.setShowGrid(true);
            dayTable.setGridColor(SystemColor.textHighlightText);
            dayTable.setFocusable(false);
            dayTable.addMouseListener(internalController);
            setupDateTableViewColumns();
        }
        return dayTable;
    }

    Component getDayTableViewHeader() {
        if (dayTableHeader == null) {
            dayTableHeader = ((JTable) getDayTableView()).getTableHeader();
            // no reordering or resizing. Will mix up the days!
            dayTableHeader.setResizingAllowed(false);
            dayTableHeader.setReorderingAllowed(false);
            dayTableHeader.setDefaultRenderer(getDayTableCellRenderer());
        }
        return dayTableHeader;
    }

    private Properties getDefaultStrings() {
        Properties defaults = new Properties();
        defaults.put("dateview.today", "Today");
        defaults.put("dateview.nextMonth", "Next month");
        defaults.put("dateview.previousMonth", "Previous month");
        defaults.put("dateview.setYear", "Choose Year");
        defaults.put("dateview.clear", "Clear");

        return defaults;
    }

    Component getHeaderView() {
        if (headerPanel == null) {
            headerPanel = new JPanel();
            headerPanel.setLayout(new BorderLayout());
            headerPanel.setName("");
            headerPanel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
            headerPanel.add(getPreviousButton(), java.awt.BorderLayout.WEST);
            headerPanel.add(getCentralHeaderView(),
                    java.awt.BorderLayout.CENTER);
            headerPanel.add(getNextButton(), java.awt.BorderLayout.EAST);
        }
        return headerPanel;
    }

    public DateViewModel getModel() {
        return internalModel.getModel();
    }

    JPopupMenu getMonthPopupMenu() {
        if (monthPopupMenu == null) {
            monthPopupMenu = new JPopupMenu();
            JMenuItem[] menuItems = getMonthPopupMenuItems();
            for (int i = 0; i < menuItems.length; i++) {
                monthPopupMenu.add(menuItems[i]);
            }
        }
        return monthPopupMenu;
    }

    JMenuItem[] getMonthPopupMenuItems() {
        if (monthPopupMenuItems == null) {
            DateFormatSymbols df = new DateFormatSymbols();
            String[] months = df.getMonths();
            monthPopupMenuItems = new JMenuItem[months.length - 1];
            for (int i = 0; i < months.length - 1; i++) {
                JMenuItem mi = new JMenuItem(months[i]);
                mi.addActionListener(internalController);
                monthPopupMenuItems[i] = mi;
            }
        }
        return monthPopupMenuItems;
    }

    Component getMonthView() {
        if (monthLabel == null) {
            monthLabel = new JLabel();
            monthLabel.setForeground(SystemColor.textText);
            monthLabel.setHorizontalAlignment(SwingConstants.CENTER);
            monthLabel.addMouseListener(internalController);
            updateHeaderLabel();
        }
        return monthLabel;
    }

    JButton getNextButton() {
        if (nextButton == null) {
            nextButton = new JNavigationButton(4, 7, SwingConstants.RIGHT);
            nextButton.setPreferredSize(new Dimension(20, 15));
            nextButton.addActionListener(internalController);
            nextButton.setToolTipText(uiStrings.getProperty("dateview.nextMonth"));
        }
        return nextButton;
    }

    JButton getPreviousButton() {
        if (previousButton == null) {
            previousButton = new JNavigationButton(4, 7, SwingConstants.LEFT);
            previousButton.setPreferredSize(new Dimension(20, 15));
            previousButton.addActionListener(internalController);
            previousButton.setToolTipText(uiStrings.getProperty("dateview.previousMonth"));
        }
        return previousButton;
    }

    Component getStatusBarView() {
        if (statusBarPanel == null) {
            statusBarPanel = new JPanel();
            statusBarPanel.setLayout(new BorderLayout());
            statusBarPanel.setOpaque(false);
            statusBarPanel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
            statusBarPanel.add(getCurrentDateView(), BorderLayout.WEST);
            statusBarPanel.add(getUnselectView(), BorderLayout.EAST);
        }
        return statusBarPanel;
    }

    Component getUnselectView() {
        if (unselectLabel == null) {
            unselectLabel = new JLabel();
            unselectLabel.setHorizontalAlignment(SwingConstants.CENTER);
            unselectLabel.addMouseListener(internalController);
            unselectLabel.setText(uiStrings.getProperty("dateview.clear"));
        }
        return unselectLabel;
    }

    Component getYearView() {
        if (yearSpinner == null) {
            yearSpinner = new JSpinner();
            yearSpinner.setModel(internalModel);
            yearSpinner.setToolTipText(uiStrings.getProperty("dateview.setYear"));

        }
        return yearSpinner;
    }

    public void removeActionListener(ActionListener actListener) {
        actListeners.remove(actListener);
    }

    private void setupDateTableViewColumns() {
        TableColumn column;
        for (int i = 0; i < 7; i++) {
            column = dayTable.getColumnModel().getColumn(i);
            column.setPreferredWidth(15);
            column.setCellRenderer(getDayTableCellRenderer());
        }
    }

    void updateHeaderLabel() {
        int month = getModel().getMonth();
        // get symbols for current locale
        DateFormatSymbols df = new DateFormatSymbols();
        monthLabel.setText(df.getMonths()[month]);
    }
}
