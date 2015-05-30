import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Write a description of class Jornada here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Jornada
{
    // Una jornada se compone de una serie de partidos que enfrentan a los equipos entre si
    private ArrayList<Partido> partidos;

    /**
     * Crea objetos del tipo jornada con los equipos introducidos como parametro
     */
    public Jornada(ArrayList<Equipo> equipos)
    {
        // initialise instance variables
        partidos = new ArrayList();
        // Empareja los equipos dos a dos sin repetir de forma aleatoria, 
        // mezclando primero los equipos
        Collections.shuffle(equipos);
        int indice = 0;
        // Toma los equipos dos a dos para el emparejamiento
        while(indice < equipos.size())
        {
            partidos.add(new Partido(equipos.get(indice), equipos.get(indice+1)));
            indice += 2;
        }
    }

    /**
     * Simula los partidos de la jornada y devuelve los resultados.
     * @return Los resultados de los partidos
     */
    public HashMap<Equipo,Integer> sumularJornada()
    {
        HashMap<Equipo,Integer> resultados = new HashMap();
        for(int i = 0; i < partidos.size(); i++)
        {
            // Simula el partido, lo que devuelve un resultado
            Partido partido = partidos.get(i);
            int rdo = partido.simular();
            switch(rdo)
            {
                case 0:
                resultados.put(partido.getLocal(), 1);
                resultados.put(partido.getVisitante(), 1);
                break;
                case 1:
                resultados.put(partido.getLocal(), 3);
                break;
                case 2:
                resultados.put(partido.getVisitante(), 3);
            }

        }
        return resultados;
    }

    /**
     * Devuelve los partidos que componen la jornada
     */
    public ArrayList<Partido> getPartidos()
    {
        return partidos;
    }

    /**
     * Compara dos jornadas para saber si son compatibles en la liga
     * (No debe haber ningun enfrentamiento igual)
     * @return true si las jornadas son compatibles en la misma liga, false sino.
     */
    public boolean compatibles(Jornada jornada)
    {
        boolean compatibles = true;
        int indice = 0;
        int index = 0;
        // Toma los partidos de la otra jornada para comparar
        ArrayList<Partido> partidosComparar = new ArrayList();
        partidosComparar = jornada.getPartidos();
        // Recorre todos los partidos, si encuentra alguno igual ya no son compatibles
        while(indice < partidos.size() && compatibles)
        {
            Partido partido = partidos.get(indice);
            // Compara el partido de esta jornada con todos los partidos de la jornada
            // introducida como parametro. Si alguno es igual, ya no son compatibles
            while(index < partidosComparar.size() && compatibles)
            {
                Partido partidoComparar = partidosComparar.get(index);
                // Para que sean iguales debe darse que el equipo local de A sea igual al local o visitante
                // de B, Y que el equipo visitante de A sea igual al local o visitante de B
                if((partido.getLocal() == partidoComparar.getLocal() || partido.getLocal() == 
                    partidoComparar.getVisitante()) && (partido.getVisitante() == partidoComparar.getLocal()
                    || partido.getVisitante() == partidoComparar.getVisitante()))
                {
                    compatibles = false;
                }
                index++;
            }
            indice++;
        }
        return compatibles;
    }
}