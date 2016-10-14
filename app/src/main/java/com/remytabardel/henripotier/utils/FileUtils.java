package com.remytabardel.henripotier.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Remy Tabardel
 */

public class FileUtils {

    public static boolean copy(String inFile, String outFile) {
        boolean isOk = true;

        try {
            isOk = copy(new FileInputStream(new File(inFile)), outFile);
        } catch (Exception e) {
            isOk = false;
        }

        return isOk;
    }

    public static boolean copy(InputStream inputStream, String outFile) {
        boolean isOk = true;
        OutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(prepareToCreate(outFile));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (Exception e) {
            isOk = false;
        } finally {
            StreamUtils.close(inputStream);
            StreamUtils.close(outputStream);
        }

        return isOk;
    }

    public static File prepareToCreate(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        } else {
            file.getParentFile().mkdirs();
        }

        return file;
    }
}
