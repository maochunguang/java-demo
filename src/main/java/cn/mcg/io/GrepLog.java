package cn.mcg.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author maocg
 * Date：2018/4/26
 * Description：阿里巴巴面试题，用java实现
 * grep "aaa" test.log |uniq -c |sort -nr
 */
public class GrepLog {
    public static String key = "Login";

    public BufferedReader getFileContent(String fileName) {
        BufferedReader bufread = null;
        try {
            File file = new File(fileName);
            bufread = new BufferedReader(new FileReader(file));
            bufread.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bufread;
    }

    public List<String> filteString(BufferedReader buf) throws IOException {
        String read = null;
        List<String> list = new ArrayList<>();
        while ((read = buf.readLine()) != null) {
            if (read.contains(key)) {
                list.add(read);
            }
        }
        return list;
    }

    public Map<String, Integer> removeDuplicateAndCount(List<String> list) {
        Map<String, Integer> map = new TreeMap<>();
        for (int i = 0; i < list.size(); i++) {
            String value = list.get(i);
            if (map.containsKey(value)) {
                map.put(value, map.get(value) + 1);
            } else {
                map.put(value, 1);
            }
        }
        return map;
    }

    public void sortAndPrint(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        for (Map.Entry<String, Integer> e : list) {
            System.out.println(e.getKey() + ":" + e.getValue());
        }
    }

    public static void main(String[] args) throws IOException {
        GrepLog log = new GrepLog();
        String file = "webx.log";
        BufferedReader buf = log.getFileContent(file);
        List<String> list = log.filteString(buf);
        Map<String, Integer> map = log.removeDuplicateAndCount(list);
        log.sortAndPrint(map);
    }
}


