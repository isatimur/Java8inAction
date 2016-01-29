package com.isatimur;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by Тимакс on 24.01.2016.
 * Reading file by using lines method, that transforms all the lines to
 */
public class StreamFile {

    public static void main(String[] args) {
        long uniqueWord= 0;

        try(Stream<String> lines= Files.lines(Paths.get("wiki_Java.txt"), Charset.defaultCharset())){
            uniqueWord = lines.flatMap(s-> Arrays.stream(s.split(" "))).filter(s1 -> s1.equals("javafx")).count();
            System.out.println("The amount of unique words: "+uniqueWord);
        } catch (IOException io){
            io.printStackTrace();
        }
    }
}
