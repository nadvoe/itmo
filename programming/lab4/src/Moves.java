import Exceptions.*;
import Stuff.*;
import Stuff.Character;

import java.util.Objects;

class Moves {

    //проверяем, сожжена челка или нет
    static boolean areBangsBurnt (FrekenSnork.Bangs a){
        if (Math.random()+ 0.4 >= 0.5){
            return true;
        } else {
            return false;
        }
    }

    //метод для создания локального класса flowers
    static void MakeWreath(FrekenSnork a){

        //local inner class
        class Flowers{
            public String name;
            public Flowers(){
                this.name = name;
            }
            public String getName(){return this.name;}
            public void setName(String name){this.name = name;}
        }

        Flowers flowers = new Flowers();
        flowers.setName(" морские лилии");

        System.out.println(a.getName() + " использовала" + flowers.getName()+ ", чтобы спрятать обгоревшие волосы.");
    }

    static boolean isStormy(Sea a) {
        if(Sea.Storm.Stormy() > 5){
            return true;
        } else{
            return false;
        }
    }
    //будем проверять, кто кому делает подарок
    static void MakePresent(Character a, Character b){
        if (a.equals(b)) {
        throw new SameCharacterException("SameCharacterException: Персонаж не может делать подарки сам себе.");
    } System.out.println(a.getName() + " хочет сделать подарок для " + b.getName());
    }

    public static String GiftTypeIsJewelry(Gift b) {
        if (Math.random() * 5 + 1 > 5) {
            b.setName("украшение");
            return b.getName();
        } else {
            b.setName("что-то oсобенное");
            return b.getName();
        }
    }

    static void CheckGiftType(FrekenSnork a, Gift b) throws JewelleryException {
        if(Objects.equals(b.getName(), "украшение")){
            throw new JewelleryException("JewelleryException: Фрекен Снорк не будет дарить украшение.");
        } System.out.println(a.getName() + " подарит " + b.getName());
    }



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
    static float getStrength(Character a){
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

