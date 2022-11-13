package com.uniBoke.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSA {
    private String algorithm = "RSA"; // 加密类型
    static {
        try {
            generateKeyToFile("RSA","pubKey.pub","priKey.pri");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成密钥对并保存在本地文件中
     *
     * @param algorithm : 算法
     * @param pubPath   : 公钥保存路径
     * @param priPath   : 私钥保存路径
     * @throws Exception
     */
    private static void generateKeyToFile(String algorithm, String pubPath, String priPath) throws Exception {
        // 获取密钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        // 获取密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 获取公钥
        PublicKey publicKey = keyPair.getPublic();
        // 获取私钥
        PrivateKey privateKey = keyPair.getPrivate();
        // 获取byte数组
        byte[] publicKeyEncoded = publicKey.getEncoded();
        byte[] privateKeyEncoded = privateKey.getEncoded();
        // 进行Base64编码
        String publicKeyString = Base64.encodeBase64String(publicKeyEncoded);
        String privateKeyString = Base64.encodeBase64String(privateKeyEncoded);
        // 保存文件
        FileUtils.writeStringToFile(new File(pubPath), publicKeyString, Charset.forName("UTF-8"));
        FileUtils.writeStringToFile(new File(priPath), privateKeyString, Charset.forName("UTF-8"));
    }

    /**
     * 获取文件中的私钥
     * @param algorithm 算法类型
     * @param priPath 密钥路径
     * @return PrivateKey
     * */
    public static PrivateKey getPrivateKey(String priPath,String algorithm) throws Exception{
        // 将文件内容转为字符串
        String privateKeyString = FileUtils.readFileToString(new File(priPath), Charset.defaultCharset());
        // 获取密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 构建密钥规范 进行Base64解码
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyString));
        // 生成私钥
        return keyFactory.generatePrivate(spec);
    }

    /**
     * 获取文件中的私钥
     * @param algorithm 算法类型
     * @param publicPath 密钥路径
     * @return PrivateKey
     * */
    public static PublicKey getPublicKey(String publicPath,String algorithm) throws Exception{
        // 将文件内容转为字符串
        String publicKeyString = FileUtils.readFileToString(new File(publicPath), Charset.defaultCharset());
        // 获取密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 构建密钥规范 进行Base64解码
        X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyString));
        // 生成公钥
        return keyFactory.generatePublic(spec);
    }

}
