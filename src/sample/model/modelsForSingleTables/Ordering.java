package sample.model.modelsForSingleTables;

import java.sql.Date;

public class Ordering {

    private int id_Ordering;
    private Date dataOrderingService;
    private int numberService;
    private int discount;
    private int codeOrdering;
    private int generalSumOrdering;
    private int clientId;
    private int workerId;
    private String nameKindService;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public String getNameKindService() {
        return nameKindService;
    }

    public void setNameKindService(String nameKindService) {
        this.nameKindService = nameKindService;
    }

    public int getId_Ordering() {
        return id_Ordering;
    }

    public void setId_Ordering(int id_Ordering) {
        this.id_Ordering = id_Ordering;
    }

    public Date getDataOrderingService() {
        return dataOrderingService;
    }

    public void setDataOrderingService(Date dataOrderingService) {
        this.dataOrderingService = dataOrderingService;
    }

    public int getNumberService() {
        return numberService;
    }

    public void setNumberService(int numberService) {
        this.numberService = numberService;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getCodeOrdering() {
        return codeOrdering;
    }

    public void setCodeOrdering(int codeOrdering) {
        this.codeOrdering = codeOrdering;
    }

    public int getGeneralSumOrdering() {
        return generalSumOrdering;
    }

    public void setGeneralSumOrdering(int generalSumOrdering) {
        this.generalSumOrdering = generalSumOrdering;
    }
}