package br.com.matsunaga.processamentodeimagens.view;

import br.com.matsunaga.processamentodeimagens.imagem.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * Representa a interace gráfica para visualização das imagens
 * @author fabio
 */
public class InterfacePI extends JFrame {
    private static int NUM_PROCESSAMENTO_ATUAL = 0;
    private JPanel area_imagem, area_botoes;
    private JLabel visualizacao_imagem;
    private JFileChooser fileChooser;
    private JButton botao_carregar_imagem, 
                    botao_redefinir_imagem, 
                    botao_escala_cinza,
                    botao_inverter_imagem,
                    botao_preto_branco,
                    botao_zoom_in_quadrado,
                    botao_zoom_in_spline, 
                    botao_zoom_out_spline, 
                    botao_histograma,
                    botao_equalizar_histograma,
                    botao_filtro_laplaciano,
                    botao_filtro_sobel,
                    botao_filtro_roberts,
                    botao_filtro_prewitt,
                    botao_filtro_kirsch,
                    botao_filtro_pb,
                    botao_translacao,
                    botao_escala,
                    botao_rotacao;
    private ImageIcon icon;
    
    private Imagem imagem_digital, imagem_temporaria;
    private String diretorio_imagem_original, diretorio_imagem_tmp;
    private Processador pi;
    
    public InterfacePI(String titulo) {
        super(titulo);
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        area_imagem = new JPanel(new FlowLayout());
        area_imagem.setBackground(Color.black);
        area_imagem.setPreferredSize(new Dimension(300, 300));
        botao_carregar_imagem = new JButton("Carregar imagem");
        botao_redefinir_imagem = new JButton("Redefinir imagem");
        botao_escala_cinza = new JButton("Escala de cinza");
        botao_inverter_imagem = new JButton("Inverter imagem");
        botao_preto_branco = new JButton("Preto e branco");
        botao_translacao = new JButton("Translação");
        botao_escala = new JButton("Escala");
        botao_rotacao = new JButton("Rotação");
        botao_zoom_in_quadrado = new JButton("Zoom in (quadrado)");
        botao_zoom_in_spline = new JButton("Zoom in (spline)");
        botao_zoom_out_spline = new JButton("Zoom out (spline)");
        botao_histograma = new JButton("Histograma");
        botao_equalizar_histograma = new JButton("Equalizar histograma");
        botao_filtro_laplaciano = new JButton("Filtro Laplaciano");
        botao_filtro_sobel = new JButton("Filtro Sobel");
        botao_filtro_roberts = new JButton("Filtro Roberts");
        botao_filtro_prewitt = new JButton("Filtro Prewitt");
        botao_filtro_kirsch = new JButton("Filtro Kirsch");
        botao_filtro_pb = new JButton("Filtro passa-baixa");
        
        
        area_botoes = new JPanel(new GridLayout(15,2));
        
        icon = new ImageIcon("images/blank.png");
        visualizacao_imagem = new JLabel(icon);
        area_imagem.add(visualizacao_imagem);
        area_botoes.add(botao_carregar_imagem);
        area_botoes.add(botao_redefinir_imagem);
        area_botoes.add(botao_escala_cinza);
        area_botoes.add(botao_inverter_imagem);
        area_botoes.add(botao_preto_branco);
        area_botoes.add(botao_translacao);
        area_botoes.add(botao_escala);
        area_botoes.add(botao_rotacao);
        /*area_botoes.add(botao_zoom_in_quadrado);
        area_botoes.add(botao_zoom_in_spline);
        area_botoes.add(botao_zoom_out_spline);
        area_botoes.add(botao_histograma);
        area_botoes.add(botao_equalizar_histograma);
        area_botoes.add(botao_filtro_laplaciano);
        area_botoes.add(botao_filtro_sobel);
        area_botoes.add(botao_filtro_roberts);
        area_botoes.add(botao_filtro_prewitt);
        area_botoes.add(botao_filtro_kirsch);
        area_botoes.add(botao_filtro_pb);*/
        
        
        container.add(new JScrollPane(area_imagem), BorderLayout.CENTER);
        container.add(area_botoes, BorderLayout.EAST);
    }
    
    protected void atualizarVisualizacaoImagem(String diretorio_imagem) {
        icon = new ImageIcon(diretorio_imagem);
        visualizacao_imagem.setIcon(icon);
        System.out.println("Nova visualização: " + diretorio_imagem);
    }
    
    public void setEvents() {
        botao_carregar_imagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.showOpenDialog(null);
                    imagem_digital = new Imagem(fileChooser.getSelectedFile());
                    pi = new Processador(imagem_digital);
                    /*pi.getImagem().salvarImagem("tmp/"+imagem_digital.getFile().getName());
                    diretorio_imagem_tmp = "tmp/" + imagem_digital.getFile().getName();*/
                    diretorio_imagem_tmp = "tmp/imagem" + NUM_PROCESSAMENTO_ATUAL + "." + imagem_digital.tipoArquivo();
                    pi.getImagem().salvarImagem(diretorio_imagem_tmp);
                    diretorio_imagem_original = imagem_digital.getFile().getAbsolutePath(); 
                    atualizarVisualizacaoImagem(imagem_digital.getFile().getAbsolutePath());
                } catch (IOException ex) {
                    Logger.getLogger(InterfacePI.class.getName()).log(Level.SEVERE, null, ex);
                } 

            }
        });
        
        botao_escala_cinza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pi = new Processador(new Imagem(diretorio_imagem_tmp));
                    imagem_digital.setFile(new File(diretorio_imagem_tmp));
                    pi.setEscalaCinza();
                    NUM_PROCESSAMENTO_ATUAL++;
                    diretorio_imagem_tmp = "tmp/imagem" + NUM_PROCESSAMENTO_ATUAL + "." + imagem_digital.tipoArquivo();
                    pi.getImagem().salvarImagem(diretorio_imagem_tmp);
                    atualizarVisualizacaoImagem(diretorio_imagem_tmp);
                } catch (IOException ex) {
                    Logger.getLogger(InterfacePI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        

        botao_inverter_imagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pi = new Processador(new Imagem(diretorio_imagem_tmp));
                    imagem_digital.setFile(new File(diretorio_imagem_tmp));
                    pi.setNegativo();
                    NUM_PROCESSAMENTO_ATUAL++;
                    diretorio_imagem_tmp = "tmp/imagem" + NUM_PROCESSAMENTO_ATUAL + "." + imagem_digital.tipoArquivo();
                    pi.getImagem().salvarImagem(diretorio_imagem_tmp);
                    atualizarVisualizacaoImagem(diretorio_imagem_tmp);
                } catch (IOException ex) {
                    Logger.getLogger(InterfacePI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        botao_preto_branco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pi = new Processador(new Imagem(diretorio_imagem_tmp));
                    imagem_digital.setFile(new File(diretorio_imagem_tmp));
                    int limiar = Integer.parseInt(JOptionPane.showInputDialog("Limiar (0 a 255)"));
                    pi.setPretoEBranco(limiar);
                    NUM_PROCESSAMENTO_ATUAL++;
                    diretorio_imagem_tmp = "tmp/imagem" + NUM_PROCESSAMENTO_ATUAL + "." + imagem_digital.tipoArquivo();
                    pi.getImagem().salvarImagem(diretorio_imagem_tmp);
                    atualizarVisualizacaoImagem(diretorio_imagem_tmp);
                } catch (IOException ex) {
                    Logger.getLogger(InterfacePI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        botao_translacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pi = new Processador(new Imagem(diretorio_imagem_tmp));
                    imagem_digital.setFile(new File(diretorio_imagem_tmp));
                    int dX = Integer.parseInt(JOptionPane.showInputDialog("Deslocamento X"));
                    int dY = Integer.parseInt(JOptionPane.showInputDialog("Deslocamento Y"));
                    pi.translacao(dX, dY);
                    NUM_PROCESSAMENTO_ATUAL++;
                    diretorio_imagem_tmp = "tmp/imagem" + NUM_PROCESSAMENTO_ATUAL + "." + imagem_digital.tipoArquivo();
                    pi.getImagem().salvarImagem(diretorio_imagem_tmp);
                    atualizarVisualizacaoImagem(diretorio_imagem_tmp);
                } catch (IOException ex) {
                    Logger.getLogger(InterfacePI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        botao_escala.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pi = new Processador(new Imagem(diretorio_imagem_tmp));
                    imagem_digital.setFile(new File(diretorio_imagem_tmp));
                    int eX = Integer.parseInt(JOptionPane.showInputDialog("Escala X"));
                    int eY = Integer.parseInt(JOptionPane.showInputDialog("Escala Y"));
                    pi.escala(eX, eY);
                    NUM_PROCESSAMENTO_ATUAL++;
                    diretorio_imagem_tmp = "tmp/imagem" + NUM_PROCESSAMENTO_ATUAL + "." + imagem_digital.tipoArquivo();
                    pi.getImagem().salvarImagem(diretorio_imagem_tmp);
                    atualizarVisualizacaoImagem(diretorio_imagem_tmp);
                } catch (IOException ex) {
                    Logger.getLogger(InterfacePI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        botao_rotacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pi = new Processador(new Imagem(diretorio_imagem_tmp));
                    imagem_digital.setFile(new File(diretorio_imagem_tmp));
                    double grau = Double.parseDouble(JOptionPane.showInputDialog("Grau"));
                    pi.rotaciona(grau);
                    NUM_PROCESSAMENTO_ATUAL++;
                    diretorio_imagem_tmp = "tmp/imagem" + NUM_PROCESSAMENTO_ATUAL + "." + imagem_digital.tipoArquivo();
                    pi.getImagem().salvarImagem(diretorio_imagem_tmp);
                    atualizarVisualizacaoImagem(diretorio_imagem_tmp);
                } catch (IOException ex) {
                    Logger.getLogger(InterfacePI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        

        
        
    }
    
    public static void main(String[] args) {
        InterfacePI janela = new InterfacePI("Processamento de Imagens Digitais");
        janela.setSize(1000, 700);
        janela.setVisible(true);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
        janela.setEvents();
    }
    
}
