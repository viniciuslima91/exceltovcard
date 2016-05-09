package com.limagiran.exceltovcard;

import com.limagiran.exceltovcard.view.View;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import static javax.swing.UIManager.getInstalledLookAndFeels;
import javax.swing.UnsupportedLookAndFeelException;

public class VCard {

    public static void main(String[] args) {
        applyNimbus();
        //Locale.setDefault(Locale.US);
        View.main();
    }

    public static void applyNimbus() {
        try {
            for (LookAndFeelInfo info : getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    UIDefaults ui = UIManager.getLookAndFeelDefaults();
                    ui.put("Table.gridColor", new Color(214, 217, 223));
                    ui.put("Table.disabled", false);
                    ui.put("Table.showGrid", true);
                    ui.put("Table.intercellSpacing", new Dimension(1, 1));
                    ui.put("ScrollBar.minimumThumbSize", new Dimension(30, 30));
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException |
                InstantiationException | IllegalAccessException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
