package FamalyTree.human;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

//import java.time.LocalDate;
//import java.util.List;

public class Human extends Fio {
    private Gender gender;
    private LocalDate birthDay;
    private LocalDate dayOfDeath;
    private List<Human> parents;
    private List<Human> children;
    private Human spouse;

  public Human (String surname, String name, String patronymic , Gender gender, LocalDate birthDay,
    LocalDate dayOfDeath, Human father,  Human mother) {
      super(surname, name, patronymic);
      this.gender = gender;
      this.birthDay = birthDay;
      this.dayOfDeath = dayOfDeath;
      parents = new ArrayList();
      if (father != null){
        parents.add(father);
      }
      if (mother != null){
        parents.add(mother);
      }
      children = new ArrayList<>();

    }

  public Human (String surname, String name, String patronymic , Gender gender, LocalDate birthDay) {
    this(surname, name, patronymic, gender, birthDay, null, null, null);
  }
    public Human (String surname, String name, String patronymic , Gender gender, LocalDate birthDay, Human father, Human mother) {
    this(surname, name, patronymic, gender, birthDay, null, father, mother);
  }

  public boolean addChild(Human child){
    if(!children.contains(child)){
      children.add(child);
      child.addParent(this);
      return true;
    }
    return false;
  }
  
  public boolean addParent(Human parent){
    if (!parents.contains(parent)){
      parents.add(parent);
      parent.addChild(this);
      return true;
    }
    return false;
  }
  public Human getFather() {
    for (Human parent: parents){
      if (parent.getGender() == Gender.Male){
        return parent;
      }
    }
    return null;
  }
    public Human getMother() {
    for (Human parent: parents){
      if (parent.getGender() == Gender.Female){
        return parent;
      }
    }
    return null;
  }
  private int getPeriod (LocalDate birtDay, LocalDate dayOfDeath) {
    Period diff = Period.between(birtDay, dayOfDeath);
    return diff.getYears();
  }

  public int getAge() {
    if (dayOfDeath == null) {
      return getPeriod(birthDay, LocalDate.now());
    } else {
      return getPeriod(birthDay, dayOfDeath);
    }
  }
  public void setSpouse(Human spouse) {
      this.spouse = spouse;
  }
  public Human getSpouse() {
      return spouse;
  }
  public LocalDate getBirthDay() {
      return birthDay;
  }
  public LocalDate getDayOfDeath() {
      return dayOfDeath;
  }
  public List<Human> getParents() {
      return parents;
  }
  public List<Human> getChildren() {
      return children;
  }
  public void setBirthDay(LocalDate birthDay) {
      this.birthDay = birthDay;
  }
  public void setDayOfDeath(LocalDate dayOfDeath) {
      this.dayOfDeath = dayOfDeath;
  }
  public Gender getGender() {
      return gender;
  }
  @Override
    public String toString(){
        return getInfo();
    }
    public String getShotinfo(){
      StringBuilder sb = new StringBuilder();
      sb.append(super.getSurname());
      sb.append(" ");
      sb.append(super.getName());
      sb.append(" ");
      sb.append(super.getPatronymic());
      return sb.toString();
    }
    
    public String getInfo(){
      StringBuilder sb = new StringBuilder();
      sb.append(super.toString());
      sb.append(", Пол: ");
      sb.append(getGender());
      sb.append(", Возраст: ");
      sb.append(getAge());
      sb.append(", ");
      sb.append(getSpouseInfo());
      sb.append(", ");
      sb.append(getMotherInfo());
      sb.append(", ");
      sb.append(getFatherInfo());
      sb.append(", ");
      sb.append(getChildrenInfo());
      return sb.toString();
    }

  public String getSpouseInfo() {
    String res = "Супруг(а): ";
    if (spouse == null) {
      res += "нет";
    } else {
      res += spouse.getFio();
    }
    return res;
  }

    public String getMotherInfo() {
    String res = "Мать: ";
    Human mother = getMother();
    if (mother != null) {
      res += mother.getFio();
    } else {
      res += "неизвестна";
    }
    return res;
  }

    public String getFatherInfo() {
    String res = "Отец: ";
    Human father = getFather();
    if (father != null) {
      res += father.getFio();
    } else {
      res += "неизвестен";
    }
    return res;
  }
  public String getChildrenInfo() {
    StringBuilder res = new StringBuilder();
    res.append("дети: ");
    if (children.size() != 0){
      res.append(children.get(0).getFio());
      for (int i = 1; i < children.size(); i++){
        res.append(", ");
        res.append(children.get(i).getFio());
      }
    } else{
        res.append("отсутствуют");
    }
    return res.toString();
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Human)) {
      return false;
    }
    Human human = (Human) obj;
    return human.getFio() == getFio();
  }
}

