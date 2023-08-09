package dmitry.prikols.spring_course.models;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Gym {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGym_name() {
        return gym_name;
    }

    public void setGym_name(String gym_name) {
        this.gym_name = gym_name;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public Gym(String gym_name, String equipment) {
        this.gym_name = gym_name;
        this.equipment = equipment;
    }

    public Gym() {
    }

    @Size(min = 1, max = 50 , message = "Полное имя спортзала должно быть больше чем 1 и меньше чем 50")
    @NotEmpty
    private  String gym_name;

    @Size(min = 1, max = 200, message = "Перечень оборудование должно содержать от 1 до 200 символов ")
    @NotEmpty
    private String equipment;


}
