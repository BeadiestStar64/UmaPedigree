package umapedigree.beadieststar64.com.github.FileSys;

import umapedigree.beadieststar64.com.github.Display.mainDisplay;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class EULA {

    private static boolean EULABool;
    private static File file;

    public static boolean isEULABool() {
        return EULABool;
    }
    public static void setEULABool(boolean EULABool) {
        EULA.EULABool = EULABool;
    }

    public static File getFile() {
        return file;
    }
    public static void setFile(File file) {
        EULA.file = file;
    }

    public boolean create() {
        try {
            Path path = Paths.get("").toAbsolutePath();
            FileSys sys = new FileSys();
            setFile(sys.createFile("non", path, "EULA", "txt"));
            if(!getFile().exists()) {
                if(getFile().createNewFile()) {
                    try (OutputStream out = new FileOutputStream(getFile());
                         OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8)){
                        Date date = new Date();
                        writer.write("#By changing the setting below to TRUE you are indicating your agreement to our EULA (https://aka.ms/MinecraftEULA).");
                        writer.write("\r#" + date);
                        writer.write("\reula=false");
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    setEULABool(true);
                }
            }else{
                setEULABool(false);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return isEULABool();
    }

    public boolean check() {
        try (BufferedReader input = new BufferedReader(new FileReader(getFile()))) {
            String data;
            while ((data = input.readLine()) != null) {
                if(data.contains("eula")) {
                    String[] strings = data.split("=");
                    return strings[1].equalsIgnoreCase("true");
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
