package com.limagiran.exceltovcard.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

/**
 *
 * @author Vinicius Silva
 */
public class Excel {

    private static Workbook wb;
    private static Sheet s;

    /**
     * Retorna o conteúdo do arquivo excel
     *
     * @param file local do arquivo
     * @return Lista com o conteúdo das linhas e colunas do arquivo do excel
     * @throws IOException erro ao abrir arquivo
     * @throws BiffException arquivo ilegível
     */
    public static List<List<String>> getData(File file)
            throws IOException, BiffException {
        List<List<String>> _return = new ArrayList<>();
        try {
            open(file);
            for (int row = 0; row < s.getRows(); row++) {
                List<String> line = new ArrayList<>();
                for (int col = 0; col < s.getColumns(); col++) {
                    line.add(getCell(row, col));
                }
                _return.add(line);
            }
            wb.close();
        } catch (IOException | BiffException ex) {
            throw ex;
        }
        return _return;
    }

    /**
     * Abre o arquivo Excel
     *
     * @param file arquivo do excel
     * @throws IOException erro ao abrir arquivo
     * @throws BiffException arquivo ilegível
     */
    private static void open(File file) throws IOException, BiffException {
        try {
            WorkbookSettings ws = new WorkbookSettings();
            ws.setEncoding("Cp1252");
            wb = Workbook.getWorkbook(file, ws);
            s = wb.getSheet(0);
        } catch (IOException | BiffException ex) {
            throw ex;
        }
    }

    /**
     * Retorna o conteúdo de uma célula
     *
     * @param row linha
     * @param col coluna
     * @return conteúdo da célula
     */
    private static String getCell(int row, int col) {
        try {
            return s.getRow(row)[col].getContents();
        } catch (Exception ex) {
            return "";
        }
    }
}
