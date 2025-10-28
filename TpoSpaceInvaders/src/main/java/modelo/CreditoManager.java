package modelo;

public class CreditoManager {
    private int creditos;
    
    public CreditoManager() {
        this.creditos = 0;
    }
    
    public void cargarCreditos(int cantidad) {
        if (cantidad > 0) {
            this.creditos += cantidad;
        }
    }
    
    public boolean consumirCredito() {
        if (tieneCreditos()) {
            creditos--;
            return true;
        }
        return false;
    }
    
    public boolean tieneCreditos() {
        return creditos > 0;
    }
    
    public int getCreditos() {
        return creditos;
    }
}

