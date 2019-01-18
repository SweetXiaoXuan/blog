package com.mf.feel.utils;


import org.springframework.util.StringUtils;

import java.io.File;
import java.net.URL;

public class FileUtil {
    public final static int SUCCESS = 0;
    public final static int ERROR = 1;
    public final static int FILE_NOT_FOUND = 2;

    /**
     * 获取文件后缀名
     *
     * @param name 文件名
     * @return 文件后缀
     */
    public static String getSuffix(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("文件名有误");
        }
        int index = name.lastIndexOf(".");
        if (index == -1) {
            throw new IllegalArgumentException("文件名有误");
        }
        return name.substring(index);
    }

//    /**
//     * 获取文件后缀名
//     *
//     * @param multipartFile 文件
//     * @return 文件后缀
//     */
//    public static String getSuffix(MultipartFile multipartFile) {
//        return getSuffix(multipartFile.getOriginalFilename());
//    }

    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName 要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static int delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return FILE_NOT_FOUND;
        } else {
            if (file.isFile())
                return deleteFile(fileName) ? SUCCESS : ERROR;
            else
                return deleteDirectory(fileName) ? SUCCESS : ERROR;
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据resource文件相对路径返回文件全路径
     *
     * @param filePath resource文件相对路径
     * @param isLinux  是否是linux系统
     * @return 文件全路径
     */
    public static String getFilePath(String filePath, Boolean isLinux) {
        URL url = FileUtil.class.getClassLoader().getResource(filePath);
        if (url == null) {
            throw new RuntimeException("ftl模板路径解析错误");
        }
        String path = url.getPath();
        if (!isLinux) {
            //windows去除前缀斜杠
            path = path.substring(1);
        }
        return path;
    }

    public static void main(String[] args) {
//  // 删除单个文件
//  String file = "c:/test/test.txt";
//  DeleteFileUtil.deleteFile(file);
//  System.out.println();
        // 删除一个目录
        String dir = "D:/data/upload/files";
//  System.out.println();
//  // 删除文件
//  dir = "c:/test/test0";
//  DeleteFileUtil.delete(dir);

    }
}
