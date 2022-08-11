package br.com.matsunaga.processamentodeimagens.imagem;

/**
 *
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
    
    public void preencherCor(RGB cor) {
        for (int y=0; y < getImagem().altura(); y++) {
            for (int x=0; x < getImagem().largura(); x++) {
                this.imagem.setCor(cor, x, y);
            }
        }
    }
    
    public void setEscalaCinza() {
        for(int y = 0; y<getImagem().altura(); y++) {
            for(int x = 0; x<getImagem().largura(); x++) {
                RGB rgb = getImagem().getCor(x, y);
                int gray = (rgb.getRed()+rgb.getGreen()+rgb.getBlue())/3;
                RGB GrayScale = new RGB(gray,gray,gray);
                RGB RedScale = new RGB(gray,0,0);
                RGB GreenScale = new RGB(0,gray,0);
                RGB BlueScale = new RGB(0,0,gray);
                
                this.imagem.setCor(GrayScale, x, y);
//                this.imagem.setCor(RedScale, x, y);
//                this.imagem.setCor(GreenScale, x, y);
//                this.imagem.setCor(BlueScale, x, y);
            }
        }
    }
    
    public void setNegativo() {
        for(int y = 0; y<getImagem().altura(); y++) {
            for(int x = 0; x<getImagem().largura(); x++) {
                RGB rgb = getImagem().getCor(x, y);
                int RedN = 255-rgb.getRed();
                int GreenN = 255-rgb.getGreen();
                int BlueN = 255-rgb.getBlue();
                RGB NegativeScale = new RGB(RedN,GreenN,BlueN);
                
                this.imagem.setCor(NegativeScale, x, y);
            }
        }
    }
    
    public void setSaturar() {
        for(int y = 0; y<getImagem().altura(); y++) {
            for(int x = 0; x<getImagem().largura(); x++) {
                RGB rgb = getImagem().getCor(x, y);
                int Red = rgb.getRed();
                int Green = rgb.getGreen();
                int Blue = rgb.getBlue();
                
                RGB Saturar = new RGB(Red,Green,Blue);
                
                if(Red>Green && Red>Blue) {
                    Saturar = new RGB(255,Green,Blue);
                }
                else if(Red>Green && Red>Blue) {
                    Saturar = new RGB(Red,255,Blue);
                }
                else {
                    Saturar = new RGB(Red,Green,255);
                }
                
                this.imagem.setCor(Saturar, x, y);
            }
        }
    }
    
    public void setBeW() {
        for(int y = 0; y<getImagem().altura(); y++) {
            for(int x = 0; x<getImagem().largura(); x++) {
                RGB rgb = getImagem().getCor(x, y);
                int bw = (rgb.getRed()+rgb.getGreen()+rgb.getBlue())/3;
                if(bw >= 128) {
                    bw = 255;
                }
                else {
                    bw = 0;
                }
                RGB BWScale = new RGB(bw,bw,bw);
                this.imagem.setCor(BWScale, x, y);
            }
        }
    }
    
    
    public void printMatrizRGB() {
        for (int y=0; y < getImagem().altura(); y++) {
            for (int x=0; x < getImagem().largura(); x++) {
                RGB pixel_cor = this.imagem.getCor(x,y);
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

