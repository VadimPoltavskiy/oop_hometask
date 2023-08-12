package FamalyTree.human;

import java.io.Serializable;

public class Fio  implements Serializable{
    private String name;
    private String surname;
    private String patronymic;

    public Fio(String surname, String name, String patronymic) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }
  
    public String getFio() {
        StringBuilder sb = new StringBuilder();
        sb.append(surname);
        sb.append(" ");
        sb.append(name);
        sb.append(" ");
        sb.append(patronymic);
        sb.append(" ");
        return sb.toString();
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getPatronymic() {
    if(patronymic == null){
        patronymic = "Отсутствует";
        return patronymic;
    }
        return patronymic;
    }


    public void setName(String name) {
         this.name = name;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String toString(){
        return "Фамилия: " + surname + ", Имя: " + name + ", Отчество: " + patronymic;
    }

}
