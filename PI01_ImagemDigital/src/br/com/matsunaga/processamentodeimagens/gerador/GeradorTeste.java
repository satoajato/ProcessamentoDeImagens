package br.com.matsunaga.processamentodeimagens.gerador;

import br.com.matsunaga.processamentodeimagens.imagem.Imagem;
import br.com.matsunaga.processamentodeimagens.imagem.Processador;
import br.com.matsunaga.processamentodeimagens.imagem.RGB;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeradorTeste {
    
    public static void main(String args[]) {
        try {
        
            int altura, largura;
            Scanner scan = new Scanner(System.in);
            System.out.print("Altura: ");
            altura = scan.nextInt();
            System.out.print("Largura: ");
            largura = scan.nextInt();
            
            Imagem imagem = new Imagem(altura, largura, "bitmap.bmp");
            Processador pi = new Processador(imagem);
            pi.preencherCor(new RGB(100, 250, 100));
            //pi.printMatrizBits();
            pi.getImagem().salvarImagem();
            
        } catch (IOException ex) {
            Logger.getLogger(GeradorTeste.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
