package umapedigree.beadieststar64.com.github.Receptor;

import umapedigree.beadieststar64.com.github.Display.parentDisplay;
import umapedigree.beadieststar64.com.github.FileSys.UPGRead;
import umapedigree.beadieststar64.com.github.UmaPedigree;

import java.util.Scanner;

public class parentReceptor extends UmaPedigree {

    private static final parentDisplay display = new parentDisplay();

    private int beforeSelectNum;

    public int getBeforeSelectNum() {return this.beforeSelectNum;}
    public void setBeforeSelectNum(int num) {this.beforeSelectNum = num;}

    public void mode() {
        Scanner scanner = new Scanner(System.in);
        try {
            int selectNum = scanner.nextInt();
            switch (selectNum) {
                case 1 -> display.register(1);
                case 2 -> display.readRecordedUmas();
            }
        }catch (Exception e) {
            display.intError();
        }
    }

    public void registerMethod(String mode) {
        Scanner scanner = new Scanner(System.in);
        try {
            int selectNum = scanner.nextInt();
            switch (mode) {
                case "row" -> {
                    if(returnMenus(selectNum, 1)) {
                        if(selectError(selectNum, 0, display.getJapaneseOrder())) {
                            display.setRowNum(selectNum);
                            display.registerColumn(selectNum);
                        }
                    }
                }
                case "column" -> {
                    if(returnMenus(selectNum, 1)) {
                        if(selectError(selectNum, 0, display.getColumn())) {
                            display.setColumnNum(selectNum - 1);
                            display.registerUma(selectNum - 1);
                        }
                    }
                }
                case "registered" -> {
                    if(returnMenus(selectNum, 1)) {
                        if(selectError(selectNum, 0, display.getUmas())) {
                            display.registeredUma(selectNum);
                        }
                    }
                }
            }
        }catch (Exception e) {
            display.intError();
        }
    }

    public void factorMethod(String mode) {
        Scanner scanner = new Scanner(System.in);
        try {
            int selectNum = scanner.nextInt();
            switch (mode) {
                case "blue factor name" -> {
                    if(returnMenus(selectNum, 1)) {
                        if(selectError(selectNum, 1, display.getFactors())) {
                            display.setBlueFactorName(display.getFactors()[selectNum - 1]);
                            display.showFactor(1);
                        }
                    }
                }
                case "blue factor level" -> {
                    if(returnMenus(selectNum, 2)) {
                        if(selectError(selectNum, 2, display.getFactorLevel())) {
                            display.setBlueFactorLevel(selectNum);
                            display.register(3);
                        }
                    }
                }
                case "red factor name" -> {
                    if(returnMenus(selectNum, 2)) {
                        if(selectError(selectNum, 2, display.getFactors())) {
                            display.setRedFactorName(display.getFactors()[selectNum - 1]);
                            display.showFactor(2);
                        }
                    }
                }
                case "red factor level" -> {
                    if(returnMenus(selectNum, 3)) {
                        if(selectError(selectNum, 3, display.getFactorLevel())) {
                            display.setRedFactorLevel(selectNum);
                            display.register(4);
                        }
                    }
                }
                case "green factor name" -> {
                    if(returnMenus(selectNum, 3)) {
                        if(selectError(selectNum, 3, display.getFactors())) {
                            display.setGreenFactorName(display.getFactors()[selectNum - 1]);
                            display.showFactor(3);
                        }
                    }
                }
                case "green factor level" -> {
                    if(returnMenus(selectNum, 4)) {
                        if(selectError(selectNum, 4, display.getFactors())) {
                            display.setGreenFactorLevel(selectNum);
                            display.register(5);
                        }
                    }
                }
                case "skill factor level" -> {
                    if (returnMenus(selectNum, 5)) {
                        if(selectError(selectNum, 5, display.getFactors())) {
                            display.getGeneSkillsLevel().add(selectNum);
                            display.setListNum(0);
                            display.register(6);
                        }
                    }
                }
            }
        }catch (Exception e) {
            int num = 1;
            switch (mode) {
                case "blue factor name" -> {
                }
                case "blue factor level", "red factor name" -> num = 2;
                case "red factor level", "green factor name" -> num = 3;
                case "green factor level" -> num = 4;
                case "skill factor level" -> {
                    if(display.getGeneSkillsName().isEmpty()) {
                       num = 5;
                    }else{
                        num = 6;
                    }
                }
            }
            display.intError();
            display.register(num);
        }
    }

    public void skillFactorMethod(String mode) {
        Scanner scanner = new Scanner(System.in);
        try {
            int selectNum = scanner.nextInt();
            switch (mode) {
                case "select type" -> {
                    if(returnMenus(selectNum, 5)) {
                        if(selectError(selectNum, 5, display.getFactors())) {
                            setBeforeSelectNum(selectNum - 1);
                            display.skillFactor(1, selectNum - 1, UPGRead.getSkillFactorNameGroup());
                        }
                    }
                }
                case "select skill" -> {
                    if(returnMenus(selectNum, 5)) {
                        if(selectError(selectNum, 5, display.getFactors())) {
                            display.getGeneSkillsName().add(display.getFactors()[selectNum - 1]);
                            display.setSkillFactorName(display.getFactors()[selectNum - 1]);
                            display.showFactor(4);
                        }
                    }
                }
            }
        }catch (Exception e) {
            int num;
            String prompt = scanner.next();
            if(prompt.equalsIgnoreCase("#pass")) {
                display.register(7);
            }else if(prompt.equalsIgnoreCase("next")) {
                num = display.getListNum() + 1;
                display.setListNum(num);
                display.skillFactor(1, getBeforeSelectNum(), UPGRead.getSkillFactorNameGroup());
            }else if(prompt.equalsIgnoreCase("back")) {
                num = display.getListNum() - 1;
                display.setListNum(num);
                display.skillFactor(1, getBeforeSelectNum(), UPGRead.getSkillFactorNameGroup());
            }else if(prompt.equalsIgnoreCase("edit")) {

            }else{
                display.intError();
                if(display.getGreenFactorName().isEmpty()) {
                    display.register(5);
                }else{
                    display.register(6);
                }
            }
        }
    }

    public void otherParent(int modeNum) {
        Scanner scanner = new Scanner(System.in);
        try {
            int num = scanner.nextInt();
            switch (modeNum) {
                case 1 -> {
                    if(returnMenus(num, 6)) {
                        display.setEvaluationPoint(num);
                        display.register(8);
                    }
                }
                case 2 -> {
                    if(returnMenus(num, 7)) {
                        display.setLifetimeRecordAll(num);
                        display.register(9);
                    }
                }
                case 3 -> {
                    if(returnMenus(num, 8)) {
                        display.setLifetimeRecord(num);
                        display.register(10);
                    }
                }
                case 4 -> {
                    if(returnMenus(num, 9)) {
                        display.setLifetimeRecordG1s(num);
                        display.register(11);
                    }
                }
                case 5 -> {
                    if(returnMenus(num, 10)) {
                        display.getLifetimeRecordG1sNames().add(UPGRead.getRacesList().get(display.getListNum() * 10 + num - 1));
                        display.register(11);
                    }
                }
                case 6 -> {
                    if(returnMenus(num, 11)) {
                        display.setCollectedFans(num);
                        display.register(14);
                    }
                }
                case 8 -> {
                    if(returnMenus(num, 12)) {
                        display.setScenario(display.getFactors()[num - 1]);
                        display.registerData();
                    }
                }
            }

        }catch (Exception e) {
            String prompt = scanner.next();
            int num;
            if(prompt.equalsIgnoreCase("#pass")) {
                if(display.getLifetimeRecordG1sNames().size() == display.getLifetimeRecordG1s()) {
                    display.register(12);
                }else{
                    System.out.println("G1勝利数と不一致があります。あと" + (display.getLifetimeRecordG1s() - display.getLifetimeRecordG1sNames().size()) + "個入力してください。");
                }
            }else if(prompt.equalsIgnoreCase("next")){
                num = display.getListNum() + 1;
                display.setListNum(num);
                display.register(11);
            }else if(prompt.equalsIgnoreCase("back")) {
                num = display.getListNum() - 1;
                display.setListNum(num);
                display.register(11);
            }else{
                e.printStackTrace();
            }
        }
    }

    public void parentRegister() {
        Scanner scanner = new Scanner(System.in);
        String prompt = scanner.next();
        if(prompt.equalsIgnoreCase("Y") || prompt.equalsIgnoreCase("y")) {
            getSql().writeRecord(display.getRegisterUmaName(),
                    display.getBlueFactorName(), display.getBlueFactorLevel(), display.getRedFactorName(), display.getRedFactorLevel(), display.getGreenFactorName(), display.getGreenFactorLevel(),
                    display.getGeneSkillsName(), display.getGeneSkillsLevel(),
                    display.getEvaluationPoint(),
                    display.getLifetimeRecordAll(), display.getLifetimeRecord(), display.getLifetimeRecordG1s(), display.getLifetimeRecordG1sNames(),
                    display.getCollectedFans(), display.getWiningHistory(), display.getScenario());
        }else{
            display.register(14);
        }
    }

    public boolean returnMenus(int selectNum, int num) {
        if(selectNum == 0) {
            if (num == 1) {
                display.entrance();
            } else {
                display.register(num);
                display.setListNum(0);
            }
            return false;
        }else{
            return true;
        }
    }

    public boolean selectError(int selectNum, int mode, String[] strings) {
        if(selectNum - 1 >= strings.length) {
            display.selectError();
            if (mode == 0) {
                display.entrance();
            } else {
                display.register(mode);
            }
            return false;
        }else{
            return true;
        }
    }

}
