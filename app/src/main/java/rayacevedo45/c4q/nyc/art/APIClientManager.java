package rayacevedo45.c4q.nyc.art;

import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by c4q-Abass on 6/21/15.
 */
public class APIClientManager {
    String API_URL;

    public void APIClientManager(String API_URL){
        this.API_URL = API_URL;
    }

    RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint("https://api.github.com")
            .setErrorHandler(new MyErrorHandler())
            .build();





}

class MyErrorHandler implements ErrorHandler {
    @Override public Throwable handleError(RetrofitError cause) {
        Response r = cause.getResponse();
        if (r != null && r.getStatus() == 401) {
            return new Exception(cause);
        }
        return cause;
    }
}
