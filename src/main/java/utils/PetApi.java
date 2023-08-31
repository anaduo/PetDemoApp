package utils;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class PetApi {
    private static final String BASE_URL = "https://petstore.swagger.io/";
    private static final String FIND_BY_STATUS = "v2/pet/findByStatus?status=";
    private static final String ADD_NEW_PET = "v2/pet";
    private HttpUrl.Builder builder;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    public Request getPetByStatus(Enum petStatus) {
        String status = petStatus.name().toLowerCase();
        return new Request.Builder()
                .url(BASE_URL + FIND_BY_STATUS + status)
                .get()
                .build();
    }
    public Request postAddorUpdatePet(String body) {
        return new Request.Builder()
                .url(BASE_URL + ADD_NEW_PET)
                .post(RequestBody.create(body, JSON))
                .build();
    }
}