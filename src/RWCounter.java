package src;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RWCounter {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите имя входного файла в папке files:");
        File inp = new File("src/files/" + in.nextLine());
        if (!inp.exists() || inp.isDirectory()){
            System.out.println("Такого входного файла не существует");
            main(args);
        }
        System.out.println("Введите имя выходного файла в папке files:");
        File out = new File("src/files/" + in.nextLine());
        if (!out.exists() || out.isDirectory()){
            try {
                out.createNewFile();
            } catch (IOException e) {
                System.out.println("Невозможно создать файл");
                System.exit(0);
            }
        }
        writeMapToFile(countLetters(inp), out);
    }

    static void writeMapToFile(Map<Character, Integer> map, File out) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(out))){
            for(Map.Entry<Character, Integer> e : map.entrySet()){
                bw.write(e.getKey() + " : " + e.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Произошел сбой во время записи в файл");
            System.exit(0);
        }
    }

    static Map<Character, Integer> countLetters(File file) {
        Map<Character, Integer> dict = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String buffer;
            while ((buffer = br.readLine()) != null){
                for (int i = 0; i < buffer.length(); i++) {
                    char temp = buffer.charAt(i);
                    if (IsLatinLetter(temp)){
                        if (!dict.containsKey(temp)) dict.put(temp, 1);
                        else dict.put(temp, dict.get(temp) + 1);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Произошел сбой во время чтения файла");
            System.exit(0);
        }
        return dict;
    }
    static boolean IsLatinLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
}
