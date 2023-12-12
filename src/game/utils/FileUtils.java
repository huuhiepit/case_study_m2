package game.utils;

import game.model.impl.IParser;
import game.model.question.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils <T> {

    public static <T> void writeFile(String fileName, List<T> data) {
        try (BufferedWriter bfw = new BufferedWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (T e : data) {
                bfw.write(e + "\n");
                bfw.flush();
            }
        } catch (Exception ignored) {
        }
    }
    public static void writeFile(String fileName, Object object) {
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(fileName, true))) {
            bfw.write("\n" + object);
        } catch (Exception ignored) {
        }
    }
    public static <T> List<T> readFile(String fileName, Class<T> clazz) {
        List<T> data = new ArrayList<>();

        try {
            BufferedReader bf = new BufferedReader(new FileReader(fileName));

            String line = null;
            while ((line = bf.readLine()) != null) {
                Object obj = clazz.newInstance();
                IParser iParser = (IParser) obj;
                iParser.parse(line);

                data.add((T) obj);
            }

            bf.close();
        } catch (Exception ignored) {
        }

        return data;
    }
    public static boolean checkFileExits(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }
}
