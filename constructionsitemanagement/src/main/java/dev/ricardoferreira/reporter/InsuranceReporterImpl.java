package reporter;

import estgconstroi.Event;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

/**
 * Nome: Ricardo Afonso Saavedra Ferreira
 * Número: 8220132
 * Turma: LSIRC
 */

public class InsuranceReporterImpl {

    private static final String MAIN_URL = "https://data.mongodb-api.com/app/triggers_realmapp-iutkp/endpoint";
    private static final String ADD_ENDPOINT = "/add";
    private static final String GET_ENDPOINT = "/get";
    private static final String RESET_ENDPOINT = "/reset";

    public InsuranceReporterImpl(){}

    public static String addEvent(String event) throws IOException, InterruptedException {
        String url = MAIN_URL + ADD_ENDPOINT;
        return request(url, "POST", event, null);
    }


    public static String getEvents(String groupKey, String groupName) throws IOException, InterruptedException {
        String url = MAIN_URL + GET_ENDPOINT;
        String[] params = { groupKey, groupName };
        return request(url, "GET", null, params);
    }


    public static String resetEvents(String groupKey, String groupName) throws IOException, InterruptedException {
        String url = MAIN_URL + RESET_ENDPOINT;
        String[] params = { groupKey, groupName };
        return request(url, "POST", null, params);
    }
    
    public static String formaterJSON(Event event){
        String eventJson = "{"
                + "\"groupname\": \"Grupo13378\","
                + "\"groupkey\": \"LEETLEETLEETLEET\","
                + "\"event\": {"
                + "\"uuid\": \"" + event.getUuid() + "\","
                + "\"data\": \"" + LocalDate.now().toString() + "\","
                + "\"priority\": \"" + event.getPriority().name() + "\","
                + "\"eventtype\": \"Accident\","
                + "\"title\": \"" + event.getTitle() + "\","
                + "\"constructionsitename\": \"" + event.getConstructionSite().getName() + "\","
                + "\"details\": \"" + event.getDetails() + "\","
                + "\"employeename\": \"" + event.getReporter().getName() + "\""
                + "}"
                + "}";
        return eventJson;
    }

    private static String request(String URL, String method, String body, String[] params) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request;

        if (params != null && params.length == 2) {
            URL = URL + "?groupKey=" + params[0] + "&groupName=" + params[1];
        }

        if (method.equals("GET")) {
            request = HttpRequest.newBuilder().uri(URI.create(URL)).GET().build();
        } else if (method.equals("POST")) {
            HttpRequest.BodyPublisher bodyPublisher = (body != null) ? HttpRequest.BodyPublishers.ofString(body): HttpRequest.BodyPublishers.noBody();
            request = HttpRequest.newBuilder().uri(URI.create(URL)).POST(bodyPublisher).build();
        } else {
            throw new IllegalArgumentException("Invalid HTTP method: " + method);
        }

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
