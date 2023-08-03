package umapedigree.beadieststar64.com.github.Display;

import umapedigree.beadieststar64.com.github.Receptor.debugReceptor;
import umapedigree.beadieststar64.com.github.UmaPedigree;

public class debugDisplay extends UmaPedigree {

    private static final EscapeColor escape = new EscapeColor();
    private static final debugReceptor debugReceptor = new debugReceptor();

    private final String debug = "[" + escape.getRED() + "UmaPedigree Debug" + escape.getRESET() + "]";

    public void clear() {
        System.out.print(escape.getFlash());
        System.out.flush();
    }

    public void debugTime(long time) {
        if(time != 0) {
            clear();
            System.out.println(debug + "処理時間: " + time + "ms");
        }
    }

    public void entrance() {
        clear();
        System.out.println(escape.getRED() + "===== Warning!! =====" + escape.getRESET());
        System.out.println("Are you sure you want to activate Debug Mode? Debug Mode restricts some functions!");
        System.out.println(escape.getRESET() + "- " + escape.getRED() + "Database does not work in Debug Mode.");
        System.out.println(escape.getRESET() + "- " + escape.getRED() + "Debug Mode displays a large number of logs. This can result in a heavy PC load.");
        System.out.println(escape.getRED() + "\nIf you want to activate Debug Mode, please type " + escape.getRESET() + "\"debug warning accept\"" + escape.getRED() + ".");
        debugReceptor.acceptDebug();
    }

    public void registerMessage(String register) {
        System.out.println(debug + register + "を登録しました。");
    }

    public void duplicationMessage() {
        System.out.println(debug + "重複検知");
    }

}
