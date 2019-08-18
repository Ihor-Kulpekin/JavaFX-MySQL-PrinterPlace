package sample.model.modelsForSingleTables;



public class Service {

    private int id_Service;
    private String name;


    public int getId_Service() {
        return id_Service;
    }

    public void setId_Service(int id_Service) {
        this.id_Service = id_Service;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return    name
                ;
    }
}
