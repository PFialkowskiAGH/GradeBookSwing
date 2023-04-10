import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

public class WindowUI extends JFrame
{
    public WindowUI (AbstractTableModel table)
    {
        setLayout(new BorderLayout());
        setSize(300, 200);
        setTitle("Gradebook");
        JTable jt = new JTable(table);
        add(jt.getTableHeader(), BorderLayout.PAGE_START);
        add(jt);
        //GradebookTableModel gradebookTableModel = new GradebookTableModel();
        //ClasTableUI table = new ClasTableUI(gradebookTableModel.data, gradebookTableModel.cols);
    }
}
