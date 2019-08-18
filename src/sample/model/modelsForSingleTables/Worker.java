package sample.model.modelsForSingleTables;

public class Worker {

    private int id_Worker;
    private String firstNameWorker;
    private String lastNameWorker;
    private String positionWorker;

    public int getId_Worker() {
        return id_Worker;
    }

    public void setId_Worker(int id_Worker) {
        this.id_Worker = id_Worker;
    }

    public String getFirstNameWorker() {
        return firstNameWorker;
    }

    public void setFirstNameWorker(String firstNameWorker) {
        this.firstNameWorker = firstNameWorker;
    }

    public String getLastNameWorker() {
        return lastNameWorker;
    }

    public void setLastNameWorker(String lastNameWorker) {
        this.lastNameWorker = lastNameWorker;
    }

    public String getPositionWorker() {
        return positionWorker;
    }

    public void setPositionWorker(String positionWorker) {
        this.positionWorker = positionWorker;
    }

    @Override
    public String toString() {
        return String.valueOf(id_Worker);
    }
}
