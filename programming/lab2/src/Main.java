import mypokemons.*;
import ru.ifmo.se.pokemon.Battle;

public class Main {
        //https://pokemondb.net/pokedex/mewtwo - мяуту
        //https://pokemondb.net/pokedex/snover - сновер
        //https://pokemondb.net/pokedex/abomasnow - абомасноy
        //https://pokemondb.net/pokedex/bellsprout - бэлспрут
        //https://pokemondb.net/pokedex/weepinbell - випиэнбэлл
        //https://pokemondb.net/pokedex/victreebel - виктрибел

        public static void main(String[] args){
            Battle b = new Battle();

            Meowtwo meowtwo = new Meowtwo("", 10);
            Snover snover = new Snover("",1);
            Abomasnow abomasnow = new Abomasnow("",9);
            Bellsprout bellsprout = new Bellsprout("",1);
            Weepinbell weepinbell = new Weepinbell("",41);
            Victreebell victreebell = new Victreebell("",1);


            b.addAlly(snover);
            b.addAlly(bellsprout);
            b.addAlly(victreebell);


            b.addFoe(meowtwo);
            b.addFoe(abomasnow);
            b.addFoe(weepinbell);

            b.go();

        }
}
