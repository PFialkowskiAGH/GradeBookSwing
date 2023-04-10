import javax.swing.table.AbstractTableModel;

public class ClassContainerTableModel extends AbstractTableModel
{
    public String[] cols = {"Name", "NumberOfStudent", "MaxNumberOfStudent"};
    public Object[][] data = {{"Katty", "Smith", "SnowBoard", 5,false}, {"Jhon", "Doe", "Rowing",3,true},
            {"Sue", "Black", "Knitting",2, false},{"Jane", "White", "Speed ride", 20, true}};
    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
