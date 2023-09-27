package bio.kuno.TheOne.adapters.output.imagestorage.service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Base64;

public class SignedLinksService {
    private static final String SECRET_KEY = "4b2ee47888bb76eeac0fd4e3f260b076";
    public static String generateSignedUrl(String baseUrl, long expirationTimeMillis, String parameter) {
        try {
            expirationTimeMillis = Instant.now().toEpochMilli() + expirationTimeMillis;
            String toSign = baseUrl + expirationTimeMillis + parameter;
            byte[] hmacSha256 = generateHmacSHA256(toSign);
            String signature = Base64.getUrlEncoder().withoutPadding().encodeToString(hmacSha256);
            String encodedSignature = URLEncoder.encode(signature, StandardCharsets.UTF_8.toString());

            return baseUrl + "?expires=" + expirationTimeMillis + "&parameter=" + parameter + "&signature=" + encodedSignature;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean validateSignedUrl(String baseUrl, long expirationTimeMillis, String parameter, String signature) {
        System.out.println(baseUrl);
        System.out.println(expirationTimeMillis);
        System.out.println(parameter);
        System.out.println(signature);
        if(Instant.now().toEpochMilli() >= expirationTimeMillis){
            return false;
        }
        try {
            String toSign = baseUrl + expirationTimeMillis + parameter;
            byte[] hmacSha256 = generateHmacSHA256(toSign);
            String toValidate = Base64.getUrlEncoder().withoutPadding().encodeToString(hmacSha256);
            String encodedSignature = URLEncoder.encode(toValidate, StandardCharsets.UTF_8.toString());
            return signature.equals(encodedSignature);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static byte[] generateHmacSHA256(String data) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        mac.init(secretKeySpec);
        return mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
    }
}
