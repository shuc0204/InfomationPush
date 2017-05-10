package com.info.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by wayne on 2017/5/10.
 */
public class FileCacheUtil {

    static private   String currentDir = new File("").getAbsolutePath();
    static {
        currentDir = currentDir.replaceAll("\\\\","/");
        if(!currentDir.endsWith("/")){
            currentDir= currentDir + "/";
            currentDir = currentDir + "cache_temp/";
        }
        final File file = new File(currentDir);
        if(!file.exists()){
            file.mkdir();
        }
    }

    public static void main(String args){
        System.out.println(currentDir);
    }


    public static File getFile(String s) {
        return  new File(currentDir+s);
    }

    public static void writeTempCacheOnce(String tempFileName, String content) {
            try {
                String f = currentDir + tempFileName;
                IOUtils.write(content,new FileOutputStream(f));
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static String getTempContent(String tempFileName) {
        File f = getFile(tempFileName);
        if(f.exists()){
            try {return FileUtils.readFileToString(f);
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }else{
            return null;
        }
    }
}
