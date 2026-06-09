package api;

import api.specifications.ResponseSpecs;
import io.restassured.response.Response;
import models.CreateUserRequest;
import models.CreateUserResponse;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserApiTest {
    UserEndpoints userapi = new UserEndpoints();
    @Test
    public void validateGetUserResponse(){
        Response response = userapi.getUser(2);
        System.out.println("response: " + response.asPrettyString());
        String name = response.jsonPath().getString("data.first_name");
        response.then()
                        .spec(
                                ResponseSpecs.successResponse()
                        );
        Assert.assertEquals(name, "Janet");
    }

    @Test(dataProvider = "users")
    public void ValidateCreateUserApi(String email, String fname, String lname){
        CreateUserRequest request = new CreateUserRequest();
        request.setEmail(email);
        request.setFirstName(fname);
        request.setLastName(lname);
        Response response = userapi.createUser(request);
        System.out.println(response.asPrettyString());
        response.then()
                .spec(
                        ResponseSpecs.createSuccessResponse()
                );
        CreateUserResponse resobj = response.as(CreateUserResponse.class);
        System.out.println(".class :" + CreateUserResponse.class);
        int id = resobj.getId();
        System.out.println("User id: "+id);
    }

    @Test
    public void ValidateUsersByPage(){
        Response response = userapi.getUserByPage(2);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void ValidateUserUpdate(){
        CreateUserRequest request = new CreateUserRequest();
        request.setFirstName("test");
        Response response = userapi.updateUser(2, request);
        response.then()
                .spec(
                        ResponseSpecs.successResponse()
                );
    }

    @Test(dataProvider = "userIds")
    public void ValidateDeleteUser(int id){
        Response response = userapi.deleteUser(id);
        response.then()
                .spec(
                        ResponseSpecs.deleteSuccessResponse()
                );
    }

    @Test
    public void ValidateNegativeScenario(){
        ApiClient client = new ApiClient();
        Response response = client.getRequestConfig()
                .post("/api/users");
        response.then()
                .spec(
                        ResponseSpecs.invalidResponse()
                );
    }

    @DataProvider(name = "userIds")
    public Object[][] userid(){
        return new Object[][]{
                {2},
                {3},
                {4}
        };
    }

    @DataProvider(name = "users")
    public Object[][] users(){
        return new Object[][]{
                {"melody.higgins@baldwin.com", "Melody", "Higgins"},
                {"test1@test.com", "test", "1"},
                {"test2@test.com", "test", "2"}
        };
    }
}
