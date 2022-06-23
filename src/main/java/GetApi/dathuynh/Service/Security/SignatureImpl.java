package GetApi.dathuynh.Service.Security;


import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class SignatureImpl implements Signature {

    @Value("${zing.serectkey}")
    public String secret_key;

    @Override
    public String getHash256(String a) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashInBytes = md.digest(a.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    @Override
    public String getHmac512(String str, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA512");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA512");
        sha256_HMAC.init(secret_key);
        byte[] byteArray =
                sha256_HMAC.doFinal(str.getBytes());
        return new String(Hex.encodeHex(byteArray));
    }

    //Get
    @Override
    public String sig(long time,
                      String hash,
                      String slug) throws NoSuchAlgorithmException, InvalidKeyException {
        String firstHash = getHash256(hash);
        return getHmac512(slug.concat(firstHash), secret_key);
    }


}
