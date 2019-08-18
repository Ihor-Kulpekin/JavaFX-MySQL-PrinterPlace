package sample.model.modelsForSingleTables;

public class NameService {

    private int Id_NameService;
    private String name;
    private String kindService;
    private float priceService;

    public int getId_NameService() {
        return Id_NameService;
    }

    public void setId_NameService(int id_NameService) {
        Id_NameService = id_NameService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKindService() {
        return kindService;
    }

    public void setKindService(String kindService) {
        this.kindService = kindService;
    }

    public float getPriceService() {
        return priceService;
    }

    public void setPriceService(float priceService) {
        this.priceService = priceService;
    }

    @Override
    public String toString() {
        return kindService ;
    }
}
