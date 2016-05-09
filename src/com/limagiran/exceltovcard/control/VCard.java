package com.limagiran.exceltovcard.control;

import static com.limagiran.exceltovcard.i18n.I18N.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vinicius Silva
 */
public class VCard {

    private static final String BEGIN = "BEGIN:VCARD\n";
    private static final String VERSION = "VERSION:2.1\n";
    private static final String NAME = "N:;?;;;\n";
    private static final String NAME_2 = "FN:?\n";
    private static final String CELL = "TEL;CELL:?\n";
    private static final String PHONE = "TEL;HOME:?\n";
    private static final String ADDRESS = "ADR;HOME;;;DOM:;?;;\n";
    private static final String EMAIL = "EMAIL;PREF:?\n";
    private static final String END = "END:VCARD\n";

    private String name = "";
    private List<String> cell = new ArrayList<>();
    private List<String> phone = new ArrayList<>();
    private String address = "";
    private List<String> email = new ArrayList<>();

    @Override
    public String toString() {
        return name;
    }

    /**
     * Cria e retorna o conteúdo do arquivo .vcf
     *
     * @return conteúdo do arquivo .vcf pronto para ser salvo em disco e
     * utilizado
     */
    public String getVCard() {
        StringBuilder vCard = new StringBuilder();
        vCard.append(BEGIN)
                .append(VERSION)
                .append(NAME.replace("?", getName()))
                .append(NAME_2.replace("?", getName()))
                .append(getAll(getCell(), CELL))
                .append(getAll(getPhone(), PHONE))
                .append(ADDRESS.replace("?", getAddress()))
                .append(getAll(getEmail(), EMAIL))
                .append(END);
        return vCard.toString();
    }

    /**
     * Adiciona um atributo ao vCard
     *
     * @param value valor
     * @param type tipo do atributo
     * @throws NullPointerException se {@code type} is null
     */
    public void put(String value, EnumTypeName type) throws NullPointerException {
        switch (type) {
            case NAME:
                setName(value);
                break;
            case CEL:
                addCell(value);
                break;
            case TEL:
                addPhone(value);
                break;
            case ADDRESS:
                setAddress(value);
                break;
            case EMAIL:
                addEmail(value);
                break;
        }
    }

    /**
     * @return o nome
     */
    public String getName() {
        return name;
    }

    /**
     * @param name novo nome
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return os telefones
     */
    public List<String> getPhone() {
        return phone;
    }

    /**
     * @param phone telefone a ser adicionado
     */
    public void addPhone(String phone) {
        this.phone.add(phone);
    }

    /**
     * @return o endereço
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address o novo endereço
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return os celulares
     */
    public List<String> getCell() {
        return cell;
    }

    /**
     * @param cell celular a ser adicionado
     */
    public void addCell(String cell) {
        this.cell.add(cell);
    }

    /**
     * @return o e-mail
     */
    public List<String> getEmail() {
        return email;
    }

    /**
     * @param email e-mail a ser adicionado
     */
    public void addEmail(String email) {
        this.email.add(email);
    }

    /**
     * Formatar uma lista de dados
     *
     * @param list lista de dados
     * @param code modelo de código
     * @return uma linha com um modelo de código preenchido para cada dado da
     * lista de dados
     */
    private String getAll(List<String> list, String code) {
        StringBuilder _return = new StringBuilder();
        list.stream().forEach(val -> _return.append(code.replace("?", val)));
        return _return.toString();
    }

    /**
     * Enum com os tipos de dados permitidos
     */
    public static enum EnumTypeName {
        EMPTY(""), NAME(ENUM_TYPE_NAME_NAME),
        CEL(ENUM_TYPE_NAME_CEL), TEL(ENUM_TYPE_NAME_TEL),
        ADDRESS(ENUM_TYPE_NAME_ADDRESS), EMAIL(ENUM_TYPE_NAME_EMAIL);

        /**
         * Nome do tipo
         */
        public final String name;

        /**
         * @param name nome do tipo de dado
         * @throws se name == null
         */
        private EnumTypeName(Object name) throws NullPointerException {
            this.name = name.toString();
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
