package umapedigree.beadieststar64.com.github.FileSys;

import umapedigree.beadieststar64.com.github.Display.mainDisplay;
import umapedigree.beadieststar64.com.github.Display.parentDisplay;
import umapedigree.beadieststar64.com.github.UmaPedigree;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;

public class SQLite {
    private String URL;
    private String groupURL;
    private String recordURL;
    private Connection con;
    private Connection groupCon;
    private Connection recordCon;

    private static final mainDisplay display = new mainDisplay();
    private static final parentDisplay parent = new parentDisplay();

    public String getURL() {return URL;}
    public void setURL(String URL) {this.URL = URL;}
    public String getGroupURL() {return groupURL;}
    public void setGroupURL(String URL) {groupURL = URL;}
    public String getRecordURL() {return recordURL;}
    public void setRecordURL(String URL) {recordURL = URL;}

    public Connection getCon() {return con;}
    public void setCon(Connection con) {this.con = con;}
    public Connection getGroupCon() {return groupCon;}
    public void setGroupCon(Connection con) {this.groupCon = con;}
    public Connection getRecordCon() {return recordCon;}
    public void setRecordCon(Connection con) {this.recordCon = con;}


    public void init() {
        try {
            FileSys sys = new FileSys();
            File directory = sys.createFolder("Database");
            if(!directory.isDirectory()) {
                if(directory.mkdirs()) {
                    System.out.println("Databaseフォルダの作成に成功しました。");
                }else{
                    System.out.println("Databaseフォルダの作成に失敗しました!");
                }
            }

            setURL("jdbc:sqlite:" + directory + File.separator + "data.db");
            setGroupURL("jdbc:sqlite:" + directory + File.separator + "uma.db");
            setRecordURL("jdbc:sqlite:" + directory + File.separator + "record.db");
            setCon(DriverManager.getConnection(getURL()));
            setGroupCon(DriverManager.getConnection(getGroupURL()));
            setRecordCon(DriverManager.getConnection(getRecordURL()));

            try (Statement stmt = getCon().createStatement();
                Statement umaStmt = getGroupCon().createStatement();
                Statement recordStmt = getRecordCon().createStatement()) {
                stmt.setQueryTimeout(30);
                getCon().setAutoCommit(false);
                umaStmt.setQueryTimeout(30);
                getGroupCon().setAutoCommit(false);
                recordStmt.setQueryTimeout(30);
                getRecordCon().setAutoCommit(false);

                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS PLAYER_DATA(" +
                        "DEVELOPED_UMAMUSUME" +
                        ", FATHER_UMAMUSUME" +
                        ", MOTHER_UMAMUSUME" +
                        ", PATERNAL_FATHER_UMAMUSUME" +
                        ", PATERNAL_MOTHER_UMAMUSUME" +
                        ", MATERNAL_FATHER_UMAMUSUME" +
                        ", MATERNAL_MOTHER_UMAMUSUME" +
                        ", FATHER_BLUE_FACTOR_NAME" +
                        ", FATHER_RED_FACTOR_NAME" +
                        ", FATHER_SKILL_FACTOR_NAME" +
                        ", MOTHER_BLUE_FACTOR_NAME" +
                        ", MOTHER_RED_FACTOR_NAME" +
                        ", MOTHER_SKILL_FACTOR_NAME" +
                        ", PATERNAL_FATHER_BLUE_FACTOR_NAME" +
                        ", PATERNAL_FATHER_RED_FACTOR_NAME" +
                        ", PATERNAL_FATHER_SKILL_FACTOR_NAME" +
                        ", PATERNAL_MOTHER_BLUE_FACTOR_NAME" +
                        ", PATERNAL_MOTHER_RED_FACTOR_NAME" +
                        ", PATERNAL_MOTHER_SKILL_FACTOR_NAME" +
                        ", MATERNAL_FATHER_BLUE_FACTOR_NAME" +
                        ", MATERNAL_FATHER_RED_FACTOR_NAME" +
                        ", MATERNAL_FATHER_SKILL_FACTOR_NAME" +
                        ", MATERNAL_MOTHER_BLUE_FACTOR_NAME" +
                        ", MATERNAL_MOTHER_RED_FACTOR_NAME" +
                        ", MATERNAL_MOTHER_SKILL_FACTOR_NAME" +
                        ", FATHER_BLUE_FACTOR_LEVEL" +
                        ", FATHER_RED_FACTOR_LEVEL" +
                        ", FATHER_SKILL_FACTOR_LEVEL" +
                        ", MOTHER_BLUE_FACTOR_LEVEL" +
                        ", MOTHER_RED_FACTOR_LEVEL" +
                        ", MOTHER_SKILL_FACTOR_LEVEL" +
                        ", PATERNAL_FATHER_BLUE_FACTOR_LEVEL" +
                        ", PATERNAL_FATHER_RED_FACTOR_LEVEL" +
                        ", PATERNAL_FATHER_SKILL_FACTOR_LEVEL" +
                        ", PATERNAL_MOTHER_BLUE_FACTOR_LEVEL" +
                        ", PATERNAL_MOTHER_RED_FACTOR_LEVEL" +
                        ", PATERNAL_MOTHER_SKILL_FACTOR_LEVEL" +
                        ", MATERNAL_FATHER_BLUE_FACTOR_LEVEL" +
                        ", MATERNAL_FATHER_RED_FACTOR_LEVEL" +
                        ", MATERNAL_FATHER_SKILL_FACTOR_LEVEL" +
                        ", MATERNAL_MOTHER_BLUE_FACTOR_LEVEL" +
                        ", MATERNAL_MOTHER_RED_FACTOR_LEVEL" +
                        ", MATERNAL_MOTHER_SKILL_FACTOR_LEVEL" +
                        ", SPEED_LIMIT" +
                        ", SPEED_VALUE" +
                        ", STAMINA_LIMIT" +
                        ", STAMINA_VALUE" +
                        ", POWER_LIMIT" +
                        ", POWER_VALUE" +
                        ", GUTS_LIMIT" +
                        ", GUTS_VALUE" +
                        ", INTELLIGENCE_LIMIT" +
                        ", INTELLIGENCE_VALUE" +
                        ", SPRINT_LEVEL" +
                        ", MILE_LEVEL" +
                        ", MIDDLE_DISTANCE_LEVEL" +
                        ", STAYER_LEVEL" +
                        ", DIRT_LEVEL" +
                        ", TURF_LEVEL" +
                        ", SKILL_NAME" +
                        ", SKILL_LEVEL" +
                        ");");

                umaStmt.executeUpdate("CREATE TABLE IF NOT EXISTS UMA_NAME(" +
                        "NAME" +
                        ", APTITUDE_GROUND" +
                        ", APTITUDE_DISTANCE" +
                        ", APTITUDE_LEGS" +
                        ", UNIQUE_SKILL" +
                        ", WIN_G1" +
                        ");");

                recordStmt.executeUpdate("CREATE TABLE IF NOT EXISTS RECORD(" +
                        "NAME" +
                        ", BLUE_FACTOR_NAME" +
                        ", BLUE_FACTOR_LEVEL" +
                        ", RED_FACTOR_NAME" +
                        ", RED_FACTOR_LEVEL" +
                        ", GREEN_FACTOR_NAME" +
                        ", GREEN_FACTOR_LEVEL" +
                        ", SKILL_FACTORS_NAME" +
                        ", SKILL_FACTORS_LEVEL" +
                        ", EVALUATION_POINT" +
                        ", LIFETIME_RECORD_ALL" +
                        ", LIFETIME_RECORD_WIN" +
                        ", LIFETIME_RECORD_G1WINS_NUM" +
                        ", LIFETIME_RECORD_G1WINS_NAME" +
                        ", FANS" +
                        ", WINNING_HISTORY" +
                        ", SCENARIO" +
                        ");");

                getCon().commit();
                getGroupCon().commit();
                getRecordCon().commit();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized boolean existence() {
        try (Statement stmt = getGroupCon().createStatement()){
            stmt.setQueryTimeout(30);
            ResultSet rs = stmt.executeQuery("SELECT * FROM UMA_NAME LIMIT 1;");
            getGroupCon().commit();
            if(rs.next()) {
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public synchronized void writeGroupData(ArrayList<ArrayList<String>> allList, ArrayList<ArrayList<String>> groundList, ArrayList<ArrayList<String>> distanceList, ArrayList<ArrayList<String>> legsList, ArrayList<ArrayList<String>> uniqueSkillList) {
        try {
            PreparedStatement prepStmt = getGroupCon().prepareStatement("INSERT INTO UMA_NAME(" +
                    "NAME" +
                    ", APTITUDE_GROUND" +
                    ", APTITUDE_DISTANCE" +
                    ", APTITUDE_LEGS" +
                    ", UNIQUE_SKILL" +
                    ") VALUES (" +
                    "?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ");");

            boolean writeBool = false;

            for(int i = 0; i < allList.size(); i++) {
                ArrayList<String> list = allList.get(i);
                for(int l = 0; l < list.size(); l++) {
                    if(i == allList.size() - 1 && l == list.size() - 1) {
                        writeBool = true;
                    }
                    prepStmt.setString(1, list.get(l));
                    prepStmt.setString(2, groundList.get(i).get(l));
                    prepStmt.setString(3, distanceList.get(i).get(l));
                    prepStmt.setString(4, legsList.get(i).get(l));
                    prepStmt.setString(5, new String(uniqueSkillList.get(i).get(l).getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
                    prepStmt.addBatch();

                    if(writeBool) {
                        display.sqlSys(1);
                        prepStmt.executeBatch();
                        getGroupCon().commit();
                        if(UmaPedigree.isDebug()) {
                            try (Statement stmt = getGroupCon().createStatement()){
                                stmt.setQueryTimeout(30);
                                getGroupCon().setAutoCommit(false);
                                ResultSet rs = stmt.executeQuery("SELECT * FROM UMA_NAME;");
                                while (rs.next()) {
                                    System.out.println(rs.getString("NAME"));
                                    System.out.println(rs.getString("APTITUDE_GROUND"));
                                    System.out.println(rs.getString("APTITUDE_DISTANCE"));
                                    System.out.println(rs.getString("APTITUDE_LEGS"));
                                    System.out.println(rs.getString(new String("UNIQUE_SKILL".getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8)));
                                }
                            }
                        }
                        prepStmt.close();
                        display.sqlSys(2);
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void writeRecord(String umamusumeName,
                                         String blueFactorName, int blueFactorLevel,
                                         String redFactorName, int redFactorLevel,
                                         String uniqueFactorName, int uniqueFactorLevel,
                                         ArrayList<String> skillFactorNames, ArrayList<Integer> skillFactorLevels,
                                         int evaluationPoint,
                                         int lifetimeRecordAll, int lifetimeRecordAllWin, int lifetimeRecordG1s, ArrayList<String> lifetimeRecordG1,
                                         int collectedFans, String winingHistory, String scenario) {
        try  {

            String skillFactorsName = skillFactorNames.toString();
            String skillFactorsNum = skillFactorLevels.toString();
            String G1Names = lifetimeRecordG1.toString();

            PreparedStatement prepStmt = getRecordCon().prepareStatement("INSERT INTO RECORD(" +
                    "NAME" +
                    ", BLUE_FACTOR_NAME" +
                    ", BLUE_FACTOR_LEVEL" +
                    ", RED_FACTOR_NAME" +
                    ", RED_FACTOR_LEVEL" +
                    ", GREEN_FACTOR_NAME" +
                    ", GREEN_FACTOR_LEVEL" +
                    ", SKILL_FACTORS_NAME" +
                    ", SKILL_FACTORS_LEVEL" +
                    ", EVALUATION_POINT" +
                    ", LIFETIME_RECORD_ALL" +
                    ", LIFETIME_RECORD_WIN" +
                    ", LIFETIME_RECORD_G1WINS_NUM" +
                    ", LIFETIME_RECORD_G1WINS_NAME" +
                    ", FANS" +
                    ", WINNING_HISTORY" +
                    ", SCENARIO" +
                    ") VALUES (" +
                    "?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ");");

            prepStmt.setString(1, umamusumeName);
            prepStmt.setString(2, blueFactorName);
            prepStmt.setInt(3, blueFactorLevel);
            prepStmt.setString(4, redFactorName);
            prepStmt.setInt(5, redFactorLevel);
            prepStmt.setString(6, uniqueFactorName);
            prepStmt.setInt(7, uniqueFactorLevel);
            prepStmt.setString(8, skillFactorsName);
            prepStmt.setString(9, skillFactorsNum);
            prepStmt.setInt(10, evaluationPoint);
            prepStmt.setInt(11, lifetimeRecordAll);
            prepStmt.setInt(12, lifetimeRecordAllWin);
            prepStmt.setInt(13, lifetimeRecordG1s);
            prepStmt.setString(14, G1Names);
            prepStmt.setInt(15, collectedFans);
            prepStmt.setString(16, "winingHistory");
            prepStmt.setString(17, scenario);

            prepStmt.executeUpdate();
            getRecordCon().commit();
            prepStmt.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void writeData(ArrayList<String> umamusumeName,
                                       ArrayList<String> blueFactorName, ArrayList<Integer> blueFactorLevel,
                                       ArrayList<String> redFactorName, ArrayList<Integer> redFactorLevel,
                                       ArrayList<String> uniqueFactorName, ArrayList<Integer> uniqueFactorLevel,
                                       ArrayList<ArrayList<String>> skillFactorNames, ArrayList<ArrayList<Integer>> skillFactorLevels,
                                       ArrayList<Integer> evaluationPoint,
                                       ArrayList<Integer> lifetimeRecordAll, ArrayList<Integer> lifetimeRecordAllWin, ArrayList<Integer> lifetimeRecordG1s, ArrayList<ArrayList<String>> lifetimeRecordG1,
                                       ArrayList<Integer> collectedFans, ArrayList<String> winingHistory, ArrayList<String> scenario) {

        try {
            PreparedStatement prepStmt = getRecordCon().prepareStatement("INSERT INTO RECORD(" +
                    "NAME" +
                    ", BLUE_FACTOR_NAME" +
                    ", BLUE_FACTOR_LEVEL" +
                    ", RED_FACTOR_NAME" +
                    ", RED_FACTOR_LEVEL" +
                    ", GREEN_FACTOR_NAME" +
                    ", GREEN_FACTOR_LEVEL" +
                    ", SKILL_FACTORS_NAME" +
                    ", SKILL_FACTORS_LEVEL" +
                    ", EVALUATION_POINT" +
                    ", LIFETIME_RECORD_ALL" +
                    ", LIFETIME_RECORD_WIN" +
                    ", LIFETIME_RECORD_G1WINS_NUM" +
                    ", LIFETIME_RECORD_G1WINS_NAME" +
                    ", FANS" +
                    ", WINNING_HISTORY" +
                    ", SCENARIO" +
                    ") VALUES (" +
                    "?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ", ?" +
                    ");");

            for(int i = 0, l = umamusumeName.size(); i < l; i++) {
                String skillFactorsName = skillFactorNames.get(i).toString();
                String skillFactorsNum = skillFactorLevels.get(i).toString();
                String G1Names = lifetimeRecordG1.get(i).toString();

                prepStmt.setString(1, umamusumeName.get(i));
                prepStmt.setString(2, blueFactorName.get(i));
                prepStmt.setInt(3, blueFactorLevel.get(i));
                prepStmt.setString(4, redFactorName.get(i));
                prepStmt.setInt(5, redFactorLevel.get(i));
                prepStmt.setString(6, uniqueFactorName.get(i));
                prepStmt.setInt(7, uniqueFactorLevel.get(i));
                prepStmt.setString(8, skillFactorsName);
                prepStmt.setString(9, skillFactorsNum);
                prepStmt.setInt(10, evaluationPoint.get(i));
                prepStmt.setInt(11, lifetimeRecordAll.get(i));
                prepStmt.setInt(12, lifetimeRecordAllWin.get(i));
                prepStmt.setInt(13, lifetimeRecordG1s.get(i));
                prepStmt.setString(14, G1Names);
                prepStmt.setInt(15, collectedFans.get(i));
                prepStmt.setString(16, winingHistory.get(i));
                prepStmt.setString(17, scenario.get(i));
                prepStmt.addBatch();

                if(i == l - 1) {
                    prepStmt.executeBatch();
                    getRecordCon().commit();
                    prepStmt.close();
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized ArrayList<ArrayList<String>> readRecord() {
        String sql = "SELECT * FROM RECORD;";
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        try (Statement stmt = getRecordCon().createStatement()){
            stmt.setQueryTimeout(30);
            getRecordCon().setAutoCommit(false);
            try (ResultSet rs = stmt.executeQuery(sql)){
                while (rs.next()) {
                    StringBuilder sb = new StringBuilder();
                    ArrayList<String> array = new ArrayList<>();
                    for(int i = 1; i < 18; i++) {
                        if(i == 1) {
                            sb.append(rs.getString(i));
                            continue;
                        }
                        sb.append(", ").append(rs.getString(i));
                    }
                    array.add(sb.toString());
                    list.add(array);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized String getUniqueSkill(String name) {
        String uniqueSkillName = "";
        try (PreparedStatement prepStmt = getGroupCon().prepareStatement("SELECT UNIQUE_SKILL FROM UMA_NAME WHERE NAME = ?;")){
            prepStmt.setString(1, name);

            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                uniqueSkillName = rs.getString("UNIQUE_SKILL");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return uniqueSkillName;
    }

}
