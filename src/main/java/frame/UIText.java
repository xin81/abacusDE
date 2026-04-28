package frame;

public final class UIText {
    private final static String DE="de";
    private final static String EN="en";
    private static String APPNAME="Abacus";
    private static String CLOSE="Close";
    private static String HIDE_NUMBERING="Hide Numbering";
    private static String HIDE_RESULTS="Hide Result Display";
    private static String FILE="File";
    private static String OPTIONS="Options";
    private static String RESET="Reset";
    private static String language=EN;

    /*private UIText(String language){
        this.language=language;
    }
    private UIText(){
        this.language=EN;// by default: english
    }*/

    /*public static UIText getInstance(String language){
        return new UIText(language);
    }*/

    public static String getAppName(String language){
        if(language.compareToIgnoreCase(DE)==0){
            APPNAME="Abakus";
        }
        return APPNAME;
    }
    public static String getCloseStr(String language){
        if(language.compareToIgnoreCase(DE)==0){
            CLOSE="Schließen";
        }
        return CLOSE;
    }
    public static String getHide_NumberingStr(String language){
        if(language.compareToIgnoreCase(DE)==0){
            HIDE_NUMBERING="Nummierungen ausblenden";
        }
        return HIDE_NUMBERING;
    }
    public static String getHide_Results(String language){
        if(language.compareToIgnoreCase(DE)==0){
            HIDE_RESULTS="Ergebnisanzeige ausblenden";
        }
        return HIDE_RESULTS;
    }
    public static String getFile(String language){
        if(language.compareToIgnoreCase(DE)==0){
            FILE="Datei";
        }
        return FILE;
    }
    public static String getOptions(String language){
        if(language.compareToIgnoreCase(DE)==0){
            OPTIONS="Optionen";
        }
        return OPTIONS;
    }
    public static String getReset(String language){
        if(language.compareToIgnoreCase(DE)==0){
            RESET="Zurücksetzen";
        }
        return RESET;
    }
    public static String getLanguage(){
        return language;
    }
    public static void setLanguage(String lang){
        language=lang;
    }
}
