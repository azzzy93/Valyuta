package kg.geektech.valyuta;

public class MainModel {
    private String valyuta, kurs;

    public MainModel(String valyuta, String kurs) {
        this.valyuta = valyuta;
        this.kurs = kurs;
    }

    public String getValyuta() {
        return valyuta;
    }

    public String getKurs() {
        return kurs;
    }
}
