package reporter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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

    public InsuranceReporterImpl() {
        // Constructor
    }

    /**
     * Add/communicates an event to the insurer.
     * 
     * @param event String containing event data in JSON format.
     * @return the message returned by the insurer regarding the operation result.
     * @throws IOException if the connection was not successful.
     * @throws InterruptedException if the server is not available.
     */
    public static String addEvent(String event) throws IOException, InterruptedException {
        String url = MAIN_URL + ADD_ENDPOINT;
        return request(url, "POST", event, null);
    }

    /**
     * Returns the events communicated to the insurer until now.
     * 
     * @param groupKey String representing the group key identification (should have at least 15 characters).
     * @param groupName String with group identification.
     * @return a string that contains the JSON representation of the data stored in the Insurer.
     * @throws IOException if the response is not available.
     * @throws InterruptedException if the server is not available.
     */
    public static String getEvents(String groupKey, String groupName) throws IOException, InterruptedException {
        String url = MAIN_URL + GET_ENDPOINT;
        String[] params = { groupKey, groupName };
        return request(url, "GET", null, params);
    }

    /**
     * Deletes all events communicated to the insurer until now.
     * 
     * @param groupKey String representing the group key identification (should have at least 15 characters).
     * @param groupName String with group identification.
     * @return the message returned by the insurer regarding the operation result.
     * @throws IOException if the response is not available.
     * @throws InterruptedException if the server is not available.
     */
    public static String resetEvents(String groupKey, String groupName) throws IOException, InterruptedException {
        String url = MAIN_URL + RESET_ENDPOINT;
        String[] params = { groupKey, groupName };
        return request(url, "POST", null, params);
    }

    /**
     * Makes a request to the provided URL with the specified method and parameters.
     * 
     * @param URL the endpoint URL.
     * @param method the HTTP method (GET or POST).
     * @param body the request body for POST requests.
     * @param params an array of parameters for query string, if applicable.
     * @return the response from the server.
     * @throws IOException if there was an issue with the connection.
     * @throws InterruptedException if the request was interrupted or the server is unavailable.
     */
    private static String request(String URL, String method, String body, String[] params) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request;

        if (params != null && params.length == 2) {
            URL = URL + "?key=" + params[0] + "&name=" + params[1]; // Append query parameters
        }

        if (method.equals("GET")) {
            request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .GET()
                .build();
        } else if (method.equals("POST")) {
            HttpRequest.BodyPublisher bodyPublisher = (body != null) ? HttpRequest.BodyPublishers.ofString(body)
                : HttpRequest.BodyPublishers.noBody();
            request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .POST(bodyPublisher)
                .build();
        } else {
            throw new IllegalArgumentException("Invalid HTTP method: " + method);
        }

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
