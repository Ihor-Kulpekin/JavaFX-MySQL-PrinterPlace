package sample.model.modelsForComplexTables;

public class NameServiceAndClient {

    private String firstName;
    private String lastName;
    private String serviceName;
    private int counter;
    private float generalSum;
    private int client_Id;


    public NameServiceAndClient(){

    }

    public NameServiceAndClient(String firstName, String lastName, String serviceName, int counter, float generalSum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.serviceName = serviceName;
        this.counter = counter;
        this.generalSum = generalSum;
    }

    public int getClient_Id() {
        return client_Id;
    }

    public void setClient_Id(int client_Id) {
        this.client_Id = client_Id;
    }

    public float getGeneralSum() {
        return generalSum;
    }

    public void setGenetalSum(float generalSum) {
        this.generalSum = generalSum;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
