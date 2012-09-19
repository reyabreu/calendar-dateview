package br.reyabreu.dateview;

import br.reyabreu.dateview.model.DateViewModel;
import br.reyabreu.dateview.model.DateViewModelImpl;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import javax.swing.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateViewDemoApp extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(DateViewDemoApp.class);

    private ActionListener al;
    private DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);

    class MainFrameListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JDateView view = (JDateView) e.getSource();
            DateViewModel model = view.getModel();
            if (model.isSelected()) {
                text.setText(df.format(model.getDate()));
            } else {
                text.setText("");
            }
        }
    }
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JTextField text;

    public DateViewDemoApp() throws HeadlessException {

        super("tester");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container contents = this.getContentPane();

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        text = new JTextField();
        DateViewModelImpl model = new DateViewModelImpl();
        JDateView view = new JDateView(model);
        contents.add(text, "North");
        contents.add(view, "Center");

        al = new MainFrameListener();
        view.addActionListener(al);

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        logger.info("Hello World");
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
