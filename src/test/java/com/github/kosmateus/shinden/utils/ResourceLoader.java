package com.github.kosmateus.shinden.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResourceLoader {


    public static File loadFile(String fileName) throws IOException {
        URL resourceUrl = getResourceAsURL(fileName);
        if (resourceUrl == null) {
            throw new IOException("File not found: " + fileName);
        }
        return new File(resourceUrl.getFile());
    }

    private static URL getResourceAsURL(String fileName) {
        return ResourceLoader.class.getClassLoader().getResource(fileName);
    }
}
