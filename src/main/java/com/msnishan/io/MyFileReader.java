package com.msnishan.io;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Created by Nishan on 4/26/2016.
 */
public class MyFileReader {

    private String fileName;
    private File file;
    public MyFileReader(String fileName) {

        this.fileName = Objects.requireNonNull(fileName, "File name can not bu null");
        file = new File(fileName);

    }

    public List<String> getStudentNames() {
        List<String> names = null;

        try {
            names = FileUtils.readLines(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return names;
    }
}
