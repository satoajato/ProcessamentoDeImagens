package gerador;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import imagem.RGB;
import imagem.Imagem;
import imagem.Processador;

public class GeradorTeste {
    
    public static void main(String args[]) {
        try {
        
            int altura, largura;
            try (Scanner scan = new Scanner(System.in)) {
                System.out.print("Altura: ");
                altura = scan.nextInt();
                System.out.print("Largura: ");
                largura = scan.nextInt();
            }
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
