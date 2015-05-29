import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
/**
 * Write a description of class Equipo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Equipo
{
    private ArrayList<Jugador>jugadores;
    private String nombre;

    /**
     * Constructor for objects of class Equipo
     */
    public Equipo(String nombre, int numJugadores)
    {
        this.nombre = nombre;
        Random rnd = new Random();
        int clave = rnd.nextInt(numJugadores - 1) + 1;
        jugadores = new ArrayList<>();
        for (int i = 1; i<=numJugadores; i++){
            if(i==1){
                jugadores.add(new Portero(i));
            }
            else if(i == clave){
                jugadores.add(new Lider(i));
            }
            else{
                jugadores.add(new JugadorCampo(i));
            }
        }
    }
    public double valoracion(){
        return 0.01;
    }
    public void showInfo(){
        double valoracionTotal = 0;
        System.out.println(nombre.toUpperCase());
        System.out.println("titulares:");
        for(Jugador jugador : jugadores){
            if(jugador instanceof Portero){
                valoracionTotal += jugador.valoracion();
                System.out.println(jugador.toString());
            }
        }
        for(Jugador jugador : jugadores){
            if(jugador instanceof Lider){
                valoracionTotal += jugador.valoracion();
                System.out.println(jugador.toString());
            }
        }
        HashSet<Jugador> jugadoresTotales = new HashSet<>();
        for(Jugador jugador : jugadores){
            if(!(jugador instanceof Lider) && !(jugador instanceof Portero)){
                jugadoresTotales.add(jugador);
            }
        }
        Iterator<Jugador> it = jugadoresTotales.iterator();
        int cont = 0;
        while(cont < 9){
            Jugador titular = it.next();
            System.out.println(titular.toString());
            valoracionTotal += titular.valoracion();
            cont++;
        }
        System.out.printf("***************************** MEDIA DE VALORACION DEL EQUIPO TITULAR: %1.2f ******************************************", valoracionTotal/11);
        System.out.println("\nReservas:");
        while(it.hasNext()){
            System.out.println(it.next().toString());
        }
        System.out.println("\n\n");
    }
}






















