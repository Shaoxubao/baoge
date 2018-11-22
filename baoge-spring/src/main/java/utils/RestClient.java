package utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

public class RestClient {

    private RestTemplate restTemplate;

    public static class RestClientHolder {
        public static final RestClient INSTANCE = new RestClient();
    }

    public static RestClient instance() {
        return RestClientHolder.INSTANCE;
    }

    private RestClient() {
        restTemplate = new RestTemplate();
    }

    public Response get(String url, Map<String, String> params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<Response> resp = restTemplate.exchange(
                builder.build().encode().toUri(), HttpMethod.GET, entity, Response.class);
        return resp.getBody();
    }


    public Response post(String url, Map<String, String> body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(body, headers);

        HttpEntity<Response> resp = restTemplate.exchange(url, HttpMethod.POST, entity, Response.class);
        return resp.getBody();
    }

    private static String encryptSignature(Request request, String appSecret) {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("client", request.getClient());
        parameters.put("appKey", request.getAppKey());

        parameters.put("appSecret", appSecret);
        parameters.put("time", request.getTime());
        parameters.put("version", request.getVersion());
        parameters.put("format", request.getFormat());
        parameters.put("body", request.getBody());

        List<String> parametersList = new ArrayList<String>();
        for (String key : parameters.keySet()) {
            parametersList.add(key);
        }
        Collections.sort(parametersList);
        StringBuilder sb = new StringBuilder();
        sb.append("POST");
        for (String key : parametersList) {
            sb.append(key).append(parameters.get(key));
        }
        String md5 = Md5Utils.getMd5ByStr(sb.toString());
        return md5;
    }
}
