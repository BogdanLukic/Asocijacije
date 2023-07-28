package Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "resenje_kolona_d")
public class Column_D {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_konacno_resenje;
    private String name;
    private String one;
    private String two;
    private String three;
    private String four;

    public int getId_konacno_resenje() {
        return id_konacno_resenje;
    }

    public void setId_konacno_resenje(int id_konacno_resenje) {
        this.id_konacno_resenje = id_konacno_resenje;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public String getFour() {
        return four;
    }

    public void setFour(String four) {
        this.four = four;
    }

    public String getTextFromPlace(String place){
        switch (place){
            case "1":
                return getOne();
            case "2":
                return getTwo();
            case "3":
                return getThree();
            case "4":
                return getFour();
            default:
                return "";
        }
    }
}
