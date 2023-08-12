package FamalyTree.FamalyTree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import FamalyTree.human.Gender;
import FamalyTree.human.Human;

public class FamalyTree implements Serializable{
    private List<Human> humanList;

    public FamalyTree(List<Human> humanList){
        this.humanList = humanList;
    }
    public FamalyTree(){
        this(new ArrayList<>());
    }
    public boolean add(Human human){
        if (human == null) {
            return false;
        }
        if (!humanList.contains(human)){
            humanList.add(human);
            addToParents(human);
            addToChildren(human);

            return true;
        }
        return false;
    }
    private void addToParents (Human human) {
        for (Human parent: human.getParents()){
            parent.addChild(human);
        }
    }
    private void addToChildren (Human human) {
        for (Human child: human.getChildren()){
            child.addChild(human);
        }
    }
    public String getSiblings(String surname, String name, String patronymic){
        Human human = getByFio(surname, name, patronymic);
        if (human == null){
            return null;
        }
        StringBuilder res = new StringBuilder();
        int count = 1;
        res.append("Браться и сестры ");
        res.append(human.getShotinfo());
        res.append(": \n");
        for (Human parent: human.getParents()){
            for(Human child: parent.getChildren()){
                if(!child.equals(human)){
                    res.append(count++);
                    res.append(". ");
                    res.append(child.getShotinfo());
                    res.append('\n');
                }
            }
        }
        return res.toString();
    }
    public Human getByFio(String surname, String name, String patronymic){
        for(Human human: humanList) {
            if (human.getSurname().equalsIgnoreCase(surname) && human.getName().equalsIgnoreCase(name) && human.getPatronymic().equalsIgnoreCase(patronymic)){
                return human;
            }
        }
        return null;
    }
    public boolean setWedding(String surname1, String name1, String patronymic1, String surname2, String name2, String patronymic2){
        if(checkByFio(surname1, name1, patronymic1) && checkByFio(surname2, name2, patronymic2)){
            Human human1 = getByFio(surname1, name1, patronymic1);
            Human human2 = getByFio(surname2, name2, patronymic2);
            if (human1.getSpouse() == null && human2.getSpouse() == null){
                human1.setSpouse(human2);
                human2.setSpouse(human1);
            }
            if (human1.getGender() == Gender.Female){
                human1.setSurname(human2.getSurname());
            } else {
                human2.setSurname(human1.getSurname());
            } 
             } else {
                return false;
            }
        return false;
    }
    public boolean setDivorce(String surname1, String name1, String patronymic1, String surname2, String name2, String patronymic2){
        if(checkByFio(surname1, name1, patronymic1) && checkByFio(surname2, name2, patronymic2)){
            Human human1 = getByFio(surname1, name1, patronymic1);
            Human human2 = getByFio(surname2, name2, patronymic2);
            if (human1.getSpouse() != null && human2.getSpouse() != null){
                human1.setSpouse(null);
                human2.setSpouse(null);
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean remove(String surname, String name, String patronymic) {
        if(checkByFio(surname, name, patronymic)){
            Human e = getByFio(surname, name, patronymic);
            return humanList.remove(e);
        }
        return false;
    }


   public boolean checkByFio(String surname, String name, String patronymic){
        for (Human human: humanList){
          if(human.getSurname().equalsIgnoreCase(surname) && human.getName().equalsIgnoreCase(name) && human.getPatronymic().equalsIgnoreCase(patronymic)){
            return true;
          }  
        }
        return false;
        
    }
    
    public String getInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append("В дереве ");
        sb.append(humanList.size());
        sb.append(" объект(а): \n");
        for (Human human: humanList){
            sb.append(human);
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString(){
         return getInfo();
    }
}

