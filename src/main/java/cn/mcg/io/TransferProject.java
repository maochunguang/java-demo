package cn.mcg.io;

import java.io.File;
import java.io.IOException;

/**
 * Author: mac
 * Date: 2017/8/9
 * Description: todo
 */
public class TransferProject {
    public static void transferFile(String pathName, int depth) throws Exception {
        File dirFile = new File(pathName);
        if (!isValidFile(dirFile)) return;
        for (int j = 0; j < depth; j++) {
            System.out.print("  ");
        }
        System.out.print("|--");
        System.out.println(dirFile.getName());
        //获取此目录下的所有文件名与目录名
        String[] fileList = dirFile.list();
        int currentDepth = depth + 1;
        for (int i = 0; i < fileList.length; i++) {
            String string = fileList[i];
            File file = new File(dirFile.getPath(), string);
            String name = file.getName();
            //如果是一个目录，搜索深度depth++，输出目录名后，进行递归
            if (file.isDirectory()) {
                //递归
                transferFile(file.getCanonicalPath(), currentDepth);
                System.out.println(name + " has converted to utf8 ");
            } else {
                if (name.contains(".java") || name.contains(".properties") || name.contains(".xml")) {
                    readAndWrite(file);
                }
            }
        }
    }

    private static boolean isValidFile(File dirFile) throws IOException {
        if (dirFile.exists()) {
            System.out.println("file exist");
            return true;
        }
        if (dirFile.isDirectory()) {
            if (dirFile.isFile()) {
                System.out.println(dirFile.getCanonicalFile());
            }
            return true;
        }
        return false;
    }

    private static void readAndWrite(File file) throws Exception {
        String  content = FileUtils.readFileByEncode(file.getPath(), "GBK");
        FileUtils.writeByBufferedReader(file.getPath(), new String(content.getBytes("UTF-8"), "UTF-8"));
    }

    public static void main(String[] args) throws Exception {
        String path = "/Users/mac/Downloads/unit06_jdbc/src";
        transferFile(path, 1);
    }
}
