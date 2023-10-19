package com.snhu.sslserver;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";
@RestController
class ServerController{
    @RequestMapping("/hash")
    public String myHash() throws NoSuchAlgorithmException {
    	String data = "Hello Zach Beachy!";

        //calculate the sha256 hash
        String hash = calculateHash(data);
        
       
        return "<p>data:"+data + "</p>" + "<p>SHA-256 cypher algorithm used.</p>" + "<p>Checksum Value: " + hash + "</p>";
    }


    private String calculateHash(String data)
    {
        try 
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));

            //convert array into hex string
            StringBuilder arrayToHex = new StringBuilder();
            for(byte hashByte : hash)
            {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1)
                {
                    arrayToHex.append('0');
                }
                arrayToHex.append(hex);
            }

            return arrayToHex.toString();
        } catch (NoSuchAlgorithmException exception)
        {
            exception.printStackTrace();
            return "Error calculating hash; No Such Algorithm";
        }
    }
}

