package com.limagiran.exceltovcard.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Vinicius Silva
 */
public enum I18N {
    WORD_SAVE("word.save"),
    
    VIEW_SELECT_EXCEL_FILE_DESCRIPT("View.selectExcelFileDescript"),
    VIEW_SELECT_EXCEL_FILE("View.selectExcelFile"),
    VIEW_EXCEL_FILE_ERROR("View.excelFileError"),
    VIEW_ONLY_ONE_NAME("View.oneName"),
    VIEW_NAME_REQUIRED("View.nameRequired"),
    VIEW_SAVE_FILE("View.saveFile"),
    VIEW_SAVE_FILE_DESCRIPT("View.saveFileDescript"),
    VIEW_GENERATE_VCARD_SUCESS("View.generateVCardSuccess"),
    VIEW_GENERATE_VCARD_FAILED("View.generateVCardFailed"),
    VIEW_EMPTY_FILE("View.emptyFile"),
    
    ENUM_TYPE_NAME_NAME("EnumTypeName.name"),
    ENUM_TYPE_NAME_CEL("EnumTypeName.cel"),
    ENUM_TYPE_NAME_TEL("EnumTypeName.tel"),
    ENUM_TYPE_NAME_ADDRESS("EnumTypeName.address"),
    ENUM_TYPE_NAME_EMAIL("EnumTypeName.email"),
    
    DIR_ERROR_OPEN_FILE("Dir.errorOpenFile"),
    DIR_OVERRIDE("Dir.override"),
    DIR_MSG_SAVE_FILE("Dir.msgSaveFile"),
    DIR_ERROR_INVALID_NAME("Dir.errorInvalidName");

    private static final String NAME = "com/limagiran/exceltovcard/i18n/Bundle";
    private static final ResourceBundle bundle = ResourceBundle.getBundle(NAME, Locale.getDefault());

    private final String key;

    /**
     * Novo enum i18n
     *
     * @param key chave no arquivo properties
     */
    private I18N(String key) {
        this.key = key;
    }

    /**
     * Valor do enum de acordo com a localidade
     *
     * @return String value
     */
    public String val() {
        return bundle.getString(key);
    }
    
    @Override
    public String toString() {
        return val();
    }

    /**
     * Valor do enum de acordo com a localidade
     *
     * @param args lista de objetos para formatar o texto
     * @return String value
     * @see MessageFormat#format(String, Object...) 
     */
    public String format(Object... args) {
        if ((args != null) && (args.length > 0)) {
            return MessageFormat.format(val(), args);
        }
        return val();
    }

}
