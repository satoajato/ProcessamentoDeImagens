package br.com.matsunaga.processamentodeimagens.imagem;

/**
 * Classe para representar um pixel e suas cores no formato RGB
 * @author fabio
 */
public class RGB {
    protected int red;
    protected int green;
    protected int blue;
    
    public RGB(int valor) {
        this.red = this.green = this.blue = valor;
    }

    public RGB(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    
    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }
    
    public String toString() {
        return "(" + this.getRed() + "," + this.getGreen() + "," + this.getBlue() + ")";
    }
}
