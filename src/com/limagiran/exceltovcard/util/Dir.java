package com.limagiran.exceltovcard.util;

import static com.limagiran.exceltovcard.i18n.I18N.*;
import java.io.File;
import static java.io.File.separator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFileChooser;
import static javax.swing.JFileChooser.APPROVE_OPTION;
import static javax.swing.JFileChooser.CANCEL_OPTION;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Vinicius Silva
 */
public class Dir {

    private static JFileChooser createDefaultFileChooser() {
        return new JFileChooser() {
            @Override
            public void setCurrentDirectory(File dir) {
                if ((dir != null) && dir.getAbsoluteFile().exists()) {
                    super.setCurrentDirectory((dir.isDirectory()
                            ? dir.getAbsoluteFile() : dir.getParentFile()));
                } else {
                    super.setCurrentDirectory(new File("").getAbsoluteFile());
                }
            }
        };
    }
    
    /**
     * Abre uma janela para seleção do local onde um arquivo será salvo. Retorna
     * o caminho completo para o diretório. Retorna null para diretório não
     * selecionado.
     *
     * @param title Título exibido na janela de seleção do diretório
     * @param suggestedName Nome que aparecerá na caixa de seleção como sugestão
     * @param initDir Pasta inicial exibida
     * @param typeFile Descrição do tipo de arquivo a ser salvo
     * @param extensions Extensão do arquivo a ser salvo
     * @return caminho absoluto com o nome digitado para salvar o arquivo
     */
    public static File windowsSave(String title, String suggestedName,
            File initDir, String typeFile, String... extensions) {
        List<String> exList = new ArrayList<>(Arrays.asList(extensions));
        JFileChooser open = createDefaultFileChooser();
        open.setApproveButtonText(WORD_SAVE.val());
        open.setAcceptAllFileFilterUsed(false);
        open.setDialogTitle(title);
        open.setCurrentDirectory(initDir);
        open.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return exList.stream().anyMatch((ex)
                        -> (f.getName().toLowerCase().endsWith(ex)
                        || f.isDirectory()));
            }

            @Override
            public String getDescription() {
                return typeFile;
            }
        });
        if (!suggestedName.isEmpty()) {
            open.setSelectedFile(new File(open.getCurrentDirectory()
                    .getAbsoluteFile() + separator + suggestedName));
        }
        while (true) {
            int option = open.showOpenDialog(null);
            if (option == APPROVE_OPTION) {
                File file = open.getSelectedFile();
                File fileF = file;
                if (exList.stream().noneMatch((ex) -> (fileF.getName().endsWith(ex)))) {
                    file = new File(file + exList.stream().findFirst().orElse(""));
                }
                List<String> list = Arrays.asList("\\", "/", ":", "*", "?", "\"", "<", ">", "|");
                String name = file.getName();
                if (list.stream().anyMatch((c) -> (name.contains(c)))) {
                    JOptionPane.showMessageDialog(null, DIR_ERROR_INVALID_NAME.format(list));
                } else if (!file.exists()) {
                    return file.getAbsoluteFile();
                } else if (file.exists() && (JOptionPane.YES_OPTION
                        == JOptionPane.showConfirmDialog(null, DIR_OVERRIDE,
                                DIR_MSG_SAVE_FILE.val(), JOptionPane.YES_NO_OPTION))) {
                    return file.getAbsoluteFile();
                }
            } else if (option == CANCEL_OPTION) {
                return null;
            }
        }
    }
    
    /**
     * Abre uma janela para seleção de arquivo. Retorna o caminho completo para
     * o arquivo. Retorna null para arquivo não selecionado.
     *
     * @param title Título exibido na janela de seleção de arquivo
     * @param initDir Pasta inicial para procurar o arquivo
     * @param typeFile Descrição do tipo de arquivo
     * @param extensions Extensão válida para procurar arquivo
     * @return caminho absoluto do arquivo selecionado ou null para erro ou
     * cancelamento
     */
    public static File windowsFile(String title, File initDir, String typeFile,
            String... extensions) {
        List<String> exList = new ArrayList<>(Arrays.asList(extensions));
        JFileChooser open = createDefaultFileChooser();
        open.setAcceptAllFileFilterUsed(false);
        open.setDialogTitle(title);
        open.setCurrentDirectory(initDir);
        open.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return exList.stream().anyMatch((ex)
                        -> (f.getName().toLowerCase().endsWith(ex)
                        || f.isDirectory()));
            }

            @Override
            public String getDescription() {
                return typeFile;
            }
        });
        if (open.showOpenDialog(null) == APPROVE_OPTION) {
            open.setVisible(false);
            return open.getSelectedFile().getAbsoluteFile();
        }
        return null;
    }
}
