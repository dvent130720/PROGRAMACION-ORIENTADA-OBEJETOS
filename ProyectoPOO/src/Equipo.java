
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Equipo {
    
    private String nombre;
    private ArrayList<Personaje> personajes;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.personajes = new ArrayList<Personaje>();
    }

    public void agregarPersonaje(Personaje p) {
        this.personajes.add(p);
        p.setEquipo(this); // Hmmm ?
    }

    public ArrayList<Personaje> getPersonajes() {
        return this.personajes;
    }

    
    public String getNombre() {
        return this.nombre;
    }
   
    public boolean estaDerrotado() {
        for (Personaje p : this.personajes) {
            if (p.estaVivo()) {
                return false; 
            }
        }
        return true; 
    }
    
 
    public int getSumaVida() {
        int suma = 0;
        for (Personaje p : this.personajes) {
            if (p.estaVivo()) {
                suma += p.getVida();
            }
        }
        return suma;
    }


    public void atacarOtroEquipo(Equipo otroEquipo, Scanner sc) {
        System.out.println("===================================");
        System.out.println("Equipo atacante: " + this.nombre);
        
        int danoTotalRonda = 0;
        ArrayList<Personaje> enemigos = otroEquipo.getPersonajes();

        // Anadir una lista de enemigos muertos
        ArrayList<Personaje> muertos = new ArrayList<>();

        Random atackPlayer = new Random();

        for (int i = 0; i < 3; i++) {
            Personaje atacante = this.personajes.get(i);
            Personaje enemigo = enemigos.get(i);
            
            if (atacante.estaVivo() && enemigo.estaVivo()) {
                int danoHecho = atacante.realizarAtaque(enemigo);
                danoTotalRonda += danoHecho;
            } else { // Le añadí código desde aquí -- Para que deje de atacar enemigos muertos
                if (!atacante.estaVivo()) {
                    System.out.println(atacante.getNombre() + " esta muerto!!!");
                } else { // Si el enemigo esta muerto
                    if (!muertos.contains(enemigo)) { // Y si no esta en la lista array de muertos
                        muertos.add(enemigo); // Que lo anada a la lista array de muertos
                
                        int muerto = enemigos.indexOf(enemigo); // Indice del enemigo con descanso eterno
                        while (muertos.contains(enemigos.get(muerto))) {
                            muerto = atackPlayer.nextInt(enemigos.size());
                            try {
                                System.out.println("Probando while de los muertos");
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }// Hasta aquí
                        int danoHecho = atacante.realizarAtaque(enemigos.get(muerto));
                        danoTotalRonda += danoHecho;
                        
                    }
                }
            }
        }
        
 
        Personaje mistico = this.personajes.get(3);
        Personaje enemigoMistico = enemigos.get(3);
        
        if (mistico.estaVivo() && enemigoMistico.estaVivo()) {
            mistico.usarEstrategia(); 
            
            Random rand = new Random();
            int dado = rand.nextInt(6) + 1; 
            
            System.out.print("¡Adivina el número del dado (1-6) para potenciar al Místico!: ");
            int adivinanza = sc.nextInt();
            
            int ataqueFinalMistico = mistico.getAtaque(); 
            
            if (dado == adivinanza) {
                System.out.println("¡Acierto! El dado fue " + dado + ". ¡El ataque se potencia con " + danoTotalRonda + " de daño extra!");
                ataqueFinalMistico += danoTotalRonda;
            } else {
                System.out.println("¡Fallo! El dado fue " + dado + ". El Místico ataca con fuerza normal.");
            }
            
            // El místico ataca directamente
            System.out.println(mistico.getNombre() + " ataca a " + enemigoMistico.getNombre());
            enemigoMistico.recibirAtaque(ataqueFinalMistico);
        }
    }
}