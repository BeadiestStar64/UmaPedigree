package umapedigree.beadieststar64.com.github.FileSys;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class FileSys {

    public File createFolder(String name) {
        try {
            File directory = new File(name);
            if(!directory.isDirectory()) {
                if(directory.mkdirs()) {
                    return new File(name);
                }else{
                    return null;
                }
            }else{
                return new File(name);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public File createFile(String mode, Path directory, String fileName, String extension) {
        try {
            File file;
            if (mode.equals("non")) {
                file = new File(fileName + "." + extension);
            } else {
                file = new File(directory + File.separator + createFolder(mode) + File.separator + fileName + "." + extension);
            }
            return file;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void copyResource(String resourcePath, String targetPath) {
        try {
            File temp = copyTemp(resourcePath);
            copyFile(temp, new File(targetPath));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File copyTemp(String resourcePath) throws Exception {
        try (InputStream input = FileSys.class.getResourceAsStream(resourcePath)){
            File temp = File.createTempFile("temp", null);
            temp.deleteOnExit();

            try (FileOutputStream output = new FileOutputStream(temp)){
                byte[] buffer = new byte[1024];
                int byteRead;
                while ((byteRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, byteRead);
                }
            }
            input.close();
            return temp;
        }
    }

    private void copyFile(File sourceFile, File targetFile) {
        try {
            Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ArrayList<String>> readFile(String targetPath) {
        try {
            File file = new File(targetPath);
            try (InputStream input = new FileInputStream(file);
                 InputStreamReader iReader = new InputStreamReader(input, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(iReader)){
                String data;
                ArrayList<String> insertData = new ArrayList<>();
                ArrayList<ArrayList<String>> insertList = new ArrayList<>();

                while ((data = reader.readLine()) != null) {
                    if(data.charAt(0) == '#') {
                        continue;
                    }
                    insertData.add(data);
                    insertList.add(insertData);
                    insertData = new ArrayList<>();
                }
                return insertList;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
