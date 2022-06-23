package GetApi.dathuynh.Service.Security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface Signature {
    String getHmac512(String str, String key) throws NoSuchAlgorithmException, InvalidKeyException;
    String getHash256(String a) throws NoSuchAlgorithmException;
    String sig(long time,
                     String hash,
                     String slug) throws NoSuchAlgorithmException, InvalidKeyException;
}
