package br.com.matsunaga.processamentodeimagens.imagem;

/**
 * Classe para representar um pixel e suas cores no formato RGBA
 * RGBA - Red, Green, Blue, Alpha
 * @author fabio
 */
public class RGBA extends RGB {
    
    private int alpha;

    public RGBA(int alpha, int red, int green, int blue) {
        super(red, green, blue);
        this.alpha = alpha;
    }
    
    public RGBA(int red, int green, int blue) {
        super(red, green, blue);
        this.alpha = 255;
    }
    
    public RGBA(int valor) {
        super(valor, valor, valor);
        this.alpha = valor;
    }
    
    public int getAlpha() {
        return this.alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }
    
    public String toString() {
        return "(" + this.getAlpha() + "," + this.getRed() + "," + this.getGreen() + "," + this.getBlue() + ")";
    }
    
}
