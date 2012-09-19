package br.reyabreu.dateview;

import br.reyabreu.dateview.model.DateViewModel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.JTable;

/**
 * This is the Date View controller. It listens and handles UI actions, updating
 * the view model accordingly.
 *
 * @author Reynaldo
 *
 */
class DateViewController extends MouseAdapter implements
        ActionListener {

    /**
     *
     */
    private final JDateView dateView;
    private DateViewModel model;

    /**
     * @param dateView
     */
    DateViewController(JDateView dateView) {
        this.dateView = dateView;
        this.model = dateView.getModel();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dateView.getNextButton()) {
            model.addMonth(1);
        } else if (e.getSource() == dateView.getPreviousButton()) {
            model.addMonth(-1);
        } else {
            for (int month = 0; month < dateView.getMonthPopupMenuItems().length; month++) {
                if (e.getSource() == dateView.getMonthPopupMenuItems()[month]) {
                    model.setMonth(month);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == dateView.getMonthView()) {
            dateView.getMonthPopupMenu().setLightWeightPopupEnabled(false);
            dateView.getMonthPopupMenu().show((Component) e.getSource(), e.getX(),
                    e.getY());
        } else if (e.getSource() == dateView.getCurrentDateView()) {
            Calendar today = Calendar.getInstance();
            model.setDate(today.get(Calendar.YEAR),
                    today.get(Calendar.MONTH), today.get(Calendar.DATE));
        } else if (e.getSource() == dateView.getDayTableView()) {
            JTable table = ((JTable) dateView.getDayTableView());
            int row = table.getSelectedRow();
            int col = table.getSelectedColumn();
            if (row >= 0 && row <= 5) {
                Integer day = (Integer) dateView.internalModel.getValueAt(row, col);
                model.setDay(day);
                model.setSelected(true);
                dateView.fireSelectionPerformed();
            }
        } else if (e.getSource() == dateView.getUnselectView()) {
            model.setSelected(false);
            dateView.fireSelectionPerformed();
        }
    }
}