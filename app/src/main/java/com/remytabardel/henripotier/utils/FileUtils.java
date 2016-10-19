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

    public static boolean copy(String inPath, String outPath) {
        boolean isOk = true;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = new FileInputStream(new File(inPath));

            File outFile = new File(outPath);
            outFile.mkdirs();

            outputStream = new FileOutputStream(outFile);
            isOk = copy(inputStream, outputStream);
        } catch (Exception e) {
            isOk = false;
        } finally {
            StreamUtils.close(inputStream);
            StreamUtils.close(outputStream);
        }

        return isOk;
    }

    /**
     * copy inputstream to outputstream
     * IMPORTANT : you need to close streams yourself
     *
     * @param inputStream
     * @param outputStream
     * @return
     * @throws Exception
     */
    public static boolean copy(InputStream inputStream, OutputStream outputStream) throws Exception {
        boolean isOk = true;

        try {
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (Exception e) {
            isOk = false;
        }

        return isOk;
    }
}
