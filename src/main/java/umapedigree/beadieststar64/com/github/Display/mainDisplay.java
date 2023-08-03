package umapedigree.beadieststar64.com.github.Display;

import umapedigree.beadieststar64.com.github.FileSys.UPGRead;
import umapedigree.beadieststar64.com.github.Receptor.mainReceptor;
import umapedigree.beadieststar64.com.github.UmaPedigree;

import java.util.ArrayList;
import java.util.Arrays;


public class mainDisplay extends UmaPedigree {

    private static final EscapeColor escape = new EscapeColor();
    private static final mainReceptor receptor = new mainReceptor();
    private static final debugDisplay debug = new debugDisplay();

    private final String[] japaneseOrder = {"あ", "か", "さ", "た", "な", "は", "ま", "や", "ら", "わ"};
    private final String[] japaneses = {
            "あ", "い", "う", "え", "お",
            "か", "き", "く", "け", "こ",
            "さ", "し", "す", "せ", "そ",
            "た", "ち", "つ", "て", "と",
            "な", "に", "ぬ", "ね", "の",
            "は", "ひ", "ふ", "へ", "ほ",
            "ま", "み", "む", "め", "も",
            "や", "ゆ", "よ", "", "",
            "ら", "り", "る", "れ", "ろ",
            "わ", "", "", "", ""};

    private String[] selectRow;
    private String[] selectColumn;
    private String[] selectUmas;
    private int selectRowNum;
    private int selectColumnNum;

    private final String log = "[" + escape.getGREEN() + "UmaPedigree" + escape.getRESET() + "]";
    private final String sql = "[" + escape.getCYAN() + "UmaPedigree SQL" + escape.getRESET() + "]";

    public String[] getJapaneseOrder() {return japaneseOrder;}

    public void setSelectRow(String[] strings) {this.selectRow = strings;}
    public String[] getSelectColumn() {return this.selectColumn;}
    public void setSelectColumn(String[] strings) {this.selectColumn = strings;}
    public String[] getSelectUmas() {return this.selectUmas;}
    public void setSelectUmas(String[] strings) {this.selectUmas = strings;}

    public int getSelectColumnNum() {return this.selectColumnNum;}
    public void setSelectColumnNum(int num) {this.selectColumnNum = num;}
    public int getSelectRowNum() {return selectRowNum;}
    public void setSelectRowNum(int num) {selectRowNum = num;}


    public void clear() {
        System.out.print(escape.getFlash());
        System.out.flush();
    }

    public void prepareSys() {
        clear();
        System.out.println(log + "システムの準備をしています");
    }

    public void preparedSys() {
        clear();
        System.out.println("\r" + log + "システムの準備が完了しました。");
    }

    public void apiSys() {
        clear();
        System.out.println(log + escape.getYELLOW() + "[WARNING]" + escape.getRESET() +"APIモードで動作中です!!");
    }

    public void debugSys(long time) {
        debug.debugTime(time);
    }

    public void sqlSys(int switching) {
        switch (switching) {
            case 1 -> System.out.println(sql + "SQLへの書き込みを行います。この処理には時間がかかることがあります...");
            case 2 -> System.out.println(sql + "SQLへの書き込みが成功しました。");
        }
    }

    public void intError() {
        clear();
        try {
            System.out.println(log + escape.getRED() + "数字以外を入力しないでください!!");
            Thread.sleep(800);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectError() {
        clear();
        try {
            System.out.println(log + escape.getYELLOW() + "上記以外の数字を入力しないでください!!" + escape.getRESET());
            Thread.sleep(800);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void leave() {
        clear();
        try {
            System.out.println(log + escape.getRESET() + "Good bye!");
            Thread.sleep(1000);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void EULAError() {
        EscapeColor escape = new EscapeColor();
        System.out.println(escape.getYELLOW() + "EULA is false! UmaPedigree will stop ..." + escape.getRESET());
    }

    public void welcome() {
        clear();
        System.out.println(escape.getGREEN() + "===== Welcome to UmaPedigree =====" + escape.getRESET());
        System.out.println("モードを選択してください。");
        System.out.println("\n1. 継承結果一覧");
        System.out.println("2. ウマ娘一覧");
        System.out.println("3. データ読み込み");
        System.out.println("0. 終了");
        receptor.entrance("main select");
    }

    public void registerMain() {
        clear();
        System.out.println("===== 登録モード =====");
        System.out.println("利用したい機能を選択してください。");
        System.out.println("\n1. 登録");
        System.out.println("2. 閲覧");
        System.out.println("3. 削除");
        System.out.println("4. csv取り込み");
        System.out.println("0. 戻る");
        receptor.entrance("register");
    }

    public void inputTxt() {
        clear();
        System.out.println("取り込みたいデータを" +
                escape.getMagenta() + "絶対パス" +
                escape.getRESET() + "で指定してください。");
        receptor.inputReceptor();
    }

    public void registerUma(String type) {
        clear();
        switch (type) {
            case "success" -> {
                System.out.println("育成ウマ娘の頭文字の行を選択してください。\n");
                setSelectRow(showRow());
                receptor.succeedsUma("detailed register");
            }
            case "father" -> {
                System.out.println("現在の育成ウマ娘: " + receptor.getDevelopedUma());
                System.out.println("育成ウマ娘の父親の頭文字の行を選択してください。\n");
                setSelectRow(showRow());
                receptor.fatherUma("register row");
            }
            case "mother" -> {
                System.out.println("現在の育成ウマ娘: " + receptor.getDevelopedUma());
                System.out.println("現在の父親ウマ娘: " + escape.getCYAN() + receptor.getFatherUma() + escape.getRESET());
                System.out.println("育成ウマ娘の母親の頭文字の行を選択してください。\n");
                setSelectRow(showRow());
                receptor.motherUma("register row");
            }
            case "paternal father" -> {
                System.out.println("現在の育成ウマ娘: " + receptor.getDevelopedUma());
                System.out.println("現在の父親ウマ娘: " + escape.getCYAN() + receptor.getFatherUma() + escape.getRESET());
                System.out.println("現在の母親ウマ娘: " + escape.getMagenta() + receptor.getMotherUma() + escape.getRESET());
                setSelectRow(showRow());
                receptor.paternalFatherUma("register row");
            }
        }
    }

    public void detailedRegisterUma(int selectNum) {
        clear();
        System.out.println("育成ウマ娘の頭文字の段を選択してください。\n");
        setSelectColumn(showColumn(selectNum));
        receptor.succeedsUma("detailed register row");
    }

    public void registerUma(int num) {
        clear();
        System.out.println("育成ウマ娘を選択してください。\n");
        setSelectUmas(selectUmaName(num, getSelectRowNum()));
        receptor.succeedsUma("selected");
    }

    public void fatherRegisterRow(int num) {
        clear();
        System.out.println("父親ウマ娘の頭文字の段を選択してください。\n");
        setSelectColumn(showColumn(num));
        receptor.fatherUma("register column");
    }

    public void registerFather(int num) {
        clear();
        System.out.println("育成ウマ娘の父親を選択してください。\n");
        setSelectUmas(selectUmaName(num, getSelectRowNum()));
        receptor.fatherUma("selected");
    }

    public void motherRegisterRow(int num) {
        clear();
        System.out.println("母親ウマ娘の頭文字の段を選択してください。\n");
        setSelectColumn(showColumn(num));
        receptor.motherUma("register column");
    }

    public void registerMother(int num) {
        clear();
        System.out.println("育成ウマ娘の母親を選択してください。\n");
        setSelectUmas(selectUmaName(num, getSelectRowNum()));
        receptor.motherUma("selected");
    }

    public void paternalFatherRegisterRow(int num) {
        clear();
        System.out.println("祖父(父)ウマ娘の頭文字の段を選択してください。");
        setSelectColumn(showColumn(num));
        receptor.paternalFatherUma("register column");
    }

    public void registerPaternalFather(int num) {
        clear();
        System.out.println("育成ウマ娘の祖父(父)ウマ娘を選択してください。\n");
        setSelectUmas(selectUmaName(num, getSelectRowNum()));
        receptor.motherUma("selected");
    }

    public String[] showRow() {
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < getJapaneseOrder().length; i++) {
            System.out.println((i + 1) + ". " + getJapaneseOrder()[i] + "行");
            list.add(getJapaneseOrder()[i]);
        }
        System.out.println("0. 戻る");
        return list.toArray(new String[0]);
    }

    public String[] showColumn(int type) {
        int min = 5 * (type - 1);
        int max = min + 5;
        ArrayList<String> list = new ArrayList<>(Arrays.asList(japaneses).subList(min, max));
        int num = 1;
        for(String row : list) {
            System.out.println(num + ". " + row + "段");
            num++;
        }
        System.out.println("0. 戻る");
        return list.toArray(new String[0]);
    }

    public String[] selectUmaName(int selectNum, int rowNum) {
        int num = (5 * (rowNum - 1) + selectNum);
        int i = 1;
        ArrayList<String> list = new ArrayList<>();
        for(String name : UPGRead.getUmaList().get(num)) {
            if(name.charAt(0) == '*') {
                continue;
            }
            System.out.println(i + ". " + name);
            i++;
            list.add(name);
        }
        System.out.println("0. 戻る");
        return list.toArray(new String[0]);
    }

    public boolean setName(String pedigree, int selectNum) {
        int num = (5 * (getSelectRowNum() - 1) + getSelectColumnNum());
        String name = UPGRead.getUmaList().get(num).get(selectNum - 1);
        String debugMessage;
        if(!judge(pedigree, name)) {
            return false;
        }
        switch (pedigree) {
            case "develop" -> {
                receptor.setDevelopedUma(name);
                debugMessage = receptor.getDevelopedUma();
            }
            case "father" -> {
                receptor.setFatherUma(name);
                debugMessage = receptor.getFatherUma();
            }
            case "mother" -> {
                receptor.setMotherUma(name);
                debugMessage = receptor.getMotherUma();
            }
            case "paternal father" -> {
                receptor.setPaternalFatherUma(name);
                debugMessage = receptor.getPaternalFatherUma();
            }
            case "paternal mother" -> {
                receptor.setPaternalMotherUma(name);
                debugMessage = receptor.getPaternalMotherUma();
            }
            case "maternal father" -> {
                receptor.setMaternalFatherUma(name);
                debugMessage = receptor.getMaternalFatherUma();
            }
            case "maternal mother" -> {
                receptor.setMaternalMother(name);
                debugMessage = receptor.getMaternalMother();
            }
            default -> {
                return false;
            }
        }
        if(isDebug()) {
            debug.registerMessage(debugMessage);
        }
        return true;
    }

    public boolean judge(String pedigree, String name) {
        if(receptor.getDevelopedUma().equalsIgnoreCase(name)) { //育成ウマ娘と重複するか
            if(!pedigree.equalsIgnoreCase("paternal father") || !pedigree.equalsIgnoreCase("paternal mother") || !pedigree.equalsIgnoreCase("maternal father") || !pedigree.equalsIgnoreCase("maternal mother")) {
                if(isDebug()){
                    debug.duplicationMessage();
                }
                duplication(name, receptor.getDevelopedUma(), "育成ウマ娘");
                return false;
            }
        }
        if(receptor.getFatherUma().equalsIgnoreCase(name)) { //父親と重複するか
            if(!pedigree.equalsIgnoreCase("maternal father") || !pedigree.equalsIgnoreCase("maternal mother")) {
                if(isDebug()){
                    debug.duplicationMessage();
                }
                duplication(name, receptor.getFatherUma(), "父親");
                return false;
            }
        }
        if(receptor.getMotherUma().equalsIgnoreCase(name)) { //母親と重複するか
            if(!pedigree.equalsIgnoreCase("paternal father") || !pedigree.equalsIgnoreCase("paternal mother")) {
                if(isDebug()){
                    debug.duplicationMessage();
                }
                duplication(name, receptor.getMotherUma(), "母親");
                return false;
            }
        }
        return true;
    }

    public void duplication(String selectedName, String dupName, String dupRole) {
        try {
            System.out.println("[" + escape.getRED() + "重複検知" + escape.getRESET() + "]" +
                    "選択された[" + selectedName + "]は、[" +
                    escape.getRED() + dupRole + escape.getRESET() + "]の[" + escape.getRED() + dupName +
                    escape.getRESET() + "]と重複します!");
            Thread.sleep(1500);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
