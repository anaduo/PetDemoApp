package petsuites;

import com.github.tomakehurst.wiremock.WireMockServer;
import domain.StatusDTO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.HelperJSON;
import utils.PetApi;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.testng.Assert.assertEquals;


public class PetTests {
    WireMockServer wireMockServer = new WireMockServer(8080);
    public PetApi petApi = new PetApi();
    private static OkHttpClient client;
    private static HelperJSON jsonhelper;

    @BeforeTest
    public void setUpClient() {
        client = new OkHttpClient();
    }


    @Test(groups = {"CRUD"}) //POST METHOD
    public void AddNewPetTest() throws IOException {

        String file = "model.json";
        String body = jsonhelper.jSONtoString(file);
        Request request = petApi.postAddorUpdatePet(body);

        // Act - call the endpoint
        Response response = client.newCall(request).execute();

        // Assert - verify the response
        assertEquals(response.code(), 200);
        Assert.assertEquals(response.header("Content-Type"), "application/json");
        Assert.assertTrue(response.body().string().contains("Wellington"));
    }

    @Test(groups = {"Bad request"})//POST METHOD BAD REQUEST
    public void AddNewPetBadRequestTest() throws IOException {
        //Empty body,bad request
        String body = "";
        Request request = petApi.postAddorUpdatePet(body);

        // Act - call the endpoint
        Response response = client.newCall(request).execute();

        // Assert - verify the response
        assertEquals(response.code(), 405);
        Assert.assertEquals(response.header("Content-Type"), "application/json");
    }

    @Test(groups = {"CRUD"}) //PUT METHOD
    public void updatePetTest() throws IOException {
        // Set up the WireMock mapping stub for the test
        stubFor(put("https://petstore.swagger.io/")
                .withHeader("Content-Type", containing("xml"))
                .willReturn(ok()
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>SUCCESS</response>")));
        String file = "updatepet.json";
        String body = jsonhelper.jSONtoString(file);
        Request request = petApi.postAddorUpdatePet(body);

        // Act - call the endpoint
        Response response = client.newCall(request).execute();

        // Assert - verify the response

        assertEquals(response.code(), 200);
        Assert.assertTrue(response.body().string().contains("Wellington"));

    }

    @Test(groups = {"CRUD"}) //GET METHOD
    public void findPetByStatusTest() throws IOException {

        // execute request through PET API  http client
        StatusDTO petStatus = StatusDTO.PENDING;
        Request request = petApi.getPetByStatus(petStatus);

        // Act - call the endpoint
        Response response = client.newCall(request).execute();

        // Assert - verify the response
        assertEquals(response.code(), 200);
        Assert.assertTrue(response.body().string().contains("pending"));
    }
}
