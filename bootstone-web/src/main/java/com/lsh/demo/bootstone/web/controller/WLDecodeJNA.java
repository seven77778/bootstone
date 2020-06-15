package com.lsh.demo.bootstone.web.controller;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class WLDecodeJNA {

    public interface WLDecode extends Library {
        WLDecode INSTANCE = (WLDecode) Native.loadLibrary("I:\\Users\\LSH\\Desktop\\123\\libwldecode.so", WLDecode.class);
        int WltToBmp(byte[] wltData,byte[] BmpData);
    }

    public static void main(String[] args) {
        String photoImg = "δ������Ƭ����";
        byte[] ImgData = new byte[38862+1024]; //38862Ϊbmp���ݳ��ȣ�1024Ϊwlt���ݳ���
        byte[] photoData = new byte[1024];
        photoData = hexStringToBytes(photoImg);
        int num = WLDecodeJNA.WLDecode.INSTANCE.WltToBmp(photoData,ImgData);
        //0��ʧ��  1���ɹ�
        System.out.println(num);
        System.out.println("ImgData len:"+ImgData.length);
        byte[] BmpData = new byte[38862];
        System.arraycopy(ImgData, 0, BmpData, 0, 38862);  //��Ҫ��ȡ38862����Ϊbmp��Ƭ����
        System.out.println(Base64.encode(BmpData));//ת��base64��ʽ

    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }


    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
