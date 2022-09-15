package br.com.matsunaga.processamentodeimagens.imagem;

public class Grayscale extends RGBA {
    
    public Grayscale(int alpha, int valor) {
        super(alpha, valor, valor, valor);
    }
    
    public Grayscale(int valor) {
        super(255, valor, valor, valor);
    }
    
    public int getValue() {
        return this.red;
    }
    
    public void setValue(int valor) {
        this.red = this.green = this.blue = valor;
    }
}
