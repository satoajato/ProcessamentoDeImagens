package br.com.matsunaga.processamentodeimagens.imagem;

import java.awt.Color;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Imagem {
    
    private BufferedImage imagem;
    private File arquivo_imagem;
    
    public Imagem(String path) throws IOException {
        this.arquivo_imagem = new File(path);
        this.imagem = ImageIO.read(this.arquivo_imagem);
    }
    
    public Imagem(File arquivo) throws IOException {
        this.arquivo_imagem = arquivo;
        this.imagem = ImageIO.read(this.arquivo_imagem);
    }
    
    public Imagem(int altura, int largura, String nome) throws IOException {
        this.imagem = new BufferedImage(largura, altura, TYPE_INT_RGB);
        this.arquivo_imagem = new File(nome);
        salvarImagem(nome);
    }
    
    public int largura() {
        return this.imagem.getWidth();
    }
    
    public int altura() {
        return this.imagem.getHeight();
    }

    public BufferedImage getImagem() {
        return imagem;
    }

    public void setImagem(BufferedImage imagem) {
        this.imagem = imagem;
    }

    public File getFile() {
        return this.arquivo_imagem;
    }

    public void setFile(File arquivo_imagem) {
        this.arquivo_imagem = arquivo_imagem;
    }
    
    public String tipoArquivo() {
        String nome_arquivo = this.arquivo_imagem.getName();
        return nome_arquivo.substring(nome_arquivo.lastIndexOf('.') + 1);
    }
    
    public int getRGB(int x, int y) {
        return this.imagem.getRGB(x, y); 
    }
    
    public RGB getCor(int x, int y) {
        // Obtém os bits RBG do pixel
        int pixel = this.imagem.getRGB(x, y); 
        // Realiza operações de AND bit-a-bit e deslocamento para extrair os valores de cada cor (RGB)
        int alpha   = (int)((pixel & 0xFF000000) >>> 24);
        int red     = (int)((pixel & 0x00FF0000) >>> 16);   //R
        int green   = (int)((pixel & 0x0000FF00) >>> 8);  //G
        int blue    = (int) (pixel & 0x000000FF);          //B
        // Retorna o objeto RGB
        return new RGB(alpha,red,green,blue);
    }
    
    public void setCor(RGB pixel, int x, int y) {
        // Cria uma instância de Color
        Color cor = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue(), pixel.getAlpha());
        this.imagem.setRGB(x, y, cor.getRGB());
    }
    
    public void salvarImagem(String path) throws IOException {
        ImageIO.write(this.imagem, this.tipoArquivo(), new File(path));
    }
    
    public void salvarImagem() throws IOException {
        ImageIO.write(this.imagem, this.tipoArquivo(), this.arquivo_imagem);
    }
}
