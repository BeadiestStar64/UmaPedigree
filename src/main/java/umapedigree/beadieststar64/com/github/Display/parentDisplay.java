package umapedigree.beadieststar64.com.github.Display;

import umapedigree.beadieststar64.com.github.FileSys.UPGRead;
import umapedigree.beadieststar64.com.github.Receptor.parentReceptor;
import umapedigree.beadieststar64.com.github.UmaPedigree;

import java.util.ArrayList;


public class parentDisplay extends UmaPedigree {

    private final EscapeColor escape = new EscapeColor();
    private static final parentReceptor receptor = new parentReceptor();
    private final mainDisplay display = new mainDisplay();

    private final String log = "[" + escape.getGREEN() + "UmaPedigree" + escape.getRESET() + "]";
    private final String[] japaneseOrder = {"あ", "か", "さ", "た", "な", "は", "ま", "や", "ら", "わ"};
    private String registerUmaName;
    private String[] row;
    private String[] column;
    private String[] umas;

    private String[] factors;
    private final String[] factorLevel = {"★☆☆", "★★☆", "★★★"};
    private String blueFactorName;
    private int blueFactorLevel;
    private String redFactorName;
    private int redFactorLevel;
    private String greenFactorName;
    private int greenFactorLevel;
    private String skillFactorName;
    private int skillFactorLevel;

    private int evaluationPoint;
    private int lifetimeRecordAll;
    private int lifetimeRecord;
    private int lifetimeRecordG1s;
    private ArrayList<String> lifetimeRecordG1sNames = new ArrayList<>();
    private int collectedFans;
    private String winingHistory = "";
    private String scenario;

    private int RowNum;
    private int ColumnNum;

    private final ArrayList<String> geneSkillsName = new ArrayList<>();
    private final ArrayList<Integer> geneSkillLevel = new ArrayList<>();
    private int listNum = 0;

    public String[] getJapaneseOrder() {return this.japaneseOrder;}

    public void setRow(String[] strings) {this.row = strings;}
    public String[] getColumn() {return this.column;}
    public void setColumn(String[] strings) {this.column = strings;}
    public String[] getUmas() {return this.umas;}
    public void setUmas(String[] strings) {this.umas = strings;}

    public int getRowNum() {return this.RowNum;}
    public void setRowNum(int num) {this.RowNum = num;}
    public int getColumnNum() {return this.ColumnNum;}
    public void setColumnNum(int num) {this.ColumnNum = num;}

    public String getRegisterUmaName() {return this.registerUmaName;}
    public void setRegisterUmaName(String name) {this.registerUmaName = name;}

    public String[] getFactors() {return this.factors;}
    public void setFactors(String[] strings) {this.factors = strings;}
    public String[] getFactorLevel() {return this.factorLevel;}
    public String getBlueFactorName() {return this.blueFactorName;}
    public void setBlueFactorName(String name) {this.blueFactorName = name;}
    public String getRedFactorName() {return this.redFactorName;}
    public void setRedFactorName(String name) {this.redFactorName = name;}
    public String getGreenFactorName() {return this.greenFactorName;}
    public void setGreenFactorName(String name) {this.greenFactorName = name;}
    public String getSkillFactorName() {return this.skillFactorName;}
    public void setSkillFactorName(String name) {this.skillFactorName = name;}
    public int getSkillFactorLevel() {return this.skillFactorLevel;}
    public void setSkillFactorLevel(int num) {this.skillFactorLevel = num;}
    public int getBlueFactorLevel() {return this.blueFactorLevel;}
    public void setBlueFactorLevel(int level) {this.blueFactorLevel = level;}
    public int getRedFactorLevel() {return this.redFactorLevel;}
    public void setRedFactorLevel(int level) {this.redFactorLevel = level;}
    public int getGreenFactorLevel() {return this.greenFactorLevel;}
    public void setGreenFactorLevel(int level) {this.greenFactorLevel = level;}

    public ArrayList<String> getGeneSkillsName() {return this.geneSkillsName;}
    public ArrayList<Integer> getGeneSkillsLevel() {return this.geneSkillLevel;}
    public int getListNum() {return this.listNum;}
    public void setListNum(int num) {this.listNum = num;}

    public int getEvaluationPoint() {return this.evaluationPoint;}
    public void setEvaluationPoint(int num) {this.evaluationPoint = num;}
    public int getLifetimeRecordAll() {return this.lifetimeRecordAll;}
    public void setLifetimeRecordAll(int num) {this.lifetimeRecordAll = num;}
    public int getLifetimeRecord() {return this.lifetimeRecord;}
    public void setLifetimeRecord(int num) {this.lifetimeRecord = num;}
    public int getLifetimeRecordG1s() {return this.lifetimeRecordG1s;}
    public void setLifetimeRecordG1s(int num) {this.lifetimeRecordG1s = num;}
    public ArrayList<String> getLifetimeRecordG1sNames() {return this.lifetimeRecordG1sNames;}
    public void setLifetimeRecordG1sNames(ArrayList<String> list) {this.lifetimeRecordG1sNames = list;}
    public int getCollectedFans() {return this.collectedFans;}
    public void setCollectedFans(int num) {this.collectedFans = num;}
    public String getWiningHistory() {return this.winingHistory;}
    public void setWiningHistory(String str) {this.winingHistory = str;}
    public String getScenario() {return this.scenario;}
    public void setScenario(String str) {this.scenario = str;}


    public void clear() {
        System.out.print(escape.getFlash());
        System.out.flush();
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

    public void entrance() {
        clear();
        System.out.println("===== 親ウマ娘一覧 =====\n");
        System.out.println("1. 登録");
        System.out.println("2. 閲覧");
        System.out.println("3. 削除");
        System.out.println("0. 戻る");
        receptor.mode();
    }

    public void readRecordedUmas() {
        ArrayList<ArrayList<String>> list = getSql().readRecord();
        System.out.println(list);
    }

    public void register(int num) {
        switch (num) {
            case 1 -> {
                clear();
                System.out.println("登録するウマ娘の頭文字の行を選択してください。\n");
                setRow(display.showRow());
                receptor.registerMethod("row");
            }
            case 2 -> {
                clear();
                System.out.println("登録ウマ娘: " + getRegisterUmaName() + "\n");
                System.out.println("青因子を選択してください。\n");
                String[] blueFactors = {"スピード", "スタミナ", "パワー", "根性", "賢さ"};
                setFactors(showFactor(blueFactors));
                receptor.factorMethod("blue factor name");
            }
            case 3 -> {
                clear();
                System.out.println("登録ウマ娘: " + getRegisterUmaName());
                System.out.println("登録青因子: " + getBlueFactorName() + "(" + getFactorLevel()[getBlueFactorLevel() - 1] + ")\n");
                System.out.println("赤因子を選択してください。\n");
                String[] redFactors = {"短距離", "マイル", "中距離", "長距離", "逃げ", "先行", "差し", "追込", "芝", "ダート"};
                setFactors(showFactor(redFactors));
                receptor.factorMethod("red factor name");
            }
            case 4 -> {
                clear();
                setGreenFactorName(getSql().getUniqueSkill(getRegisterUmaName()));
                String[] greenFactors = getGreenFactorName().split(",");
                System.out.println("登録ウマ娘: " + getRegisterUmaName());
                System.out.println("登録青因子: " + getBlueFactorName() + "(" + getFactorLevel()[getBlueFactorLevel() - 1] + ")");
                System.out.println("登録赤因子: " + getRedFactorName() + "(" + getFactorLevel()[getRedFactorLevel() - 1] + ")\n");
                System.out.println("固有スキルを選択してください。\n");
                setFactors(showFactor(greenFactors));
                receptor.factorMethod("green factor name");
            }
            case 5 -> {
                clear();
                String arg = "";
                if(!getGeneSkillsName().isEmpty()) {
                    arg = "登録済みの因子を編集するには「edit」を入力してください。";
                }
                String[] skillFactors = {"能力上昇スキル", "速度スキル", "加速スキル", "回復スキル", "デバフスキル", "ゲートスキル", "ポジションスキル", "視野スキル"};
                System.out.println("登録ウマ娘: " + getRegisterUmaName());
                System.out.println("登録青因子: " + getBlueFactorName() + "(" + getFactorLevel()[getBlueFactorLevel() - 1] + ")");
                System.out.println("登録赤因子: " + getRedFactorName() + "(" + getFactorLevel()[getRedFactorLevel() - 1] + ")");
                System.out.println("登録固有スキル因子: " + getGreenFactorName() + "(" + getFactorLevel()[getGreenFactorLevel() - 1 ] + ")\n");
                System.out.println("登録するスキル因子の種類を選択してください。スキル因子登録を飛ばすには、「#pass」と入力してください。" + arg);
                setFactors(showFactor(skillFactors));
                receptor.skillFactorMethod("select type");
            }
            case 6 -> {
                clear();
                String arg = "";
                if(!getGeneSkillsName().isEmpty()) {
                    arg = "登録済みの因子を編集するには「edit」を入力してください。";
                }
                String[] skillFactors = {"能力上昇スキル", "速度スキル", "加速スキル", "回復スキル", "デバフスキル", "ゲートスキル", "ポジションスキル", "視野スキル"};
                System.out.println("登録ウマ娘: " + getRegisterUmaName());
                System.out.println("登録青因子: " + getBlueFactorName() + "(" + getFactorLevel()[getBlueFactorLevel() - 1] + ")");
                System.out.println("登録赤因子: " + getRedFactorName() + "(" + getFactorLevel()[getRedFactorLevel() - 1] + ")");
                System.out.println("登録固有スキル因子: " + getGreenFactorName() + "(" + getFactorLevel()[getGreenFactorLevel() - 1 ] + ")");
                System.out.println("登録スキル因子" + getGeneSkillsName() + "(" + getGeneSkillsLevel() + ")\n");
                System.out.println("登録するスキル因子の種類を選択してください。スキル因子登録を飛ばすには、「#pass」と入力してください。" + arg);
                setFactors(showFactor(skillFactors));
                receptor.skillFactorMethod("select type");
            }
            case 7 -> {
                clear();
                String skillFactors = "";
                boolean displayBool = false;
                if(!getGeneSkillsName().isEmpty()) {
                    StringBuilder builder = new StringBuilder();
                    int i = 0;
                    for(String str : getGeneSkillsName()) {
                        if(i == 0) {
                            builder = new StringBuilder(str + "(" + getFactorLevel()[getGeneSkillsLevel().get(i) - 1] + ")");
                        }else{
                            builder = new StringBuilder(builder + "、" + str + "(" + getFactorLevel()[getGeneSkillsLevel().get(i) - 1] + ")");
                        }
                        i++;
                    }
                    skillFactors = builder.toString();
                    displayBool = true;
                }
                System.out.println("登録ウマ娘: " + getRegisterUmaName());
                System.out.println("登録青因子: " + getBlueFactorName() + "(" + getFactorLevel()[getBlueFactorLevel() - 1] + ")");
                System.out.println("登録赤因子: " + getRedFactorName() + "(" + getFactorLevel()[getRedFactorLevel() - 1] + ")");
                System.out.println("登録固有スキル因子: " + getGreenFactorName() + "(" + getFactorLevel()[getGreenFactorLevel() - 1 ] + ")");
                if(displayBool) {
                    System.out.println("登録スキル因子: " + skillFactors + "\n");
                }
                System.out.println("評価点を数字で入力してください。コンマを入力するとエラーになります!\n0. 戻る");
                receptor.otherParent(1);
            }
            case 8 -> {
                clear();
                System.out.println("評価点: " + getEvaluationPoint());
                System.out.println("出走レース数を入力してください。\n0. 戻る");
                receptor.otherParent(2);
            }
            case 9 -> {
                clear();
                System.out.println("評価点: " + getEvaluationPoint());
                System.out.println("出走レース数: " + getLifetimeRecordAll());
                System.out.println("出走レースのうち、勝利した全てのレース数を入力してください。\n0. 戻る");
                receptor.otherParent(3);
            }
            case 10 -> {
                clear();
                System.out.println("評価点: " + getEvaluationPoint());
                System.out.println("出走レース数: " + getLifetimeRecordAll());
                System.out.println("勝利レース数: " + getLifetimeRecord());
                System.out.println("勝利したG1の数を入力してください。クラシックとシニアで重複したレース数も含めて下さい。\n0. 戻る");
                setListNum(0);
                receptor.otherParent(4);
            }
            case 11 -> {
                clear();
                System.out.println("評価点: " + getEvaluationPoint());
                System.out.println("出走レース数: " + getLifetimeRecordAll());
                System.out.println("勝利レース数: " + getLifetimeRecord());
                System.out.println("勝利G1数: " + getLifetimeRecordG1s());
                System.out.println("勝利したG1の名前を選択してください。次の入力に進むには、「#pass」を入力してください。\n");
                if(!getLifetimeRecordG1sNames().isEmpty()) {
                    System.out.println("勝利G1: " + getLifetimeRecordG1sNames());
                }
                System.out.println(show(UPGRead.getRacesList()));
                String[] factors;
                if(UPGRead.getRacesList().size() > 10) {
                    factors = UPGRead.getRacesList().subList(getListNum() * 10, 10 + (getListNum() * 10)).toArray(new String[0]);
                }else if(UPGRead.getRacesList().size() > 1){
                    factors = UPGRead.getRacesList().subList(getListNum() * 10, UPGRead.getRacesList().size()).toArray(new String[0]);
                }else{
                    factors = UPGRead.getRacesList().toArray(new String[0]);
                }
                setFactors(showFactor(factors));
                receptor.otherParent(5);
            }
            case 12 -> {
                clear();
                System.out.println("評価点: " + getEvaluationPoint());
                System.out.println("出走レース数: " + getLifetimeRecordAll());
                System.out.println("勝利レース数: " + getLifetimeRecord());
                System.out.println("勝利G1: " + getLifetimeRecordG1sNames());
                System.out.println("勝利G1: " + getLifetimeRecordG1s() + "つ");
                System.out.println("獲得ファン数を数字で入力してください。コンマを入力するとエラーになります!\n0. 戻る");
                receptor.otherParent(6);
            }
            case 13 -> {
                clear();
                System.out.println("評価点: " + getEvaluationPoint());
                System.out.println("出走レース数: " + getLifetimeRecordAll());
                System.out.println("勝利レース数: " + getLifetimeRecord());
                System.out.println("勝利G1: " + getLifetimeRecordG1sNames());
                System.out.println("勝利G1: " + getLifetimeRecordG1s() + "つ");
                System.out.println("獲得ファン数: " + getCollectedFans() + "人");
                System.out.println("二つ名を選択してください。\n0. 戻る");
                receptor.otherParent(7);
            }
            case 14 -> {
                clear();
                String[] scenarios = UPGRead.getScenarioList().toArray(new String[0]);
                System.out.println("評価点: " + getEvaluationPoint());
                System.out.println("出走レース数: " + getLifetimeRecordAll());
                System.out.println("勝利レース数: " + getLifetimeRecord());
                System.out.println("勝利G1: " + getLifetimeRecordG1sNames());
                System.out.println("勝利G1: " + getLifetimeRecordG1s() + "つ");
                System.out.println("獲得ファン数: " + getCollectedFans() + "人");
                System.out.println("二つ名: " + getWiningHistory());
                System.out.println("育成シナリオを選択してください。");
                setFactors(showFactor(scenarios));
                receptor.otherParent(8);
            }
        }
    }

    public void registerColumn(int num) {
        clear();
        System.out.println("登録するウマ娘の頭文字の段を選択してください。\n");
        setColumn(display.showColumn(num));
        receptor.registerMethod("column");
    }

    public void registerUma(int num) {
        clear();
        System.out.println("登録するウマ娘を選択してください。\n");
        setUmas(display.selectUmaName(num, getRowNum()));
        receptor.registerMethod("registered");
    }

    public void showFactor(int type) {
        String factorName = "";
        String argStr = "";
        switch (type) {
            case 1 -> { //青因子
                factorName = getBlueFactorName();
                argStr = "blue";
            }
            case 2 -> { //赤因子
                factorName = getRedFactorName();
                argStr = "red";
            }
            case 3 -> { //緑因子
                factorName = getGreenFactorName();
                argStr = "green";
            }
            case 4 -> { //白因子
                factorName = getSkillFactorName();
                argStr = "skill";
            }
        }
        clear();
        System.out.println(factorName + "因子のレベルを選択してください。\n");
        int i = 1;
        for(String level : getFactorLevel()) {
            System.out.println(i + ". " + level);
            i++;
        }
        System.out.println("0. 戻る");
        setFactors(getFactorLevel());
        receptor.factorMethod(argStr + " factor level");
    }

    public String showSomeData(int showListNum, ArrayList<ArrayList<String>> parentList) {
        clear();
        String addMsg = "";
        ArrayList<String> list = parentList.get(showListNum);
        try {
            String errorCheck = (list.get(10 + (getListNum() * 10)));
            if (list.subList(getListNum() * 10, 10 + (getListNum() * 10)).size() > 9 && getListNum() == 0) {
                addMsg = "次のページを見るには「next」を入力してください。";
            } else if (list.subList(getListNum() * 10, 10 + (getListNum() * 10)).size() > 9 && getListNum() > 0) {
                addMsg = "次のページを見るには「next」、前のページを見るには「back」を入力してください。";
            }
        } catch (Exception e) {
            addMsg = "前のページを見るには「back」を入力してください。";
        }
        return addMsg;
    }

    public String show(ArrayList<String> list) {
        clear();
        String addMsg = "";
        try {
            String errorCheck = (list.get(10 + (getListNum() * 10)));
            if (list.subList(getListNum() * 10, 10 + (getListNum() * 10)).size() > 9 && getListNum() == 0) {
                addMsg = "次のページを見るには「next」を入力してください。\n";
            } else if (list.subList(getListNum() * 10, 10 + (getListNum() * 10)).size() > 9 && getListNum() > 0) {
                addMsg = "次のページを見るには「next」、前のページを見るには「back」を入力してください。\n";
            }
        }catch (Exception e) {
            addMsg = "前のページを見るには「back」を入力してください。\n";
        }
        return addMsg;
    }

    public void skillFactor(int mode, int showListNum, ArrayList<ArrayList<String>> parentList) {

        String addMsg = showSomeData(showListNum, parentList);
        ArrayList<String> list = parentList.get(showListNum);
        String msg = getFactors()[showListNum] + "因子を選択してください。" + addMsg;
        System.out.println(msg);

        if (mode == 1) {
            try {
                String[] factors;
                if(list.size() > 10) {
                    factors = UPGRead.getSkillFactorNameGroup().get(showListNum).subList(getListNum() * 10, 10 + (getListNum() * 10)).toArray(new String[0]);
                }else if(list.size() > 1){
                    factors = list.subList(getListNum() * 10, list.size()).toArray(new String[0]);
                }else{
                    factors = list.toArray(new String[0]);
                }
                setFactors(showFactor(factors));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        receptor.skillFactorMethod("select skill");
    }

    public void registerData() {
        try {
            StringBuilder builder = new StringBuilder();
            int i = 0;
            for(String str : getGeneSkillsName()) {
                if(i == 0) {
                    builder = new StringBuilder(str + "(" + getFactorLevel()[getGeneSkillsLevel().get(i) - 1] + ")");
                }else{
                    builder = new StringBuilder(builder + "、" + str + "(" + getFactorLevel()[getGeneSkillsLevel().get(i) - 1] + ")");
                }
                i++;
            }
            String skillFactors = builder.toString();

            StringBuilder strBuilder = new StringBuilder();
            int l = 0;
            for(String str : getLifetimeRecordG1sNames()) {
                if(l == 0) {
                    strBuilder = new StringBuilder(str);
                }else{
                    strBuilder = new StringBuilder(strBuilder + ", " + strBuilder);
                }
                l++;
            }
            String collectG1s = strBuilder.toString();

            clear();
            System.out.println("登録ウマ娘: " + getRegisterUmaName());
            System.out.println("登録青因子: " + getBlueFactorName() + "(" + getFactorLevel()[getBlueFactorLevel() - 1] + ")");
            System.out.println("登録赤因子: " + getRedFactorName() + "(" + getFactorLevel()[getRedFactorLevel() - 1] + ")");
            System.out.println("登録固有スキル因子: " + getGreenFactorName() + "(" + getFactorLevel()[getGreenFactorLevel() - 1 ] + ")");
            System.out.println("登録スキル因子: " + skillFactors);
            System.out.println("育成評価点: " + getEvaluationPoint());
            System.out.println("出走レース数: " + getLifetimeRecordAll());
            System.out.println("勝利G1数: " + getLifetimeRecordG1s());
            System.out.println("勝利G1一覧: " + collectG1s);
            System.out.println("獲得ファン数: " + getCollectedFans());
            System.out.println("育成シナリオ: " + getScenario());
            System.out.println("\n以上の内容を登録しますか？[Y/N]");
            receptor.parentRegister();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registeredUma(int selectNum) {
        clear();
        int num = (5 * (getRowNum() - 1) + getColumnNum());
        setRegisterUmaName(UPGRead.getUmaList().get(num).get(selectNum - 1));
        register(2);
    }

    public String[] showFactor(String[] factors) {
        int num = 1;
        for(String factor : factors) {
            System.out.println(num + ". " + factor);
            num++;
        }
        System.out.println("0. 戻る");
        return factors;
    }

}