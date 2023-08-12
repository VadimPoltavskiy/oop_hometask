

import FamalyTree.human.Fio;
import FamalyTree.human.Gender;
import FamalyTree.human.Human;

import java.time.LocalDate;

import FamalyTree.FamalyTree.FamalyTree;

public class Main {
    public static void main(String[] args){
        FamalyTree famalyTree = testSustem();
        System.out.println(famalyTree);
    }

    static FamalyTree testSustem(){
        FamalyTree tree = new FamalyTree();
        Human pvv = new Human("Полтавский", "Вадим", "Викторович", Gender.Male, LocalDate.of(1993, 10, 29));
        Human jad = new Human("Жукова", "Анастасия", "Дмитриевна", Gender.Female, LocalDate.of(1992, 3, 29));
        Human venera = new Human("Полтавская", "Венера", "Вадимовна", Gender.Female, LocalDate.of(2018, 12, 4));
        Human olga = new Human("Полтавская", "Ольгя", "Олеговна", Gender.Female, LocalDate.of(2018, 12, 4));
        
        tree.add(pvv);
        tree.add(jad);
        tree.add(venera);
        tree.add(olga);
        olga.addParent(pvv);
        jad.addParent(pvv);
        venera.addParent(pvv);
        tree.setWedding("Полтавский", "Вадим", "Викторович","Жукова", "Анастасия", "Дмитриевна");
        tree.remove("Полтавский", "Вадим", "Викторович");
        return tree;
    
    }
}
