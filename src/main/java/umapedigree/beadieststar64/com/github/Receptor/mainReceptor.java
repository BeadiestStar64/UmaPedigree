package umapedigree.beadieststar64.com.github.Receptor;

import umapedigree.beadieststar64.com.github.Display.debugDisplay;
import umapedigree.beadieststar64.com.github.Display.mainDisplay;
import umapedigree.beadieststar64.com.github.Display.parentDisplay;
import umapedigree.beadieststar64.com.github.FileSys.FileSys;
import umapedigree.beadieststar64.com.github.UmaPedigree;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class mainReceptor extends UmaPedigree {

    private static final mainDisplay display = new mainDisplay();
    private static final parentDisplay parent = new parentDisplay();

    private String developedUma = "";
    private String fatherUma = "";
    private String motherUma = "";
    private String paternalFatherUma = "";
    private String paternalMotherUma = "";
    private String maternalFatherUma = "";
    private String maternalMother = "";

    public String getDevelopedUma() {return this.developedUma;}
    public void setDevelopedUma(String name) {this.developedUma = name;}
    public String getFatherUma() {return this.fatherUma;}
    public void setFatherUma(String name) {this.fatherUma = name;}
    public String getMotherUma() {return this.motherUma;}
    public void setMotherUma(String name) {this.motherUma = name;}
    public String getPaternalFatherUma() {return this.paternalFatherUma;}
    public void setPaternalFatherUma(String name) {this.paternalFatherUma = name;}
    public String getPaternalMotherUma() {return this.paternalMotherUma;}
    public void setPaternalMotherUma(String name) {this.paternalMotherUma = name;}
    public String getMaternalFatherUma() {return this.maternalFatherUma;}
    public void setMaternalFatherUma(String name) {this.maternalFatherUma = name;}
    public String getMaternalMother() {return maternalMother;}
    public void setMaternalMother(String name) {this.maternalMother = name;}

    public void entrance(String select) {
        Scanner scanner = new Scanner(System.in);
        try {
            int selectNum = scanner.nextInt();
            switch (select) {
                case "main select" -> {
                    switch (selectNum) {
                        case 0 -> display.leave();
                        case 1 -> display.registerMain();
                        case 2 -> parent.entrance();
                        case 3 -> display.inputTxt();
                        default -> display.selectError();
                    }
                }
                case "register" -> {
                    switch (selectNum) {
                        case 0 -> display.welcome();
                        case 1 -> display.registerUma("success");
                        case 2 -> {}
                        case 3 -> {}
                        default -> display.selectError();
                    }
                }
            }
        }catch (Exception e) {
            if(scanner.next().equalsIgnoreCase("debug")) {
                if(isDebug()) {
                    debugDisplay debug = new debugDisplay();
                    debug.entrance();
                }
            }
            display.intError();
            display.welcome();
        }
    }

    public void inputReceptor() {
        Scanner scanner = new Scanner(System.in);
        String path = scanner.next();
        path = path.replace("\"", "");
        File file = new File(path);
        if(file.exists()) {
            System.out.println("ファイルが見つかりました");
            getRead().inputTxt(path);
        }else{
            System.out.println("ファイルが見つかりませんでした");
        }
    }

    public void succeedsUma(String mode) {
        Scanner scanner = new Scanner(System.in);
        try {
            int selectNum = scanner.nextInt();
            switch (mode) {
                case "detailed register" -> {
                    if(selectNum == 0) {
                        display.registerMain();
                    }else{
                        if(selectNum - 1 >= display.getJapaneseOrder().length) {
                            display.selectError();
                            display.registerMain();
                        }else{
                            display.setSelectRowNum(selectNum);
                            display.detailedRegisterUma(selectNum);
                        }
                    }
                }
                case "detailed register row" -> {
                    if(selectNum == 0) {
                        display.registerUma("success");
                    }else{
                        if(selectNum - 1 >= display.getSelectColumn().length) {
                            returnMenu(0);
                        }else{
                            display.setSelectColumnNum(selectNum - 1);
                            display.registerUma(selectNum - 1);
                        }
                    }
                }
                case "selected" -> {
                    if(selectNum == 0) {
                        display.registerUma("success");
                    }else{
                        if(selectNum - 1 >= display.getSelectUmas().length) {
                            returnMenu(0);
                        }else{
                            if(display.setName("develop", selectNum)) {
                                display.registerUma("father");
                            }else{
                                //重複検出
                                display.registerUma("success");
                            }
                        }
                    }
                }
            }
        }catch (Exception e) {
            display.intError();
        }
    }

    public void fatherUma(String mode) {
        Scanner scanner = new Scanner(System.in);
        try {
            int selectNum = scanner.nextInt();
            switch (mode) {
                case "register row" -> {
                    if(selectNum == 0) {
                        display.registerUma("success");
                    }else{
                        if(selectNum - 1 >= display.getJapaneseOrder().length) {
                            returnMenu(1);
                        }else{
                            display.setSelectRowNum(selectNum);
                            display.fatherRegisterRow(selectNum);
                        }
                    }
                }
                case "register column" -> {
                    if(selectNum == 0) {
                        returnMenu(1);
                    }else{
                        if(selectNum - 1 >= display.getSelectColumn().length) {
                            returnMenu(1);
                        }else{
                            display.setSelectColumnNum(selectNum - 1);
                            display.registerFather(selectNum - 1);
                        }
                    }
                }
                case "selected" -> {
                    if(selectNum == 0) {
                        returnMenu(1);
                    }else{
                        if(display.setName("father", selectNum)) {
                            display.registerUma("mother");
                        }else{
                            display.registerUma("father");
                        }
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            display.intError();
        }
    }

    public void motherUma(String mode) {
        Scanner scanner = new Scanner(System.in);
        try {
            int selectNum = scanner.nextInt();
            switch (mode) {
                case "register row" -> {
                    if(selectNum == 0) {
                        display.registerUma("father");
                    }else{
                        if(selectNum - 1 >= display.getJapaneseOrder().length) {
                            returnMenu(2);
                        }else{
                            display.setSelectRowNum(selectNum);
                            display.motherRegisterRow(selectNum);
                        }
                    }
                }
                case "register column" -> {
                    if(selectNum == 0) {
                        returnMenu(1);
                    }else{
                        if(selectNum - 1 >= display.getSelectColumn().length) {
                            returnMenu(2);
                        }else{
                            display.setSelectColumnNum(selectNum - 1);
                            display.registerMother(selectNum - 1);
                        }
                    }
                }
                case "selected" -> {
                    if(selectNum == 0) {
                        returnMenu(2);
                    }else{
                        if(display.setName("mother", selectNum)) {
                            display.registerUma("paternal father");
                        }else{
                            display.registerUma("mother");
                        }
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            display.intError();
        }
    }

    public void paternalFatherUma(String mode) {
        Scanner scanner = new Scanner(System.in);
        try {
            int selectNum = scanner.nextInt();
            switch (mode) {
                case "register row" -> {
                    if(selectNum == 0) {
                        display.registerUma("mother");
                    }else{
                        if(selectNum - 1 >= display.getJapaneseOrder().length) {
                            returnMenu(3);
                        }else{
                            display.setSelectRowNum(selectNum);
                            display.paternalFatherRegisterRow(selectNum);
                        }
                    }
                }
                case "register column" -> {
                    if(selectNum == 0) {
                        returnMenu(1);
                    }else{
                        if(selectNum - 1 >= display.getSelectColumn().length) {
                            returnMenu(3);
                        }else{
                            display.setSelectColumnNum(selectNum - 1);
                            display.registerPaternalFather(selectNum - 1);
                        }
                    }
                }
                case "selected" -> {
                    if(selectNum == 0) {
                        returnMenu(3);
                    }else{
                        if(display.setName("paternal father", selectNum)) {
                            display.registerUma("paternal mother");
                        }else{
                            display.registerUma("paternal father");
                        }
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            display.intError();
        }
    }

    public void returnMenu(int type) {
        display.selectError();
        switch (type) {
            case 0 -> display.registerUma("success");
            case 1 -> display.registerUma("father");
            case 2 -> display.registerUma("mother");
            case 3 -> display.registerUma("paternal father");
            case 4 -> display.registerUma("paternal mother");
            case 5 -> display.registerUma("maternal father");
            case 6 -> display.registerUma("maternal mother");
        }
    }

}
