
public abstract class Personaje {

    
    private String nombre;
    protected int vida;
    protected int ataque;
    protected int defensa;
    protected int ataqueBase; 
    protected Equipo miEquipo; 

    public Personaje(String n, int v, int a, int d) {
        this.nombre = n;
        this.vida = v;
        this.ataque = a;
        this.defensa = d;
        this.ataqueBase = a; 
    }

    public int recibirAtaque(int dano) {
        int danoReal = dano - this.defensa;
        
        if (danoReal < 0) {
            danoReal = 0;
        }
        
        this.vida -= danoReal;
        
        if (this.vida < 0) {
            this.vida = 0; 
        }
        
        System.out.println(this.nombre + " recibe " + danoReal + " de daño. Vida restante: " + this.vida);
        return danoReal;
    }

    
    public int realizarAtaque(Personaje contrario) {
        usarEstrategia(); 
        System.out.println(this.nombre + " ataca a " + contrario.getNombre());
        
        int danoHecho = contrario.recibirAtaque(this.ataque);
        
        this.ataque = this.ataqueBase; 
        return danoHecho;
    }

    
    public abstract void usarEstrategia();

    
    public void setEquipo(Equipo e) {
        this.miEquipo = e;
    }
    
    public String getNombre() {
        return this.nombre;
    }

    public int getAtaque() {
        return this.ataqueBase; 
    }
    
    public int getVida() {
        return this.vida;
    }
    
    public boolean estaVivo() {
        return this.vida > 0;
    }
    
    public void curar(int cantidad) {
        this.vida += cantidad;
        System.out.println(this.nombre + " es curado por " + cantidad + ". Vida actual: " + this.vida);
    }
}