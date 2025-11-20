import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
     
        Equipo equipo1 = new Equipo("Equipo Rojo");
        equipo1.agregarPersonaje(new Guerrero("Guerrero Rojo 1", 100, 20, 10));
        equipo1.agregarPersonaje(new Guerrero("Guerrero Rojo 2", 100, 20, 10));
        equipo1.agregarPersonaje(new Mago("Mago Rojo", 80, 15, 5));
        equipo1.agregarPersonaje(new Mistico("Mistico Rojo", 90, 18, 8));

      
        Equipo equipo2 = new Equipo("Equipo Azul");
        equipo2.agregarPersonaje(new Guerrero("Guerrero Azul 1", 100, 20, 10));
        equipo2.agregarPersonaje(new Guerrero("Guerrero Azul 2", 100, 20, 10));
        equipo2.agregarPersonaje(new Mago("Mago Azul", 80, 15, 5));
        equipo2.agregarPersonaje(new Mistico("Mistico Azul", 90, 18, 8));
        
        
        int ronda = 0;
        boolean juegoTerminado = false;
        
        while (ronda < 15 && !juegoTerminado) {
            ronda++;
            System.out.println(String.format("""
                                ---->>        Ronda: %d         <<----
                    """, ronda));

           
            if (!equipo1.estaDerrotado() && !equipo2.estaDerrotado()) {
                equipo1.atacarOtroEquipo(equipo2, sc);
            }
            
           
            if (equipo2.estaDerrotado()) {
                juegoTerminado = true;
            } else if (!equipo1.estaDerrotado()) { 
                equipo2.atacarOtroEquipo(equipo1, sc);
                
                
                if (equipo1.estaDerrotado()) {
                    juegoTerminado = true;
                }
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
           
        System.out.println("FIN DEL COMBATE");
        
        if (equipo1.estaDerrotado()) {
            System.out.println("¡El " + equipo2.getNombre() + " GANA!");
        } else if (equipo2.estaDerrotado()) {
            System.out.println("¡El " + equipo1.getNombre() + " GANA!");
        } else {
           
            System.out.println("Se alcanzo el limite de 15 rondas. Se decide por vida total:");
            int vida1 = equipo1.getSumaVida();
            int vida2 = equipo2.getSumaVida();
            
            System.out.println(equipo1.getNombre() + ": " + vida1 + " HP");
            System.out.println(equipo2.getNombre() + ": " + vida2 + " HP");
            
            if (vida1 > vida2) {
                System.out.println("¡El " + equipo1.getNombre() + " GANA!");
            } else if (vida2 > vida1) {
                System.out.println("¡El " + equipo2.getNombre() + " GANA!");
            } else {
                System.out.println("¡Es un EMPATE!");
            }
        }
        
        sc.close();
    }
}