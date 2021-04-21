import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AnimalStuff {
    public static void main(String[] args) {
        AnimalBehaviour animalBehaviour = new AnimalBehaviour();
        animalBehaviour.start();
    }
}

class AnimalBehaviour {
    public void start() {
        Scanner end = new Scanner(System.in);
        Animal dog1 = new Dog();                //To add new animal, create an animal referenced object,
        Animal dog2 = new Dog();                //create an array for that animal's species
        Animal cat1 = new Cat();                //add that array to the allAnimals array list
        Animal cat2 = new Cat();                //use the tag setter function for that species
        Animal wolf1 = new Wolf();              //and the animal will be added to the program.
        Animal wolf2 = new Wolf();
        Animal turtle1 = new Turtle();
        Animal turtle2 = new Turtle();
        ArrayList<Animal> allAnimals = new ArrayList<Animal>();
        Animal[] dogs = {dog1, dog2};
        Animal[] cats = {cat1, cat2};
        Animal[] wolves = {wolf1, wolf2};
        Animal[] turtles = {turtle1, turtle2};
        Comparison comp1 = new Comparison();

        allAnimals.addAll(Arrays.asList(dogs));
        allAnimals.addAll(Arrays.asList(cats));
        allAnimals.addAll(Arrays.asList(wolves));
        allAnimals.addAll(Arrays.asList(turtles));

        tagSetter(dogs);
        tagSetter(cats);
        tagSetter(wolves);
        tagSetter(turtles);

        System.out.println(allAnimals);
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

            comp1.compareIntra(dogs.length, dogs);
            comp1.compareIntra(cats.length, cats);
            comp1.compareIntra(wolves.length, wolves);
            comp1.compareIntra(turtles.length, turtles);

            System.out.print('\n');
            System.out.print("Type end to end, anything else to restart: ");
            s = end.nextLine();
            System.out.print('\n');
        }
    }

    public void tagSetter(Animal[] as) {
        for (int c = 0; c < as.length; ++c) {
            Animal sa = as[c];
            sa.setTag(c + 1); //c+1 bc arrays start at 0, but the first obj is an animal, so it exists. cant be 0, so 1
        }
    }

    public void printThings(Animal an) {
        CapitalL cp = new CapitalL();
        System.out.print(cp.capitalizer(an.getName()) + " is " + an.movement().toLowerCase() +
                " while saying " + an.makeNoise() + '\n');
    }

    public void setParams(Animal a) {
        InputValidation validate = new InputValidation();
        Scanner name = new Scanner(System.in);

        System.out.print("Set name for " + a.getSpecies() + " " + a.getTag() + ": ");
        a.setName(name.nextLine());
        System.out.print("Set speed for " + a.getName() + ": ");
        a.setSpeed(validate.inputValidate());
        System.out.print('\n');
    }

    public ArrayList<Animal> addToAll(Animal[] merge) {
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
    private int walk_and_run_transition;

    Animal() {
        speed = 0;
        walk_and_run_transition = 20;
    }

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

    public void setWalk_and_run_transition(int wart) { walk_and_run_transition = wart; };

    public int getWalk_and_run_transition() { return walk_and_run_transition; }

    abstract public String makeNoise();

    public String movement() {
        if (getSpeed() <= 0) {
            return "Standing";
        } else if (getSpeed() <= walk_and_run_transition) {
            return "Walking";
        } else {
            return "Running";
        }
    }
}

class Dog extends Animal {

    Dog() {
        setSpecies("Dog");
    }

    public String makeNoise() {
        return "Hav Hav";
    }
}

class Cat extends Animal {
    Cat() {
        setSpecies("cat");
    }

    public String makeNoise() {
        return "Miyav";
    }
}

class Wolf extends Animal {
    Wolf() {
        setSpecies("Wolf");
    }

    public String makeNoise() {
        return "Aauuuu";
    }
}

class Turtle extends Animal {

    Turtle() {
        setSpecies("Turtle");
        setWalk_and_run_transition(5);
    }

    public String makeNoise() {
        return "absolutely nothing";
    }
}

class Comparison{
    CapitalL ca = new CapitalL();

    public static void compareIntra(int size, Animal[] species) {
        Comparison comp = new Comparison();
        for (int c1 = 0; c1 < size; ++c1) {
            for (int c2 = c1 + 1; c2 < size; ++c2) {
                comp.speedCompIntra(species[c1], species[c2]);
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