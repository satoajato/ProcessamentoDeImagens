package br.com.matsunaga.processamentodeimagens.imagem;

public class RGB {
    private int alpha;
    private int red;
    private int green;
    private int blue;

    public RGB(int alpha, int red, int green, int blue) {
        this.alpha = alpha;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    
    public RGB(int red, int green, int blue) {
        this.alpha = 255;
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
