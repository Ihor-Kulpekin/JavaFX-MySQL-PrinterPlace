package sample.model.modelsForSingleTables;

public class Client {

    private int id_Client;
    private String firstNameClient;
    private String lastNameClient;
    private int codeClient;
    private String electronicPostOffice;
    private String numberPhone;

    public int getId_Client() {
        return id_Client;
    }

    public void setId_Client(int id_Client) {
        this.id_Client = id_Client;
    }

    public String getFirstNameClient() {
        return firstNameClient;
    }

    public void setFirstNameClient(String firstNameClient) {
        this.firstNameClient = firstNameClient;
    }

    public String getLastNameClient() {
        return lastNameClient;
    }

    public void setLastNameClient(String lastNameClient) {
        this.lastNameClient = lastNameClient;
    }

    public int getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(int codeClient) {
        this.codeClient = codeClient;
    }

    public String getElectronicPostOffice() {
        return electronicPostOffice;
    }

    public void setElectronicPostOffice(String electronicPostOffice) {
        this.electronicPostOffice = electronicPostOffice;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    @Override
    public String toString() {
        return String.valueOf(id_Client);
    }
}
