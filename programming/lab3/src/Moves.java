import Stuff.*;
import Stuff.Character;

class Moves {

    static void sizeDifference (Character a, Figure b){
       System.out.println("\n" + a.getName() + ": " + a.getSize());
        System.out.println(b.getName() + ": " + b.getSize());
    }
    static void courage (Character a){
        System.out.println("\nУровень смелости " + a.getName() + " по 10-ти балльной шкале: " + a.getCourage());
    }
    static boolean isShivering (Character a){
        if (Math.random()+ 0.3 >= 0.5){
            return true;
        } else{
            return false;
        }
    }

    static boolean beauty(Figure b){
        if (b.isBeautiful()){
            System.out.println("\n" + b.getName() + Beauty.STUNNING.getBeauty() + ". ");
            return true;
        } else{
            System.out.println("\n" + b.getName() + Beauty.SHABBY.getBeauty() + ". " );
            return false;
        }
    }
    static int getStrength(Character a){
        System.out.println("\nУровень силы " + a.getName() + ": " + a.getStrength());
        return a.getStrength();
    }
    static void downStrength(Character a){
        a.changeStrength(-1);
        System.out.println("\nСил стало меньше.");
        getStrength(a);
    }

    static boolean enoughStrength(Character a, Figure b){
        if (a.getMass() - b.getMass() > 0){
            System.out.println("\nСможет доставить.");
            return true;
        }else{
            System.out.println("\nНе сможет доставить.");
            return false;
        }
    }

}


















//    static void shivering(FrekenSnork x)
//    static boolean hasCome(FrekenSnork x, Stranger y) {
//        if (x.courage()) {
//            System.out.println("\nТакая громадная " + y.getName() + " напугала " + x.getName() + ", и она бросилась прочь");
//            return true;
//        } else {
//            System.out.println(y.getName() + "\n заинтересовала " + x.getName() + ", и она подошла ближе");
//            return false;
//        }
//    }
//    static boolean present(FrekenSnork x, Stranger y){
//        if (y.isBeautiful()){
//            System.out.println("\n" + y.getName() + Beauty.STUNNING.getBeauty() + ". "+ x.getName() + " поняла: 'Это идеальный подарок!'");
//            return true;
//        } else{
//            System.out.println("\n" + y.getName() + Beauty.SHABBY.getBeauty() + ". " + x.getName() + "подумала: 'Это нельзя дарить!' ");
//            return false;
//        }
//    }
//    static void lookingAt (FrekenSnork x, Stranger y){
//        System.out.println("\n" + x.getName() + " рассматривала " + y.getName());
//    }
//    static void sizeDifference (Character a){
//        System.out.println("\n" + a.getName() + " :" + a.getSize());
//    }
//
//    static void whatRace (Character a){
//        System.out.println("\n" + a.getName() + " - " + a.getRace());
//    }
//
//    static void toTheCoast (FrekenSnork x, Stranger y){
//        System.out.println("\n" + y.getName() + y.swim() + ", поэтому " + x.getName() + " поплыла до берега на ней");
//    }
//
//}

