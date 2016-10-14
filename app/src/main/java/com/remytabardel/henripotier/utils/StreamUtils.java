package com.remytabardel.henripotier.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author Remy Tabardel
 */

public class StreamUtils {
    public static void close(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {

            }
        }
    }
}
