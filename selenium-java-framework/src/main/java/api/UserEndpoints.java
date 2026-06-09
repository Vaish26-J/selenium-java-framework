package api;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import models.CreateUserRequest;

public class UserEndpoints {
    ApiClient client = new ApiClient();
    public Response getUser(int userId){
        Response response = client.getRequestConfig()
                            .pathParam("userId", userId)
                            .get("/api/users/{userId}");
        return response;
    }

    public Response createUser(CreateUserRequest requestBody){
        Response response = client.getRequestConfig()
                            .body(requestBody)
                            .post("/api/users");
        return response;
    }

    public Response getUserByPage(int page){
        Response response = client.getRequestConfig()
                            .queryParam("page", page)
                            .get("api/users");
        return response;
    }

    public Response updateUser(int userId, CreateUserRequest request){
        Response response = client.getRequestConfig()
                            .pathParam("userId", userId)
                            .body(request)
                            .log().all()
                            .put("/api/users/{userId}");
        return response;
    }

    public Response deleteUser(int userId){
        Response response = client.getRequestConfig()
                            .pathParam("userId", userId)
                            .delete("/api/users/{userId}");
        return response;
    }
}
