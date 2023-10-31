import java.io.*;
import java.util.*;

/**
 *программа считывает содержимое input.txt
 *подсчитывает количество вхождений каждого символа кроме пробела и переноса строки
 *результаты записывает в output.txt
 */
public class Main {
    public static void main(String[] args) throws IOException {

        File file = new File("input.txt");
        //проверка существования файла
        if (!file.exists()) {
            System.out.print("input.txt отсутствует\n");
        } else {
            //проверка является ли файл пустым
            if (file.length() == 0) {
                System.out.print("input.txt пустой\n");
            } else {
                HashMap<Character, Integer> map = new HashMap<>();
                FileInputStream iStream = new FileInputStream(file);
                /*если в файле еще есть символы,
                 *то прочитанный символ проверяется на наличие ключа в словаре,
                 *если он присутсвует значение увеличивается на 1,
                 *иначе символ добавляется в словарь
                 */
                while (iStream.available() > 0) {
                    char temporary = (char) iStream.read();
                    if (map.containsKey(temporary)) {
                        int x = map.get(temporary);
                        map.put(temporary, x + 1);
                    } else {
                        if (!Character.isWhitespace(temporary)) {
                            map.put(temporary, 1);
                        }
                    }
                }
                iStream.close();
                StringBuilder out = new StringBuilder();
                ArrayList<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
                list.sort(Comparator.comparingInt(Map.Entry::getValue));
                //создается строка из всех ключей и значений
                for (Map.Entry<Character, Integer> el : list)
                    out.append("'").append(el.getKey()).append("'=").append(el.getValue()).append(", ");
                out.deleteCharAt(out.length() - 2);
                BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
                //результат записывается в файл
                writer.write(String.valueOf(out));
                writer.close();
            }
        }
    }
}