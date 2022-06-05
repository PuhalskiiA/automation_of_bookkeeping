package project.utils.commands;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Scanner;

public class Authorization {
    public static String getAuthorization(Scanner scan, String url) {
        try {
            System.out.println("Enter login:");
            String login = scan.next();

            System.out.println("Enter password:");
            String password = scan.next();

            ResponseEntity<Map> responseEntity;
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> tokenRequest = new HttpEntity<>("{\n" + "\t\"userName\":\"" + login + "\",\n" +
                    "\t\"password\":\"" + password + "\"\n" + "}", headers);

            headers.setContentType(MediaType.APPLICATION_JSON);
            responseEntity = restTemplate.postForEntity(url + "/auth/signin", tokenRequest, Map.class);
            String token = (String) responseEntity.getBody().get("token");

            return token;
        } catch (Exception exception) {
            System.err.println("Can't take token to authorization");
            return null;
        }
    }
}
