package com.view.mvvmdemo.utils

import android.util.Base64
import com.view.mvvmdemo.DemoApplication
import java.math.BigInteger
import java.security.InvalidParameterException
import java.security.MessageDigest
import java.security.spec.AlgorithmParameterSpec
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * AES encryption and decryption
 *
 *
 * 1. key's length >= 16
 * 2. iv's length > 16
 * 3. "transformation": AES/CBC/PKCS5Padding
 * 4. iv=12(bytes) length=128(bits)
 * 5. iv=24(bytes) length=192(bits)
 * 6. iv=32(bytes) length=256(bits)
 *
 *
 * Created by Anter on 2018/4/11.
 */
object EncryptUtil {
    private var key: ByteArray? = null

    /**
     * Base64 decode then AES decrypt
     *
     * @param data           Data to decrypt
     * @param key            Decrypt key
     * @param iv             Decrypt key
     * @param transformation AES/CBC/PKCS5Padding
     * @return Decrypted bytes
     * @throws Exception Decrypt exception
     */
    @Throws(Exception::class)
    fun decryptBase64EncodeData(
        data: ByteArray?,
        key: ByteArray?,
        iv: ByteArray?,
        transformation: String?
    ): ByteArray {
        if (data == null || data.size == 0 || key == null || key.size < 16 || iv == null || iv.size < 16 || transformation == null || transformation.length == 0) {
            throw InvalidParameterException()
        }
        val textBytes = Base64.decode(data, Base64.DEFAULT)
        val ivSpec: AlgorithmParameterSpec = IvParameterSpec(iv)
        val newKey = SecretKeySpec(key, "AES")
        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec)
        return cipher.doFinal(textBytes)
    }

    /**
     * AES encrypt then base64 decode
     *
     * @param data           Data to encrypt
     * @param key            Encrypt key
     * @param iv             Encrypt key
     * @param transformation AES/CBC/PKCS5Padding
     * @return Encrypted bytes
     * @throws Exception Encrypt exception
     */
    @Throws(Exception::class)
    fun encryptAndBase64Encode(
        data: ByteArray?,
        key: ByteArray?,
        iv: ByteArray?,
        transformation: String?
    ): ByteArray {
        if (data == null || data.size == 0 || key == null || key.size == 0 || iv == null || iv.size == 0 || transformation == null || transformation.length == 0) {
            throw InvalidParameterException()
        }
        val ivSpec: AlgorithmParameterSpec = IvParameterSpec(iv)
        val newKey = SecretKeySpec(key, "AES")
        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec)
        return Base64.encode(cipher.doFinal(data), Base64.NO_WRAP)
    }

    @Throws(Exception::class)
    fun encryptAndBase64Encode2(txt: String): String {
        val data = txt.toByteArray()
        val msharedPreference: SharedPreference =
            SharedPreference(DemoApplication.getInstance())

        val ENC: String? = msharedPreference.getValueString("enc")

        if (DemoApplication.getappToken().equals("-1")) {
            key = ENC?.toByteArray()
        } else {
            val txttest: String = DemoApplication.getappToken()
            key = txttest.toByteArray()
        }


        val newKey = SecretKeySpec(key, "AES")
        val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding")
        cipher.init(Cipher.ENCRYPT_MODE, newKey)
        var encryptedString = String(Base64.encode(cipher.doFinal(data), Base64.NO_WRAP))
        encryptedString = md5(encryptedString)
        return encryptedString
    }


    fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }


    fun txtau(str: String, str1: String?, str2: String?): String {
        val txt = str1 + "urjfoj29f" + str2 + str + "0f84kf4f"
        return txt
    }


}