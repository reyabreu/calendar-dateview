package br.reyabreu.dateview;

import br.reyabreu.dateview.model.DateViewModel;
import br.reyabreu.dateview.model.DateViewModelImpl;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import javax.swing.*;
import javax.swing.border.Border;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a simple demo application for the DateView component, in the shape of
 * a JFrame. A date selection (or clear action) on the DateView affects the
 * contents of the text field.
 *
 * @author Reynaldo
 */
public class DateViewDemoApp extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(DateViewDemoApp.class);
    private ActionListener al;
    private DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);

    /**
     * This is the event handler for DateView actions. It simply updates a text
     * field.
     *
     * @author Reynaldo
     */
    class MainFrameListener implements ActionListener {

        //handle actions for our frame
        public void actionPerformed(ActionEvent e) {
            logger.info("DateView action performed");
            JDateView view = (JDateView) e.getSource();
            DateViewModel model = view.getModel();
            //if a selection is made, show the date. Clear text otherwise
            if (model.isSelected()) {
                dateTextField.setText(df.format(model.getDate()));
            } else {
                dateTextField.setText("");
            }
        }
    }
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JTextField dateTextField;

    /**
     * The Demo Application class constructor. It layouts the components,
     * including the DateView
     *
     * @throws HeadlessException
     */
    public DateViewDemoApp() throws HeadlessException {

        super("DateView Demo App frame");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        dateTextField = new JTextField();

        DateViewModelImpl model = new DateViewModelImpl();
        JDateView dateView = new JDateView(model);
        Border titleBorder = BorderFactory.createTitledBorder("Click on the month to change.");
        dateView.setBorder(titleBorder);

        Container contents = this.getContentPane();
        contents.add(dateTextField, BorderLayout.NORTH);
        contents.add(dateView, BorderLayout.CENTER);

        al = new MainFrameListener();
        dateView.addActionListener(al);

    }

    /**
     * This is the main method for our Demo application
     *
     * @param args
     */
    public static void main(String[] args) {
        logger.info("DateView Demo App started.");

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    logger.info("Nimbus look and feel set");
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            logger.error("ClassNotFoundException", ex);
        } catch (InstantiationException ex) {
            logger.error("InstantiationException", ex);
        } catch (IllegalAccessException ex) {
            logger.error("IllegalAccessException", ex);
        } catch (UnsupportedLookAndFeelException ex) {
            logger.error("UnsupportedLookAndFeelException", ex);
        }

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                JFrame frame = new DateViewDemoApp();
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
            }
        });
    }
}
