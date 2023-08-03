package umapedigree.beadieststar64.com.github;

import umapedigree.beadieststar64.com.github.Display.mainDisplay;
import umapedigree.beadieststar64.com.github.FileSys.BatCreator;
import umapedigree.beadieststar64.com.github.FileSys.EULA;
import umapedigree.beadieststar64.com.github.FileSys.SQLite;
import umapedigree.beadieststar64.com.github.FileSys.UPGRead;

import java.util.Arrays;

public class UmaPedigree {

    private static boolean debug;

    private static UPGRead read;
    private static SQLite sql;
    private static mainDisplay display;
    private static long startTime;

    public static long getStartTime() {return startTime;}
    public static void setStartTime(long time) {startTime = time;}

    public static boolean isDebug() {return debug;}
    public static void setDebug(boolean bool) {debug = bool;}

    public static UPGRead getRead() {return read;}
    public static void setRead(UPGRead program) {read = program;}

    public static SQLite getSql() {return sql;}
    public static void setSql(SQLite program) {sql = program;}

    public static mainDisplay getDisplay() {return display;}
    public static void setDisplay(mainDisplay program) {display = program;}

    public static void main(String[] args) {
        EULA eula = new EULA();
        if(!eula.create() && eula.check()) {
            //実行
            updateCheck();
            mainDisplay display = new mainDisplay();
            if(modeSwitch(args)) {
                //apiモード
                display.apiSys();
            }else{
                //jarモード+
            }
            init();
            BatCreator bat = new BatCreator();
            bat.create();
            display.welcome();
        }else{
            //EULAエラー
            mainDisplay display = new mainDisplay();
            display.EULAError();
        }
    }

    public static void init() {
        setSql(new SQLite());
        getSql().init();
        setRead(new UPGRead());
        getRead().INIT();
    }

    protected static boolean modeSwitch(String[] args) {
        String arg = Arrays.toString(args);
        String s = "[]";
        if(!arg.equalsIgnoreCase(s)) {
            String[] str = arg.split(" ");
            for(String string : str) {
                if(string.contains("debug")) {
                    setDebug(true);
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }
    }

    public static long debugMethod(boolean bool) {
        if(isDebug()) {
            try {
                if(bool) {
                    long endTime = System.currentTimeMillis();
                    return (endTime - getStartTime());
                }else{
                    setStartTime(System.currentTimeMillis());
                    return 0;
                }
            }catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }else{
            return 0;
        }
    }

    public static void updateCheck() {
        String url = "";
    }
}