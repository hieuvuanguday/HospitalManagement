package tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class FileHandle {

    //lấy đường dẫn chính xác đến file sẽ save data/load data
    private final static String SYSPATH = new File("").getAbsolutePath();

    private static String initPath(String path) {
        return SYSPATH + path;
    }

    //hàm đọc từ file
    public static ArrayList<String> readFromFile(String path) {
        String _path = initPath(path);
        File file = new File(_path);
        ArrayList<String> dta = new ArrayList<>();
        try {
            BufferedReader input = new BufferedReader(new FileReader(file));
            String line;
            while ((line = input.readLine()) != null) {
                dta.add(line.trim());
            }
            input.close();
        } catch (IOException e) {
        }
        return dta;
    }

    //viết vào file
    public static boolean writeToFile(String filePath, ArrayList<String> dta) {
        String _path = initPath(filePath);
        File file = new File(_path);
        try {
            file.createNewFile();
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            for (String line : dta) {
                output.write(line);
                output.newLine();
            }
            output.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
