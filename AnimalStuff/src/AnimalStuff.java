import java.util.ArrayList;
import java.util.Scanner;

public class AnimalStuff {
    public static void main (String[] args) {
        Scanner end = new Scanner(System.in);
        Animal dog1 = new Dog();
        Animal dog2 = new Dog();
        Animal cat1 = new Cat();
        ArrayList<Animal> allAnimals = new ArrayList<Animal>();
        Comparison comp1 = new Comparison();

        allAnimals.add(dog1);
        allAnimals.add(dog2);
        allAnimals.add(cat1);

        dog1.setTag(1);
        dog2.setTag(2);
        cat1.setTag(1);

        String s = "";
        while (!(s.equals("end")) && !(s.equals("End"))) {

            setParams(dog1, dog2, cat1);


            for (Animal loopAnimal : allAnimals) {
                printThings(loopAnimal);
            }

            System.out.print('\n');

            comp1.speedCompIntra(dog2, cat1);
            comp1.speedCompIntra(dog1, dog2);
            comp1.speedCompIntra(dog1, cat1);
            System.out.print('\n');
            comp1.speedCompInter(dog1, dog2);

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

    public static void setParams(Animal a, Animal b, Animal c) {
        InputValidation validate = new InputValidation();
        Scanner name = new Scanner(System.in);

        a.setParameters();
        b.setParameters();
        c.setParameters();

        System.out.print("Set name for " + a.getSpecies() + " " + a.getTag() + ": ");
        a.setName(name.nextLine());
        System.out.print("Set speed for " + a.getName() + ": ");
        a.setSpeed(validate.inputValidate());
        System.out.print('\n');

        System.out.print("Set name for " + b.getSpecies() + " " + b.getTag() + ": ");
        b.setName(name.nextLine());
        System.out.print("Set speed for " + b.getName() +  ": ");
        b.setSpeed(validate.inputValidate());
        System.out.print('\n');

        System.out.print("Set name for " + c.getSpecies() + " " + c.getTag() + ": ");
        c.setName(name.nextLine());
        System.out.print("Set speed for " + c.getName() +  ": ");
        c.setSpeed(validate.inputValidate());
        System.out.print('\n');

        System.out.println("");
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

class Comparison{
    CapitalL ca = new CapitalL();
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