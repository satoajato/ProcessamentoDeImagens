package br.com.matsunaga.processamentodeimagens.main;

import br.com.matsunaga.processamentodeimagens.imagem.Imagem;
import br.com.matsunaga.processamentodeimagens.imagem.Processador;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Fabio Matsunaga
 */
public class Main {
    
    public static void main(String[] args) {
        try {
            
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(null);
            Imagem imagem_digital = new Imagem(fileChooser.getSelectedFile());
            System.out.println("Altura da imagem: " + imagem_digital.altura());
            System.out.println("Largura da imagem: " + imagem_digital.largura());
            System.out.println("Caminho do arquivo: " + imagem_digital.getFile().getAbsolutePath());
            System.out.println("Nome do arquivo: " + imagem_digital.getFile().getName());
            System.out.println("Tipo do arquivo: " + imagem_digital.tipoArquivo());
            Processador pi = new Processador(imagem_digital);
            // pi.printMatrizRGB();
            // pi.printMatrizBits();

            // int qtdCinza = 256;
            // pi.setEscalaCinza(qtdCinza);
            // pi.getImagem().salvarImagem(qtdCinza + "_GrayScale_" + imagem_digital.getFile().getName());
            
            int qtdZoom = 2;
            
            // String zoomAD = "Diminuir"; //Diminuir ou Aumentar
            // pi.zoomDigital(qtdZoom, zoomAD);
            // pi.getImagem().salvarImagem("X"+ qtdZoom + "_" + zoomAD + "_DigitalZoom_" + imagem_digital.getFile().getName());
            
            pi.zoomLinear(qtdZoom);
            pi.getImagem().salvarImagem("X" + qtdZoom + "_Aumentar_LinearZoom_" + imagem_digital.getFile().getName());
            
            // String cor = "Yellow"; //Red, Green, Blue, Cyan, Magenta, Yellow.
            // pi.setEscalaColorida(cor);
            // pi.getImagem().salvarImagem("C" + cor + "Scale_" + imagem_digital.getFile().getName());

            // pi.setSaturar(2.5);
            // pi.getImagem().salvarImagem("Satured" + 25 + "%_" + imagem_digital.getFile().getName());

            // pi.setSaturar(5.0);
            // pi.getImagem().salvarImagem("Satured" + 50 + "%_" + imagem_digital.getFile().getName());

            // pi.setSaturar(7.5);
            // pi.getImagem().salvarImagem("Satured" + 75 + "%_" + imagem_digital.getFile().getName());

            // pi.setSaturar(10);
            // pi.getImagem().salvarImagem("Satured" + 100 + "%_" + imagem_digital.getFile().getName());
            
            // pi.setNegativo();
            // pi.getImagem().salvarImagem("Negative_" + imagem_digital.getFile().getName());

            
            // pi.printMatrizRGB();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
