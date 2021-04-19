import java.util.ArrayList;
import java.util.Scanner;

public class AnimalStuff {
    public static void main (String[] args) {
        Scanner end = new Scanner(System.in);
        Animal dog1 = new Dog();
        Animal dog2 = new Dog();
        Animal cat1 = new Cat();
        Animal cat2 = new Cat();
        Animal wolf1 = new Wolf();
        Animal wolf2 = new Wolf();
        ArrayList<Animal> allAnimals = new ArrayList<Animal>();
        ArrayList<Animal> dogs = new ArrayList<Animal>();
        ArrayList<Animal> cats = new ArrayList<Animal>();
        ArrayList<Animal> wolves = new ArrayList<Animal>();
        Comparison comp1 = new Comparison();

        dogs.add(dog1);
        dogs.add(dog2);
        cats.add(cat1);
        cats.add(cat2);
        wolves.add(wolf1);
        wolves.add(wolf2);

        allAnimals.addAll(dogs);
        allAnimals.addAll(cats);
        allAnimals.addAll(wolves);

        dog1.setTag(1);
        dog2.setTag(2);
        cat1.setTag(1);
        cat2.setTag(2);
        wolf1.setTag(1);
        wolf2.setTag(2);

        String s = "";
        while (!(s.equals("end")) && !(s.equals("End"))) {

           for (Animal setParamAll : allAnimals) {
               setParams(setParamAll);
           }

           for (Animal loopAnimal : allAnimals) {
               printThings(loopAnimal);
           }

           System.out.print('\n');

           comp1.compareInter(allAnimals.size(), allAnimals);

           System.out.print('\n');

           comp1.compareIntra(dogs.size(), dogs);
           comp1.compareIntra(cats.size(), cats);

           System.out.print('\n');
           System.out.print("Type end to end, anything else to restart: ");
           s = end.nextLine();
           System.out.print('\n');
       }
    }



    public static void printThings(Animal an) {
        CapitalL cp = new CapitalL();
        System.out.print(cp.capitalizer(an.getName()) + " is " + an.movement().toLowerCase() +
                " while saying " + an.makeNoise() + '\n');
    }

    public static void setParams(Animal a) {
        InputValidation validate = new InputValidation();
        Scanner name = new Scanner(System.in);

        a.setParameters();

        System.out.print("Set name for " + a.getSpecies() + " " + a.getTag() + ": ");
        a.setName(name.nextLine());
        System.out.print("Set speed for " + a.getName() + ": ");
        a.setSpeed(validate.inputValidate());
        System.out.print('\n');
    }

    public static ArrayList<Animal> addToAll(ArrayList<Animal> merge) {
        ArrayList<Animal> all = new ArrayList<Animal>();
        for (Animal a : merge) {
            all.add(a);
        }
        return all;
    }

}

abstract class Animal {
    private int speed;
    private String species;
    private String name;
    private int tag;


    public void setSpeed(int s) {
        speed = s;
    }

    public int getSpeed() {
        return speed;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String s) {
        species = s;
    }

    public void setName(String nm) {
        name = nm;
    }

    public String getName() {
        return name;
    }

    public void setTag(int t) {
        tag = t;
    }

    public int getTag() {
        return tag;
    }

    abstract public String makeNoise();

    abstract public void setParameters();

    public String movement() {
        if (speed <= 0) {
            return "Standing";
        } else if (speed <= 20) {
            return "Walking";
        } else {
            return "Running";
        }
    }
}

class Dog extends Animal {

    public void setParameters() {
        setSpeed(0);
        setSpecies("Dog");
    }

    public String makeNoise() {
        return "Hav Hav";
    }
}

class Cat extends Animal {
    public void setParameters() {
        setSpeed(0);
        setSpecies("Cat");
    }

    public String makeNoise() {
        return "Miyav";
    }
}

class Wolf extends Animal {
    public void setParameters() {
        setSpeed(0);
        setSpecies("Wolf");
    }

    public String makeNoise() {
        return "Aauuuu";
    }
}

class Comparison{
    CapitalL ca = new CapitalL();

    public static void compareIntra(int size, ArrayList<Animal> species) {
        Comparison comp = new Comparison();
        for (int c1 = 0; c1 < size; ++c1) {
            for (int c2 = c1 + 1; c2 < size; ++c2) {
                comp.speedCompIntra(species.get(c1), species.get(c2));
            }
        }
    }

    public static void compareInter(int sizeAll, ArrayList<Animal> allOfThem) {
        Comparison comp = new Comparison();
        for (int c1 = 0; c1 < sizeAll; ++c1) {
            for (int c2 = c1 + 1; c2 < sizeAll; ++c2) {
                comp.speedCompIntra(allOfThem.get(c1), allOfThem.get(c2));
            }
        }
    }

    public void speedCompIntra(Animal a, Animal b) {

        if (a.getSpeed() < b.getSpeed()) {
            System.out.println(ca.capitalizer(a.getName()) + " is slower than " + ca.capitalizer(b.getName()));
        } else if (a.getSpeed() == b.getSpeed()) {
            System.out.println(ca.capitalizer(a.getName()) + " is the same speed as " + ca.capitalizer(b.getName()));
        } else {
            System.out.println(ca.capitalizer(a.getName()) + " is faster than " + ca.capitalizer(b.getName()));
        }
    }

    public void speedCompInter(Animal a, Animal b) {
        if (a.getSpeed() < b.getSpeed()) {
            System.out.println(a.getSpecies() + " " + a.getTag() + " is slower than " + b.getSpecies()+ " " +
                    b.getTag());
        } else if (a.getSpeed() == b.getSpeed()) {
            System.out.println(a.getSpecies() + " " + a.getTag() + " is the same speed as " + b.getSpecies() + " " +
                    b.getTag());
        } else {
            System.out.println(a.getSpecies() + " " + a.getTag() + " is faster than " + b.getSpecies()+ " " +
                    b.getTag());
        }
    }
}

class CapitalL {
    public String capitalizer(String input) {
        String capInput = input.substring(0, 1).toUpperCase() + input.substring(1);
        return capInput;
    }
}

class InputValidation {

    public static int inputValidate() {
        Scanner vs = new Scanner(System.in);
        int input;

        do {
            while (!vs.hasNextInt()) {
                System.out.println("Invalid input. Try again.");
                vs.next();
            }

            input = vs.nextInt();

            if (input < 0) {
                System.out.println("Number must be at least 0. Try again.");
            }

        } while (input < 0);

        return input;
    }
}