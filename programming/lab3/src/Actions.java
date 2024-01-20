import Stuff.*;
import Stuff.Character;

public class Actions {
    public static void start() {
        Character frekensnork = new FrekenSnork();
        Figure stranger = new Stranger();

        System.out.println(frekensnork.getName() + " увидела " + stranger.getName() + ".");

        if (Moves.isShivering(frekensnork)){
            System.out.println(frekensnork.getName() + " от волнения" + ((Shivering) frekensnork).shiver());
        } else{
            System.out.println(frekensnork.getName() + ", смело направилась к ней");
        }

        if (Moves.beauty(stranger)){
            System.out.println(frekensnork.getName() + " поняла: 'Это идеальный подарок!'");
        } else{
            System.out.println(frekensnork.getName() + " подумала: 'Так дарить нельзя, нужно отремонтировать!' ");
        }

        Moves.sizeDifference(frekensnork, stranger);

        System.out.println(stranger.getName() + ((Buoyancy) stranger).swim() + ", поэтому " + frekensnork.getName() + " доберется до берега на ней");


        if (Moves.enoughStrength(frekensnork,stranger)){
            frekensnork.changeStrength(1);
            System.out.println("\n" + frekensnork.getName() + " несет " + stranger.getName() + "\nПонижен уровень силы");
            Moves.getStrength(frekensnork);
            if (frekensnork.getStrength() > 0){
                System.out.println("\nСил хватит. Подарок будет доставлен");
            } else{
                System.out.println("\nСилы кончились. Потребуется отдых");
            }
        }else{
            System.out.println("\nПридется просить друзей о помощи.");
        }




    }
}

