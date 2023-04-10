import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClassContainerTableModel extends AbstractTableModel
{
    public static final String[] columnNames = {"Name", "NumberOfStudents", "MaxNumberOfStudents"};
    public List<ClassOfStudent> data;

    public ClassContainerTableModel(Map<?, ClassOfStudent> data)
    {
        this.data = new ArrayList<ClassOfStudent>(data.values());
    }
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex)
        {
            case 0:
                return data.get(rowIndex).className;
            case 1:
                return data.get(rowIndex).students.size();
            case 2:
                return data.get(rowIndex).maxNumberOfStudents;
        }
        throw new IllegalStateException("Unhandled column index: " + columnIndex);
    }
    public String getColumnName(int col) {
        return columnNames[col];
    }
}
