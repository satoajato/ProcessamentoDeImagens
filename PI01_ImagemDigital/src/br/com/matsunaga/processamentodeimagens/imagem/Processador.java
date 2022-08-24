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
    
    public void setEscalaCinza(int qtd) {
        for(int y = 0; y<getImagem().altura(); y++) {
            for(int x = 0; x<getImagem().largura(); x++) {
                RGB rgb = getImagem().getCor(x, y);
                int gray = (rgb.getRed()+rgb.getGreen()+rgb.getBlue())/3;
                
                if(gray >= 256/qtd) {
                    int auxMin = (rgb.getRed()+rgb.getGreen()+rgb.getBlue())/3;
                    while(auxMin %(256/qtd) != 0) {
                        auxMin--;
                    }
                    gray = auxMin + (256/qtd) - 1;
                }
                else {
                    gray = 0;
                }

                // switch (qtd) {
                //     case 2:
                //         if(gray >= 128) gray = 255;
                //         else gray = 0;
                //         break;
                //     case 4:
                //         if(gray >= 192) gray = 255;
                //         else if (gray >= 128) gray = 191;
                //         else if (gray >= 64) gray = 127;
                //         else if (gray > 0) gray = 63;
                //         else gray = 0;
                //         break;
                //     case 8:
                //         if(gray >= 224) gray = 255;
                //         else if (gray >= 192) gray = 223;
                //         else if (gray >= 160) gray = 191;
                //         else if (gray >= 128) gray = 159;
                //         else if (gray >= 96) gray = 127;
                //         else if (gray >= 64) gray = 95;
                //         else if (gray >= 32) gray = 63;
                //         else if (gray > 0) gray = 31;
                //         else gray = 0;
                //         break;
                //     case 16:
                //         break;
                //     case 32:
                //         break;
                //     case 64:
                //         break;
                //     case 128:
                //         break;
                //     default:
                //         break;
                // }

                RGB GrayScale = new RGB(gray,gray,gray);
                this.imagem.setCor(GrayScale, x, y);

            }
        }
    }

    public void setEscalaColorida(String cor) {
        for(int y = 0; y<getImagem().altura(); y++) {
            for(int x = 0; x<getImagem().largura(); x++) {
                RGB rgb = getImagem().getCor(x, y);
                int gray = (rgb.getRed()+rgb.getGreen()+rgb.getBlue())/3;
                RGB CorScale;
                switch (cor) {
                    case "Red":
                        CorScale = new RGB(gray,0,0);
                        break;
                    case "Green":
                        CorScale = new RGB(0,gray,0);
                        break;
                    case "Blue":
                        CorScale = new RGB(0,0,gray);
                        break;
                    case "Cyan":
                        CorScale = new RGB(0,gray,gray);
                        break;
                    case "Magenta":
                        CorScale = new RGB(gray,0,gray);
                        break;
                    case "Yellow":
                        CorScale = new RGB(gray,gray,0);
                        break;
                    default:
                        CorScale = new RGB(gray,gray,gray);
                        break;
                }

                this.imagem.setCor(CorScale, x, y);
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
    
    public void setSaturar(double nivel) {
        for(int y = 0; y<getImagem().altura(); y++) {
            for(int x = 0; x<getImagem().largura(); x++) {
                
                RGB rgb = getImagem().getCor(x, y);
                int Red = rgb.getRed();
                int Green = rgb.getGreen();
                int Blue = rgb.getBlue();

                int Gray = (int) (0.2989*Red + 0.5870*Green + 0.1140*Blue);

                Red = (int) (-Gray * nivel + Red * (1+nivel));
                Green = (int) (-Gray * nivel + Green * (1+nivel));
                Blue = (int) (-Gray * nivel + Blue * (1+nivel));
                
                if(Red > 255) Red  = 255;
                if(Green > 255) Green = 255;
                if(Blue > 255) Blue = 255;
                if(Red  < 0) Red  = 0;
                if(Green < 0) Green = 0;
                if(Blue < 0) Blue = 0;

                RGB Saturar = new RGB(Red,Green,Blue);
                
                this.imagem.setCor(Saturar, x, y);
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

