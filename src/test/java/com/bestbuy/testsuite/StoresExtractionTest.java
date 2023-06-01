package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }


    // 1. Extract the limit
    @Test
    public void extractLimit() {
        int limit = response.extract().path("limit");
        System.out.println("The value of limit is : " + limit);

    }

    //2. Extract the total
    @Test
    public void extractTheTotal() {
        int total = response.extract().path("total");
        System.out.println("The total is : " + total);

    }

    //3. Extract the name of 5th store
    @Test
    public void extractNameOf5thStore() {
        String name = response.extract().path("data[4].name");
        System.out.println("The name of the 5th store is : " + name);
    }

    //4. Extract the names of all the store
    @Test
    public void extractNamesOfAllStores() {
        List<String> storeNames = response.extract().path("data.name");
        System.out.println("Names of all the stores are : " + storeNames);
    }

    //5. Extract the storeId of all the store
    @Test
    public void extractStoreIdOfAllStores() {
        List<Integer> storeIdList = response.extract().path("data.id");
        System.out.println("Store Id of all the store are : " + storeIdList);
    }

    //6. Print the size of the data list
    @Test
    public void printSizeOfDataList() {
        int sizeList = response.extract().path("data.size");
        System.out.println("The size of the data list is : " + sizeList);
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void getAllValuesOfStoresWhere() {
        List<HashMap<String, ?>> allTheValues = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("All the value of the stores where stores name is St Cloud : " + allTheValues);
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void getAddressOfStore() {
        List<HashMap<String, ?>> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("The address of the store where store name is Rochester : " + address);
    }

    //9. Get all the services of 8th store
    @Test
    public void GetAllTheServices() {
        List<HashMap<String, ?>> services = response.extract().path("data[7].services");
        System.out.println("All the services of the 8th store are : " + services);
    }

    //10. Get store services of the store where service name = Windows Store
    @Test
    public void getStoreServices() {
        List<HashMap<String, ?>> storeServices = response.extract().path("data.findAll{it.services.findAll{it.name== 'Windows Store'}}.services.storeservices");
        System.out.println("The store services of the store where service name = Windows store  : " + storeServices);
    }

    //11. Get all the storeId of all the store
    @Test
    public void getAllTheStoreIds() {
        List<Integer> storeIdList = response.extract().path("data.services.storeservices.storeId");
        System.out.println("The store Id of all the stores are : " + storeIdList);
    }

    //13. Find the store names Where state = ND
    @Test
    public void findTheStoreNames() {
        List<String> storeName = response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println("The store names where state is ND : " + storeName);
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void findTheTotalNumberOfServices() {
        List<Integer> totalServices = response.extract().path("data[8].services");
        System.out.println("The total number of services for the store where store name is Rochester   : " + totalServices.size());


    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void findTheCreateAtForAllServices() {
        List<?> createdAtAllServicesList = response.extract().path("data.find{it.services}.services.findAll{it.name='Windows Store'}.storeservices.createdAt");
        System.out.println("The createdAt for all services whose name is Windows Store   : " + createdAtAllServicesList.size());
        System.out.println("-------------------- End Of The Test ----------------------");
    }

    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void findTheNameOfAllServicesWhereStoreNameIsFargo() {
        List<String> nameOfAllServicesList = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");
        System.out.println("The name of all services Where store name is Fargo   : " + nameOfAllServicesList);
    }

    //17. Find the zip of all the store
    @Test
    public void findZipOfAllTheStores() {
        List<String> zipList = response.extract().path("data.zip");
        System.out.println("The zip of all the store  : " + zipList);
    }

    //18. Find the zip of store name = Roseville
    @Test
    public void findZipOfStoreName() {
        List<String> zip = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("The zip of store name is Roseville  : " + zip);
    }

    //19. Find the store services details of the service name = Magnolia Home Theater
    @Test
    public void findStoreServicesDetails() {
        List<HashMap<String,?>> storeServicesOfServiceName = response.extract().path("data.findAll{it.services.find{it.name=='Magnolia Home Theater'}!=null}.services.storeservices");
        System.out.println("The store services details of the service name is Magnolia Home Theater  : " + storeServicesOfServiceName);
    }

    //20. Find the lat of all the stores
    @Test
    public void findLatOfAllTheStores() {
        List<Double> latOfAllStores = response.extract().path("data.lat");
        System.out.println("The lat of all the stores  : " + latOfAllStores);
        System.out.println("The size of lat of all the stores  : " + latOfAllStores.size());
    }
}
