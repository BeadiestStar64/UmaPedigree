package umapedigree.beadieststar64.com.github.Display;

public class EscapeColor {

    public String getRED() {
        return "\u001B[31m";
    }
    public String getRESET() {
        return "\u001B[0m";
    }
    public String getYELLOW() {
        return "\u001B[33m";
    }
    public String getFlash() {
        return "\033[H\033[2J";
    }
    public String getGREEN() {return "\u001B[32m";}
    public String getBLUE() {return "\u001B[34m";}
    public String getCYAN() {return "\u001B[36m";}
    public String getMagenta() {return "\u001B[35m";}
}
