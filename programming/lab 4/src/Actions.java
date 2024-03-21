import Exceptions.JewelleryException;
import Stuff.*;


public class Actions {
    public static void start() throws Exception {
        FrekenSnork frekensnork = new FrekenSnork();
        FrekenSnork.Bangs bangs = frekensnork.new Bangs();
        MoomyTroll moomyTroll = new MoomyTroll();
        Figure stranger = new Stranger();
        Sea sea = new Sea();
        Gift gift = new Gift();

        //unchecked exception - extends  RuntimeException
       Moves.MakePresent(frekensnork, moomyTroll);
        //Moves.MakePresent(frekensnork, frekensnork);

        Moves.GiftTypeIsJewelry(gift);
        //System.out.println(gift.getName());


        //checked exception - extends Exception
        try {
            Moves.CheckGiftType(frekensnork, gift);
        } catch (JewelleryException e) {
            System.err.println("Фрекен Снорк придется искать другой подарок.");
            System.exit(0);
        }


        interface Friends{
            public void BeSurprised();
        }


        //анонимный класс, переопределили метод BeSurprised
        Friends goodFriends = new Friends() {
            public void BeSurprised() {
                System.out.println("Некоторые друзья ахнут от удивления!");
            }
        };

        Friends badFriends = new Friends() {
            public void BeSurprised() {
                System.out.println("Некоторые друзья ахнут от зависти!");
            }
        };

        if (Moves.areBangsBurnt(bangs)){
            System.out.println("\n" + frekensnork.getName() + " спалила челку, поэтому расстроена.");
            Moves.MakeWreath(frekensnork);
        } else {
            System.out.println("\n" + frekensnork.getName() + ": ee челка в порядке.");
        }


        goodFriends.BeSurprised();
        badFriends.BeSurprised();


        System.out.println("\n" + frekensnork.getName() + " увидела " + stranger.getName() + ".");

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
            if (frekensnork.getStrength() > 5){
                System.out.println("\nСил хватит. Подарок будет доставлен");
            } else{
                System.out.println("\nСилы кончились. Потребуется отдых");
            }
        }else{
            System.out.println("\nПридется просить друзей о помощи.");
        }

        if (Moves.isStormy(sea)){
            System.out.println("\nВолны усиливались, начинался шторм.");
        } else{
            System.out.println("\nМоре было спокойным, ничего не угрожало Фрекен Снорк.");
        }

    }
}

