package br.com.matsunaga.processamentodeimagens.imagem;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Aplica algoritmos de processamento na imagem.
 * @author Fabio Matsunaga
 */
public class Processador {
    private Imagem imagem;

    public Processador(Imagem imagem) {
        this.imagem = imagem;
    }
    
    public Imagem getImagem() {
        return this.imagem;
    }
    
    
    public void preencherCor(RGBA cor) {
        for (int y=0; y < getImagem().altura(); y++) {
            for (int x=0; x < getImagem().largura(); x++) {
                this.imagem.setCor(cor, x, y);
            }
        }
    }
    
    public void setEscalaCinza() {
        for (int y=0; y < getImagem().altura(); y++) {
            for (int x=0; x < getImagem().largura(); x++) {
                RGBA pixel_cor = this.imagem.getCor(x,y);
                int r = pixel_cor.getRed();
                int g = pixel_cor.getGreen();
                int b = pixel_cor.getBlue();
                int tom_cinza = (r+g+b)/3;
                this.imagem.setCor(new Grayscale(tom_cinza), x, y);
            }
        }
    }
    
    
    public void setNegativo() {
        for (int y=0; y < getImagem().altura(); y++) {
            for (int x=0; x < getImagem().largura(); x++) {
                RGBA pixel_cor = this.imagem.getCor(x,y);
                int r = 255 - pixel_cor.getRed();
                int g = 255 - pixel_cor.getGreen();
                int b = 255 - pixel_cor.getBlue();
                this.imagem.setCor(new RGBA(r,g,b), x, y);
            }
        }
    }
    
    public void setPretoEBranco(int limiar) {
        for (int y=0; y < getImagem().altura(); y++) {
            for (int x=0; x < getImagem().largura(); x++) {
                RGBA pixel_cor = this.imagem.getCor(x,y);
                int r = pixel_cor.getRed();
                int g = pixel_cor.getGreen();
                int b = pixel_cor.getBlue();
                int media = (r+g+b)/3;
                RGBA nova_cor;
                if (media<=limiar) {
                    nova_cor = new Grayscale(0);
                }
                else {
                    nova_cor = new Grayscale(255);
                }
                this.imagem.setCor(nova_cor, x, y);
            }
        }
    }
    
    
    
    
    public void translacao(int dX, int dY) {
        try {
            Imagem img = new Imagem(imagem.altura(), imagem.largura(), imagem.getFile().getName());
            for(int y=0; y<imagem.altura(); y++) {
                for(int x=0; x<imagem.largura(); x++) {
                    RGBA pixel = imagem.getCor(x, y);
                    int newX = x+dX;
                    int newY = y+dY;
                    if(newX<img.largura() && newY<img.altura()) {
                       img.setCor(pixel, newX, newY);
                    }
                }
            }
            imagem = img;
        } catch (IOException ex) {
            Logger.getLogger(Processador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void escala(int eX, int eY) throws IOException {
        try {
            Imagem img = new Imagem(imagem.altura()*eY, imagem.largura()*eX, imagem.getFile().getName());
            for(int y=0; y<imagem.altura(); y++) {
                for(int x=0; x<imagem.largura(); x++) {
                    RGBA pixel = imagem.getCor(x, y);
                    int newX = x*eX;
                    int newY = y*eY;
                    if(newX<imagem.largura() && newY<imagem.altura()) {
                       img.setCor(pixel, newX, newY);
                    }
                }
            }
            imagem = img;
        } catch (IOException ex) {
            Logger.getLogger(Processador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rotaciona(double grau) {
        try {
            double rad = (grau*Math.PI)/180;
            
            int cX = imagem.largura()/2;
            int cY = imagem.altura()/2;
            
            Imagem img = new Imagem(imagem.altura(), imagem.largura(), imagem.getFile().getName());
            for(int y=0; y<imagem.altura(); y++) {
                for(int x=0; x<imagem.largura(); x++) {
                    RGBA pixel = imagem.getCor(x, y);
                    
                    int dX = x-cX;
                    int dY = y-cY;
                    
                    int newX = (int) (dX*Math.cos(rad) - dY*Math.sin(rad)) + cX;
                    int newY = (int) (dX*Math.sin(rad) + dY*Math.cos(rad)) + cY;
                    
                    if(newX >=0 && newY>=0 && newX<img.largura() && newY<img.altura()) {
                       img.setCor(pixel, newX, newY);
                    }
                }
            }
            imagem = img;
        } catch (IOException ex) {
            Logger.getLogger(Processador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void printMatrizRGB() {
        for (int y=0; y < getImagem().altura(); y++) {
            for (int x=0; x < getImagem().largura(); x++) {
                RGBA pixel_cor = this.imagem.getCor(x,y);
                System.out.print(pixel_cor);
            }
            System.out.println();
        }
    }
    
    public void printMatrizBits() {
        for (int y=0; y < getImagem().altura(); y++) {
            for (int x=0; x < getImagem().largura(); x++) {
                System.out.print(Integer.toBinaryString(this.imagem.getRGB(x,y)) + " ");
            }
            System.out.println();
        }
    }

}

