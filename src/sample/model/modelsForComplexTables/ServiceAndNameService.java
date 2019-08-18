package sample.model.modelsForComplexTables;

public class ServiceAndNameService {

    private String namesService;
    private String kindNameService;
    private float priceService;
    private float averagePriceGood;

    public float getAveragePriceGood() {
        return averagePriceGood;
    }

    public void setAveragePriceGood(float averagePriceGood) {
        this.averagePriceGood = averagePriceGood;
    }

    public String getNamesService() {
        return namesService;
    }

    public void setNamesService(String namesService) {
        this.namesService = namesService;
    }

    public String getKindNameService() {
        return kindNameService;
    }

    public void setKindNameService(String kindNameService) {
        this.kindNameService = kindNameService;
    }

    public float getPriceService() {
        return priceService;
    }

    public void setPriceService(float priceService) {
        this.priceService = priceService;
    }
}
