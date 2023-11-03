package app;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MySpinnerEditor extends DefaultCellEditor {
        JSpinner sp;
        DefaultEditor defaultEditor;
        JTextField text;
        
        // Initialize the spinner
        public MySpinnerEditor() {
            super(new JTextField());
            sp = new JSpinner();
            defaultEditor = ((DefaultEditor)sp.getEditor());
            text = defaultEditor.getTextField();
        }
        // Prepare the spinner component and return it
        public Component getTableCellEditorComponent(JTable table, Object 
        value, boolean isSelected, int row, int column) 
        {
            sp.setValue(value);
            return sp;
        }
        // Returns the current value of the spinners
        public Object getCellEditorValue() {
            return sp.getValue();
        }
}