package umapedigree.beadieststar64.com.github.FileSys;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BatCreator {

    public void create() {
        try {
            Path path = Paths.get("").toAbsolutePath();
            File file = new File(path + File.separator + "run.bat");
            if(!file.exists()) {
                String resourcePath = "/run.bat";  // リソースファイルのパス
                String targetFilePath = String.valueOf(file);  // コピー先のファイルパス
                FileSys sys = new FileSys();
                sys.copyResource(resourcePath, targetFilePath);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
