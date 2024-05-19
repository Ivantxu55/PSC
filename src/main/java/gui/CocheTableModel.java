package gui;

import domain.jdo.Coche;

import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
/**
 * Clase que representa un modelo de tabla para los coches
 */
public class CocheTableModel extends DefaultTableModel {

    private final List<String> headers = Arrays.asList("Marca", "Año", "Color", "Kms", "Precio", "Estado");
    private List<Coche> coches;

    public CocheTableModel(List<Coche> coches) {
        this.coches = coches;
    }

    public int getRowCount() {
        return coches == null ? 0 : coches.size();
    }

    @Override
    public int getColumnCount() {
        return headers.size();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
    }

    @Override
    public String getColumnName(int column) {
        return headers.get(column);
    }

    /**
     * Devuelve el valor de la celda en la fila y columna especificadas.
     * @param row             the row whose value is to be queried
     * @param column          the column whose value is to be queried
     * @return
     */
    public Object getValueAt(int row, int column) {
        Coche coche = coches.get(row);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        switch (column) {
            case 0:
                return coche.getMarca().toString();
            case 1:
                return coche.getAnyo();
            case 2:
                return coche.getColor();
            case 3:
                return coche.getKilometraje();
            case 4:
                return coche.getPrecio();
            case 5:
                if(coche.isNuevo()){
                    return "Nuevo";
                }else{
                    return "Usado";
                }
            default:
                return null;
        }
    }

    public Coche getCocheAt(int row) {
        return coches.get(row);
    }

    public void setCoches(List<Coche> coches) {
        this.coches = coches;
        fireTableDataChanged();
    }
}


