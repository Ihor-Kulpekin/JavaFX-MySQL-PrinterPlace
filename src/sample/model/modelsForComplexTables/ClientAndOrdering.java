package sample.model.modelsForComplexTables;

public class ClientAndOrdering {

   private String firstNameClient;
   private String lastNameClient;
   private String buyGood;
   private float generalSum;

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

    public String getBuyGood() {
        return buyGood;
    }

    public void setBuyGood(String buyGood) {
        this.buyGood = buyGood;
    }

    public float getGeneralSum() {
        return generalSum;
    }

    public void setGeneralSum(float generalSum) {
        this.generalSum = generalSum;
    }

}
