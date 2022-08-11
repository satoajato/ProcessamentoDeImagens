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
//            pi.printMatrizRGB();
//            pi.printMatrizBits();
//            pi.setEscalaCinza();
//            pi.setNegativo();
//            pi.setBeW();
            pi.setSaturar();
            pi.getImagem().salvarImagem("nova_imagem." + imagem_digital.tipoArquivo());
            pi.printMatrizRGB();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
