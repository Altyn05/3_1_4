package rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import rest.model.User;

import javax.annotation.PostConstruct;

@Component
public class restController {

    private static final String URL = "http://91.241.64.178:7081/api/users";


    @PostConstruct
    public void code() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);
        String cookie = response.getHeaders().getFirst("Set-Cookie");
        headers.add("cookie", cookie);

        String string = "";


        HttpEntity<User> userHttpEntity = new HttpEntity<>(new User(3L, "James", "Brown", (byte) 50), headers);
        ResponseEntity<String> stringResponseEntity = restTemplate.exchange(URL, HttpMethod.POST, userHttpEntity, String.class);
        string += stringResponseEntity.getBody();


        userHttpEntity = new HttpEntity<>(new User(3L, "Thomas", "Shelby", (byte) 50), headers);
        stringResponseEntity = restTemplate.exchange(URL, HttpMethod.PUT, userHttpEntity, String.class);
        string += stringResponseEntity.getBody();


        userHttpEntity = new HttpEntity<>(new User(3L, "Thomas", "Shelby", (byte) 50), headers);
        stringResponseEntity = restTemplate.exchange(URL + "/" + 3, HttpMethod.DELETE, userHttpEntity, String.class);
        string += stringResponseEntity.getBody();

        System.out.println(string);

    }
}

