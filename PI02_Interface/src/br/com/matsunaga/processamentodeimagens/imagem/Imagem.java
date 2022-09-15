package br.com.matsunaga.processamentodeimagens.imagem;

import java.awt.Color;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Representa uma imagem digital.
 * @author fabio
 */
public class Imagem implements Cloneable {
    
    private BufferedImage imagem;
    private File arquivo_imagem;
    
    /**
     * Cria uma instância carregando um arquivo de acordo
     * com seu diretório.
     * @param path String de diretório do arquivo
     * @throws IOException 
     */
    public Imagem(String path) throws IOException {
        this.arquivo_imagem = new File(path);
        this.imagem = ImageIO.read(this.arquivo_imagem);
    }
    
    /**
     * Cria uma instância carregando um arquivo de acordo
     * com o objeto File.
     * @param arquivo Objeto File.
     * @throws IOException 
     */
    public Imagem(File arquivo) throws IOException {
        this.arquivo_imagem = arquivo;
        this.imagem = ImageIO.read(this.arquivo_imagem);
    }
    
    /**
     * Criar um novo arquivo de imagem em "branco"
     * @param altura Altura da imagem (y)
     * @param largura Largura da imagem (x)
     * @param nome Nome do novo arquivo de imagem
     * @throws IOException 
     */
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
    
    /**
     * Retorna a extensão do arquivo (jpg, png, gif, bmp)
     * @return String de extensão do arquivo
     */
    public String tipoArquivo() {
        String nome_arquivo = this.arquivo_imagem.getName();
        return nome_arquivo.substring(nome_arquivo.lastIndexOf('.') + 1);
    }
    
    /**
     * Retorna os bytes referentes ao pixel.
     * @param x Posição x
     * @param y Posição y
     * @return Valor dos bytes convertido para inteiro.
     */
    public int getRGB(int x, int y) {
        return this.imagem.getRGB(x, y); 
    }
    
    /**
     * Converte um pixel da imagem em um objeto RGBA
     * @param x
     * @param y
     * @return 
     */
    public RGBA getCor(int x, int y) {
        // Obtém os bits RBG do pixel
        int pixel = this.imagem.getRGB(x, y); 
        // Realiza operações de AND bit-a-bit e deslocamento para extrair os valores de cada cor (RGBA)
        int alpha   = (int)((pixel & 0xFF000000) >>> 24);
        int red     = (int)((pixel & 0x00FF0000) >>> 16);   //R
        int green   = (int)((pixel & 0x0000FF00) >>> 8);  //G
        int blue    = (int) (pixel & 0x000000FF);          //B
        // Retorna o objeto RGBA
        return new RGBA(alpha,red,green,blue);
    }
    
    /**
     * Obtém o nível de cinza do pixel (x,y)
     * @param x
     * @param y
     * @return 
     */
    public Grayscale getNivelCinza(int x, int y) {
        int pixel = this.imagem.getRGB(x, y);
        int alpha   = (int)((pixel & 0xFF000000) >>> 24);
        int red     = (int)((pixel & 0x00FF0000) >>> 16);   //R
        int green   = (int)((pixel & 0x0000FF00) >>> 8);  //G
        int blue    = (int) (pixel & 0x000000FF);
        int media = (red+green+blue)/3;
        return new Grayscale(alpha,media);
    }
    
    /**
     * Alterar a cor da imagem
     * @param pixel
     * @param x
     * @param y 
     */
    public void setCor(RGBA pixel, int x, int y) {
        // Cria uma instância de Color
        Color cor = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue(), pixel.getAlpha());
        this.imagem.setRGB(x, y, cor.getRGB());
    }
    
    /**
     * Salva uma cópia do arquivo.
     * @param path
     * @throws IOException 
     */
    public void salvarImagem(String path) throws IOException {
        ImageIO.write(this.imagem, this.tipoArquivo(), new File(path));
        this.imagem.flush();
    }
    
    /**
     * Salva as alterações da imagem.
     * @throws IOException 
     */
    public void salvarImagem() throws IOException {
        ImageIO.write(this.imagem, this.tipoArquivo(), this.arquivo_imagem);
        this.imagem.flush();
    }
    
    public Imagem getClone() {
        try {
            return (Imagem) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println (" Cloning not allowed. " );
            return this;
        }
    }
}
