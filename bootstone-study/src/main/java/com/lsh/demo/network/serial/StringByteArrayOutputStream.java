package com.lsh.demo.network.serial;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 提供对string的转化
 */

public class StringByteArrayOutputStream extends ByteArrayOutputStream {

    private Charset charset = Charset.defaultCharset();

    public StringByteArrayOutputStream() {
    }

    public StringByteArrayOutputStream(Charset charset) {
        this.charset = charset;
    }

    public void write(String str, Charset charset) throws IOException {
        if (str == null || str.length() == 0) return;
        this.write(str.getBytes(charset));
    }

    public void write(String str) throws IOException {
        if (str == null || str.length() == 0) return;
        this.write(str.getBytes(charset));
    }
}
