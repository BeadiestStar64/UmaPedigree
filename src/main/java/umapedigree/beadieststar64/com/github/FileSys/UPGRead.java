package umapedigree.beadieststar64.com.github.FileSys;


import umapedigree.beadieststar64.com.github.Display.mainDisplay;
import umapedigree.beadieststar64.com.github.UmaPedigree;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class UPGRead extends UmaPedigree {

    private static final ArrayList<ArrayList<String>> umaList = new ArrayList<>();
    private static final ArrayList<ArrayList<String>> groundGroup = new ArrayList<>();
    private static final ArrayList<ArrayList<String>> distanceGroup = new ArrayList<>();
    private static final ArrayList<ArrayList<String>> legsGroup = new ArrayList<>();
    private static final ArrayList<ArrayList<String>> uniqueSkillGroup = new ArrayList<>();

    private static final ArrayList<ArrayList<String>> skillFactorNameGroup = new ArrayList<>();

    private static final ArrayList<String> racesList = new ArrayList<>();

    public static ArrayList<ArrayList<String>> getUmaList() {return umaList;}
    public static ArrayList<ArrayList<String>> getGroundGroup() {return groundGroup;}
    public static ArrayList<ArrayList<String>> getDistanceGroup() {return distanceGroup;}
    public static ArrayList<ArrayList<String>> getLegsGroup() {return legsGroup;}
    public static ArrayList<ArrayList<String>> getUniqueSkillGroup() {return uniqueSkillGroup;}

    public static ArrayList<ArrayList<String>> getSkillFactorNameGroup() {return skillFactorNameGroup;}

    public static ArrayList<String> getRacesList() {return racesList;}

    public void INIT() {
        Path path = Paths.get("").toAbsolutePath();
        FileSys sys = new FileSys();
        String[] fileNames = {"UmaName", "FactorSkillName", "Races"};
        for(String fileName : fileNames) {
            File file = sys.createFile("upg", path, fileName, "upg");
            if(!file.exists()) {
                String resource = "/" + fileName + ".upg";
                String target = file.toString();
                sys.copyResource(resource, target);
            }
        }

        setDisplay(new mainDisplay());
        getDisplay().prepareSys();
        read();
        readPassiveSkill();
        sqlSetupRead();
        readRaces();
        getDisplay().preparedSys();
    }

    public void read() {
        debugMethod(false);
        Path path = Paths.get("").toAbsolutePath();
        File file = new File(path + File.separator + "upg" + File.separator + "UmaName.upg");
        try (InputStream input = new FileInputStream(file);
             InputStreamReader iReader = new InputStreamReader(input, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(iReader)){

            String data;
            int column = 0;
            int beforeColum = 0;
            String ground;
            String distance;
            String legs;
            String uniqueSkill;

            ArrayList<String> nameList = new ArrayList<>();
            ArrayList<String> groundList = new ArrayList<>();
            ArrayList<String> distanceList = new ArrayList<>();
            ArrayList<String> legsList = new ArrayList<>();
            ArrayList<String> uniqueSkillList = new ArrayList<>();

            while ((data = reader.readLine()) != null) {
                String[] strings = data.split(" - ");
                if (strings[0].charAt(0) == '#') { //メッセージ
                    versionCheck();
                    continue;
                }else if(strings[0].charAt(0) == '*') { //未実装
                    if(isDebug()) {
                        System.out.println("未実装ウマ娘:" + strings[0]);
                    }
                    continue;
                }

                String name = strings[0];
                column = Integer.parseInt(strings[1]);
                ground = strings[2];
                distance = strings[3];
                legs = strings[4];
                uniqueSkill = strings[5];

                if(column > beforeColum) {
                    beforeColum = updateList(column, nameList, groundList, distanceList, legsList, uniqueSkillList);
                    nameList = new ArrayList<>();
                    groundList = new ArrayList<>();
                    distanceList = new ArrayList<>();
                    legsList = new ArrayList<>();
                    uniqueSkillList = new ArrayList<>();
                }
                nameList.add(name);
                groundList.add(ground);
                distanceList.add(distance);
                legsList.add(legs);
                uniqueSkillList.add(uniqueSkill);
            }
            updateList(column, nameList, groundList, distanceList, legsList, uniqueSkillList);
        }catch (Exception e) {
            e.printStackTrace();
        }
        getDisplay().debugSys(debugMethod(true));
    }

    public void readPassiveSkill() {
        debugMethod(false);
        Path path = Paths.get("").toAbsolutePath();
        File file = new File(path + File.separator + "upg" + File.separator + "FactorSkillName.upg");
        try (InputStream input = new FileInputStream(file);
             InputStreamReader iReader = new InputStreamReader(input, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(iReader)){

            String data;
            int before = 0;
            ArrayList<String> passiveSkills = new ArrayList<>();
            ArrayList<String> speedSkills = new ArrayList<>();
            ArrayList<String> accelerateSkills = new ArrayList<>();
            ArrayList<String> healthSkills = new ArrayList<>();
            ArrayList<String> debuffSkills = new ArrayList<>();
            ArrayList<String> gateSkills = new ArrayList<>();
            ArrayList<String> positionSkills = new ArrayList<>();
            ArrayList<String> fovSkills = new ArrayList<>();

            while ((data = reader.readLine()) != null) {
                String[] strings = data.split(" - ");
                if(strings[0].charAt(0) == '#') {
                    versionCheck();
                    continue;
                }

                String skillName = strings[0];
                int skillTypeNum = Integer.parseInt(strings[1]);
                String skillTargetTypeNum = strings[2];

                switch (skillTypeNum) {
                    case 1 -> passiveSkills.add(skillName); //能力上昇
                    case 2 -> speedSkills.add(skillName);
                    case 3 -> accelerateSkills.add(skillName);
                    case 4 -> healthSkills.add(skillName);
                    case 5 -> debuffSkills.add(skillName);
                    case 6 -> gateSkills.add(skillName);
                    case 7 -> positionSkills.add(skillName);
                    case 8 -> fovSkills.add(skillName);
                }

            }
            getSkillFactorNameGroup().add(0, passiveSkills);
            getSkillFactorNameGroup().add(1, speedSkills);
            getSkillFactorNameGroup().add(2, accelerateSkills);
            getSkillFactorNameGroup().add(3, healthSkills);
            getSkillFactorNameGroup().add(4, debuffSkills);
            getSkillFactorNameGroup().add(5, gateSkills);
            getSkillFactorNameGroup().add(6, positionSkills);
            getSkillFactorNameGroup().add(7, fovSkills);

        }catch (Exception e) {
            e.printStackTrace();
        }
        getDisplay().debugSys(debugMethod(true));
    }

    public void readRaces() {
        debugMethod(false);
        Path path = Paths.get("").toAbsolutePath();
        File file = new File(path + File.separator + "upg" + File.separator + "Races.upg");
        try (InputStream input = new FileInputStream(file);
             InputStreamReader iReader = new InputStreamReader(input, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(iReader)) {

            String data;
            String raceName;
            String grade = "";
            String openYear = "";
            String openDate;
            String openPlace = "";
            String raceTrack;
            int distance;
            String courseType = "";
            String season = "";
            String addLisData = "";

            while ((data = reader.readLine()) != null) {
                String[] strings = data.split(" - ");
                if(strings[0].charAt(0) == '#') {
                    versionCheck();
                    continue;
                }

                raceName = strings[0];

                switch (Integer.parseInt(strings[1])) {
                    case 1 -> grade = "G1";
                    case 2 -> grade = "G2";
                    case 3 -> grade = "G3";
                    case 4 -> grade = "OP";
                    case 5 -> grade = "Pre-OP";
                }

                switch (Integer.parseInt(strings[2])) {
                    case 1 -> openYear = "ジュニア";
                    case 2 -> openYear = "クラシック";
                    case 3 -> openYear = "シニア";
                    case 4 -> openYear = "クラシック・シニア";
                }

                String addOpenDate = "";
                if(Integer.parseInt(strings[4]) == 1) {
                    addOpenDate = "前半";
                }else{
                    addOpenDate = "後半";
                }
                openDate = strings[3] + "月" + addOpenDate;

                switch (Integer.parseInt(strings[5])) {
                    //1-東京, 2-中山, 3-札幌, 4-大井, 5-阪神, 6-小倉, 7-京都, 8-函館, 9-福島, 10-新潟, 11-中京, 12-川崎, 13-船橋, 14-盛岡
                    case 1 -> openPlace = "東京";
                    case 2 -> openPlace = "中山";
                    case 3 -> openPlace = "札幌";
                    case 4 -> openPlace = "大井";
                    case 5 -> openPlace = "阪神";
                    case 6 -> openPlace = "小倉";
                    case 7 -> openPlace = "京都";
                    case 8 -> openPlace = "函館";
                    case 9 -> openPlace = "福島";
                    case 10 -> openPlace = "新潟";
                    case 11 -> openPlace = "中京";
                    case 12 -> openPlace = "川崎";
                    case 13 -> openPlace = "船橋";
                    case 14 -> openPlace = "盛岡";
                }

                if(Integer.parseInt(strings[6]) == 1) {
                    raceTrack = "芝";
                }else{
                    raceTrack = "ダート";
                }

                distance = Integer.parseInt(strings[7]);

                switch (Integer.parseInt(strings[8])) {
                    //1-右, 2-左, 3-右内, 4-左内, 5-右外, 6-左外, 7-右外->内
                    case 1 -> courseType = "右";
                    case 2 -> courseType = "左";
                    case 3 -> courseType = "右内";
                    case 4 -> courseType = "左内";
                    case 5 -> courseType = "右外";
                    case 6 -> courseType = "左外";
                    case 7 -> courseType = "右外->内";
                }

                switch (Integer.parseInt(strings[9])) {
                    case 1 -> season = "春";
                    case 2 -> season = "夏";
                    case 3 -> season = "秋";
                    case 4 -> season = "冬";
                }

                addLisData = raceName + "[" + grade + "] 開催時期:" + openYear + openDate + " " + distance + "m " + raceTrack + " " + openPlace + " " + season + " " + courseType;
                getRacesList().add(addLisData);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        getDisplay().debugSys(debugMethod(true));
    }

    public void sqlSetupRead() {
        if(!getSql().existence()) {
            debugMethod(false);
            getSql().writeGroupData(getUmaList(), getGroundGroup(), getDistanceGroup(), getLegsGroup(), getUniqueSkillGroup());
            getDisplay().debugSys(debugMethod(true));
        }
    }

    public int updateList(int column, ArrayList<String> nameList, ArrayList<String> groundList, ArrayList<String> distanceList, ArrayList<String> legsList, ArrayList<String> uniqueSkillList) {
        umaList.add(nameList);
        groundGroup.add(groundList);
        distanceGroup.add(distanceList);
        legsGroup.add(legsList);
        uniqueSkillGroup.add(uniqueSkillList);
        return column;
    }

    public void inputTxt(String path) {
        FileSys sys = new FileSys();
        ArrayList<ArrayList<String>> list = sys.readFile(path);

        String umaName;
        String blueFactorName;
        int blueFactorLevel;
        String redFactorName;
        int redFactorLevel;
        String uniqueFactorName;
        int uniqueFactorLevel;
        ArrayList<String> skillFactorName;
        ArrayList<Integer> skillFactorLevel;
        int evaluationPoint;
        int lifetimeRecord_All;
        int lifetimeRecord_AllWin;
        int lifetimeRecord_G1;
        int lifetimeRecord_G1Name;
        int fans;
        String winningHistory;
        String scenario;

        int i = 0;
        for(ArrayList<String> array : list) {
            for(String str : array) {
                String[] strings = str.split("\\[");
                System.out.println(Arrays.toString(strings));
            }
        }
    }

    public void versionCheck() {

    }

}
