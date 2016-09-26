/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GameNave;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import java.util.List;
import java.util.Random;

/**
 *
 * @author vinicius
 */
public class Fase extends JPanel implements ActionListener
{
    
    private Image fundo;
    private Nave nave;
    private Timer timer;
    
    private boolean inGame;
    private List<Inimigo>inimigos;
    private List<Item>item;
    private int[][] coordenadas = new int[40][2];
    
    public Fase()
    {
        setFocusable(true); //seta a tela como foco principal (necessario para receber as teclas)
        setDoubleBuffered(true);
        
        addKeyListener(new TecladoAdapter()); //recebe as teclas pressionadas 
        ImageIcon referencia = new ImageIcon("res/fundo.png"); //seta a imagem do fundo
        
        fundo = referencia.getImage(); // a variavel fundo recebe a imagem
        nave = new Nave(); // uma nova 'Nave' é instanciada
        inGame = true;
        
        inicializaInimigos();
        
        timer = new Timer(5,this); //atualizações na tela em ms
        timer.start();//inicia a thread
        
    }
    
    public void novoItem()
    {
    	Random random = new Random();
    	item = new ArrayList<Item>();
    	
    }
    
    public void inicializaInimigos()
    {
    	Random random = new Random();
        inimigos = new ArrayList<Inimigo>();
        
        for(int i=0;i<40;i++){
        	coordenadas[i][0] = random.nextInt(2348)+500;
        	coordenadas[i][1] = random.nextInt(310)+15;
        }
        
        for(int i=0;i<coordenadas.length;i++){
            inimigos.add(new Inimigo(coordenadas[i][0],coordenadas[i][1]));
        }
    }
    
    public void paint(Graphics g)
    {        
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null); //Desenha o fundo
       
        if(inGame){
        
            graficos.drawImage(nave.getImagem(),nave.getX(),nave.getY(),this); //desenha a nave
        
            List<Missel>misseis = nave.getMisseis();
        
            for(int i=0;i<misseis.size();i++){
                Missel m = (Missel)misseis.get(i);
                graficos.drawImage(m.getImagem(), m.getX(), m.getY(),this);
            }
        
            for(int i=0;i<inimigos.size();i++){
                Inimigo m = inimigos.get(i);
                graficos.drawImage(m.getImagem(), m.getX(), m.getY(),this);
            }
            
            
            graficos.setColor(Color.WHITE);
            graficos.drawString("Inimigos: "+inimigos.size(),5,15);
        }
        else{
            ImageIcon fimJogo = new ImageIcon("res/game_over.jpg");
            graficos.drawImage(fimJogo.getImage(),0,0,null);
        }
        
        g.dispose(); //limpa a tela para receber a proxima tela
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        if(inimigos.isEmpty())
            inGame = false;
        
        List<Missel>misseis = nave.getMisseis();
        
        for(int i=0;i<misseis.size();i++){
            Missel m = (Missel)misseis.get(i);
            
            if(m.isVisivel())
                m.atiraMissel();
            else
                misseis.remove(i);
        }
        
        for(int i=0;i<inimigos.size();i++){
            Inimigo inimigo = (Inimigo)inimigos.get(i);
            
            if(inimigo.isVisivel())
                inimigo.mexer();
            else
                inimigos.remove(i);
        }
        
        nave.moverNave();
        checarColisao();
        repaint(); // toda vez que o método é chamado uma nova 'Nave' é criada em uma nova posicao
        
    }
    
    public void checarColisao()
    {
        Rectangle rectNave = nave.getBounds();
        Rectangle rectInimigo;
        Rectangle rectMissel;
        
        for(int i=0;i<inimigos.size();i++){
            Inimigo tempInimigo = inimigos.get(i);
            rectInimigo = tempInimigo.getBounds();
            
            if(rectNave.intersects(rectInimigo)){
                nave.setVisibilidade(false);
                tempInimigo.setVisivel(false);
                inGame = false;
            }
        }
        
        List<Missel>misseis = nave.getMisseis();
        
        for(int i=0;i<misseis.size();i++){//testa o Missel para todos os inimigos (verifica colisao)
            
            Missel tempMissel = misseis.get(i);
            rectMissel = tempMissel.getBounds();//recebe o 'quadrado' em torno do missel
            
            for(int j=0;j<inimigos.size();j++){
              
                Inimigo tempInimigo = inimigos.get(j);
                rectInimigo = tempInimigo.getBounds();
                
                if(rectMissel.intersects(rectInimigo)){
                    tempInimigo.setVisivel(false);
                    tempMissel.setVisivel(false);
                    
                }
            }
        }
    }
    
    private class TecladoAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                inGame = true;
                nave = new Nave();
                inicializaInimigos();
            }
            
            
            nave.keyPressed(e);
        }
        
        @Override
        public void keyReleased(KeyEvent e)
        {
            nave.keyReleased(e);
        }
    }
}
