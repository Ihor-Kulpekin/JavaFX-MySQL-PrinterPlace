package sample.model.modelsForComplexTables;


import java.sql.Date;

public class NameServiceAndOrdering {

    //Data for table of average of number of solt good
    private String serviceKind;
    private float averageNumberOrdering;
    private int counter;



    //Data for table of bought good in choice day and table of made ordering for all time
    private Date dateOrdering;
    private float sumAllOrdering;
    private int sumNumberGoods;

    public Date getDateOrdering() {
        return dateOrdering;
    }

    public void setDateOrdering(Date dateOrdering) {
        this.dateOrdering = dateOrdering;
    }

    public float getSumAllOrdering() {
        return sumAllOrdering;
    }

    public void setSumAllOrdering(float sumAllOrdering) {
        this.sumAllOrdering = sumAllOrdering;
    }

    public int getSumNumberGoods() {
        return sumNumberGoods;
    }

    public void setSumNumberGoods(int sumNumberGoods) {
        this.sumNumberGoods = sumNumberGoods;
    }

    public String getKindService() {
        return serviceKind;
    }

    public void setKindService(String serviceKind) {
        this.serviceKind = serviceKind;
    }

    public float getAverageNumberOrdering() {
        return averageNumberOrdering;
    }

    public void setAverageNumberOrdering(float averageNumberOrdering) {
        this.averageNumberOrdering = averageNumberOrdering;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

}
