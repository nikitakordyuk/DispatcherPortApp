package com.port.DispatcherPortApp.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FileDownloader {
    public static void download(String fileUrl) {
        String outputPath = "D:\\downloads";
        try {
            FileUtils.copyURLToFile(new URL(fileUrl), new File(outputPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
