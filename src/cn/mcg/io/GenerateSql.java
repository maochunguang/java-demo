package cn.mcg.io;

import java.util.List;

/**
 * Created by mao on 2017/9/11.
 */
public class GenerateSql {
    public static void main(String[] args) throws Exception {
        String dataPath = "/Users/mao/workspack/java-demo/src/channel.txt";
        String sqlPath = "/Users/mao/workspack/java-demo/src/insert.sql";
        List<String> data = FileUtils.readFile(dataPath, "UTF-8");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            String[] channels = data.get(i).split("-");
            sb.append("INSERT INTO `PAY`.`pay_channel`(`channel`, `name`) VALUES ('"+channels[0]+ "', '"+channels[1]+"');");
            sb.append("\r\n");
        }
        FileUtils.writeByBufferedReader(sqlPath, sb.toString());
    }
}
