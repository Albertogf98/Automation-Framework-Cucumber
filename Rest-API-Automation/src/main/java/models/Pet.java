package models;

public class Pet extends PetBase {

    private String photo, status;

    public Pet(String name, int id, String photo, String status) {
        super(name, id);

        this.photo = photo;
        this.status = status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
