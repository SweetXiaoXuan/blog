package com.mf.feel.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.*;

/**
 * zip打包工具类
 */
@Slf4j
public class ZipCompress {
    public static final int NOT_CHOOSE = 0;
    public static final int CONTAINS = 1;
    public static final int NOT_CONTAINS = 2;


    public ZipCompress() {
    }

    /**
     * 打包
     *
     * @param downloadPath            保存路径
     * @param paths                   需要打包的文件路径
     * @param fileName                打包后的文件名
     * @param keepDictionaryStructure 是否保留目录结构
     * @return 打包是否成功
     */
    public static Boolean zipFile(String downloadPath, List<String> paths, String fileName, Boolean keepDictionaryStructure) {
        File file = new File(downloadPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        downloadPath = downloadPath + fileName + ".zip";
        FileOutputStream fileOutputStream = null;
        ZipOutputStream out = null;
        try {
            fileOutputStream = new FileOutputStream(downloadPath);
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
            out = new ZipOutputStream(cos);
            ZipCompress zipCompress = new ZipCompress();
            zipCompress.zipFile(paths, out, keepDictionaryStructure);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.flush();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        List<String> file = new ArrayList<>();
        String downUrl = "D:\\Documents\\WeChat Files\\C_o-YJF\\c\\";
        file.add("D:\\Documents\\WeChat Files\\C_o-YJF\\a");
//        file.add("D:\\Documents\\WeChat Files\\C_o-YJF\\11111.pdf");
//        file.add("D:\\Documents\\WeChat Files\\C_o-YJF\\Files\\123456789.pdf");
        zipFile(downUrl, file, "aaabbbccc", true);

    }

    /**
     * 打包默认不保留目录结构
     *
     * @param downloadPath 保存路径
     * @param paths        需要打包的文件路径
     * @param fileName     打包后的文件名
     * @return 打包是否成功
     */
    public static Boolean zipFile(String downloadPath, List<String> paths, String fileName) {
        return zipFile(downloadPath, paths, fileName, false);
    }

    public static Boolean zipFile(String downloadPath, List<String> paths, String fileName, Map<Integer, String> message) {
        return zipFile(downloadPath, paths, fileName, false, message);
    }

    /**
     * 打包
     *
     * @param downloadPath            保存路径
     * @param paths                   需要打包的文件路径
     * @param fileName                打包后的文件名
     * @param keepDictionaryStructure 是否保留目录结构
     * @return 打包是否成功
     */
    public static Boolean zipFile(String downloadPath, List<String> paths, String fileName, Boolean keepDictionaryStructure, Map<Integer, String> message) {
        File file = new File(downloadPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        downloadPath = downloadPath + fileName + ".zip";
        FileOutputStream fileOutputStream = null;
        ZipOutputStream out = null;
        try {
            fileOutputStream = new FileOutputStream(downloadPath);
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
            out = new ZipOutputStream(cos);
            ZipCompress zipCompress = new ZipCompress();
            zipCompress.zipFile(paths, out, keepDictionaryStructure, message);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                fileOutputStream.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解压缩
     *
     * @param sourceFile 源文件
     * @param targetPath 目标文件夹
     */
    public static void unZip(String sourceFile, String targetPath) {
        unZip(sourceFile, targetPath, 0, null);
    }

    /**
     * 解压缩选择文件解压
     *
     * @param sourceFile 源文件
     * @param targetPath 目标文件夹
     * @param chooseType 选择类型 0 不选择文件解压 1包含 2不包含 详情见顶部常量
     * @param name       选择的文件名
     */
    public static void unZip(String sourceFile, String targetPath, int chooseType, String name) {
        File source = new File(sourceFile);
        ZipInputStream zis = null;
        try {
            zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(source)));
            File target;
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null && !entry.isDirectory()) {
                if (chooseType != NOT_CHOOSE && (chooseType == NOT_CONTAINS && !entry.getName().contains(name)) || (chooseType == CONTAINS && entry.getName().contains(name))) {
                    if (!entry.isDirectory()) {
                        target = new File(targetPath + File.separator + entry.getName());
                        if (!target.exists()) {
                            new File(target.getParent()).mkdirs();
                        }
                        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(target));

                        int b;
                        byte[] buffer = new byte[1024];
                        while ((b = zis.read(buffer)) != -1) {
                            bos.write(buffer, 0, b);
                        }
                        bos.close();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (zis != null) {
                    zis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 批量打包文件
     *
     * @param files                   文件路径
     * @param outputStream            文件流
     * @param keepDictionaryStructure 是否保留文件结构
     */
    private void zipFile(List<String> files, ZipOutputStream outputStream, Boolean keepDictionaryStructure) {
        files.forEach(filePath -> {
            log.debug("file path :" + filePath);
            File file = new File(filePath);
            zipFile(file, outputStream, keepDictionaryStructure, "");
        });
    }

    private void zipFile(List<String> files, ZipOutputStream outputStream, Boolean keepDictionaryStructure, Map<Integer, String> message) {
        files.forEach(filePath -> {
            log.debug("file path :" + filePath);
            File file = new File(filePath);
            zipFile(file, outputStream, keepDictionaryStructure, "", message);
        });
    }

    /**
     * 单个打包文件
     *
     * @param inputFile               文件路径
     * @param ouputStream             输出文件流
     * @param keepDictionaryStructure 是否保留文件结构
     * @param parentPath              保留文件结构的情况加需要加上父文件名字例:该文件在meeting/a下就传入meeting/a
     */
    private void zipFile(File inputFile, ZipOutputStream ouputStream, Boolean keepDictionaryStructure, String parentPath) {

        if (inputFile.exists()) {
            if (inputFile.isFile()) {
                try (FileInputStream in = new FileInputStream(inputFile);
                     BufferedInputStream bins = new BufferedInputStream(in, 512)) {

                    //org.apache.tools.zip.ZipEntry
                    ZipEntry entry = new ZipEntry(parentPath + inputFile.getName());
                    ouputStream.putNextEntry(entry);
                    // 向压缩文件中输出数据
                    int nNumber;
                    byte[] buffer = new byte[512];
                    while ((nNumber = bins.read(buffer)) != -1) {
                        ouputStream.write(buffer, 0, nNumber);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    File[] files = inputFile.listFiles();
                    // 需要保留原来的文件结构时,需要对空文件夹进行处理
                    if (keepDictionaryStructure) {
                        parentPath += inputFile.getName() + File.separator;
                        if (files == null || files.length == 0) {
                            // 空文件夹的处理
                            ouputStream.putNextEntry(new ZipEntry(parentPath));
                            return;
                        }
                    }
                    for (File file : files) {
                        zipFile(file, ouputStream, keepDictionaryStructure, parentPath);
                    }
                } catch (Exception e) {
                    log.error("zip打包失败: {}", e);
                }
            }

        }

    }

    /**
     * 单个打包文件
     *
     * @param inputFile               文件路径
     * @param ouputStream             输出文件流
     * @param keepDictionaryStructure 是否保留文件结构
     * @param parentPath              保留文件结构的情况加需要加上父文件名字例:该文件在meeting/a下就传入meeting/a
     */
    private void zipFile(File inputFile, ZipOutputStream ouputStream, Boolean keepDictionaryStructure, String parentPath, Map<Integer, String> message) {
        if (inputFile.exists()) {
            if (inputFile.isFile()) {
                try (FileInputStream in = new FileInputStream(inputFile);
                     BufferedInputStream bins = new BufferedInputStream(in, 512)) {
                    ZipEntry entry = new ZipEntry(parentPath + inputFile.getName());
                    ouputStream.putNextEntry(entry);
                    // 向压缩文件中输出数据
                    int nNumber;
                    byte[] buffer = new byte[512];
                    while ((nNumber = bins.read(buffer)) != -1) {
                        ouputStream.write(buffer, 0, nNumber);
                    }
                } catch (Exception e) {
                    log.error("zip打包关闭流失败: {}", e);
                }
            } else {
                try {
                    File[] files = inputFile.listFiles();
                    // 需要保留原来的文件结构时,需要对空文件夹进行处理
                    if (keepDictionaryStructure) {
                        parentPath += inputFile.getName() + File.separator;
                        if (files == null || files.length == 0) {
                            // 空文件夹的处理
                            ouputStream.putNextEntry(new ZipEntry(parentPath));
                            return;
                        }
                    }
                    for (File file : files) {
                        zipFile(file, ouputStream, keepDictionaryStructure, parentPath);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}