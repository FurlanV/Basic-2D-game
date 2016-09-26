/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GameNave;

import java.awt.Image; //Imagem
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.util.List;
/**
 *
 * @author vinicius
 */
public class Nave {
    
    private int x,y;//Coordenadas de posicão da nave
    private int dx,dy; //Proxima posicao
    private int altura,largura;
    private Image imagem;
    private List<Missel> misseis; //faz uma lista de misseis
    private boolean isVisivel;
    
    public Nave()
    {
        ImageIcon referencia = new ImageIcon("res/player.gif"); //seta uma referencia para imagem
        imagem = referencia.getImage();
        
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
        
        misseis = new ArrayList<Missel>();
        
        this.x = 100;
        this.y = 100;
    
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle(x,y,largura,altura);
    }
    
    public List<Missel> getMisseis()
    {
        return misseis;
    }
    
    public void atirar()
    {
        this.misseis.add(new Missel(x+largura,y+altura/2));
    }
            
    public void moverNave()
    {
        x += dx; //limites 1 e 465
        y += dy;// limites 1 e 345
        
        if(this.x < 1)
            x = 1;
        
        if(this.x > 465)
            x = 465;
        
        if(this.y < 1)
            y = 1;
        
        if(this.y > 345)
            y = 345;
        
        
    }
    
    public int getX()//retorna posicao X
    {
        return this.x;
    }
    
    public int getY()
    {
        return this.y;
    }
    
    public Image getImagem()
    {
        return this.imagem;
    }
    
    public boolean isVisivel()
    {
        return isVisivel;
    }
    
    public void setVisibilidade(boolean isVisivel)
    {
        this.isVisivel = isVisivel;
    }
    
    public void keyPressed(KeyEvent tecla)
    {
        int codigo = tecla.getKeyCode(); //codigo recebe o codigo da tecla pressionada
        
        switch(codigo){
            case KeyEvent.VK_UP:
                dy = -1;
                break;
            case KeyEvent.VK_DOWN:
                dy = 1;
                break;
            case KeyEvent.VK_LEFT:
                dx = -1;
                break;
            case KeyEvent.VK_RIGHT:
                dx = 1;
                break;
            case KeyEvent.VK_SPACE:
                atirar();
                break;
        }
    }
    
    public void keyReleased(KeyEvent tecla)
    {
        int codigo = tecla.getKeyCode();
        
        switch(codigo){
            case KeyEvent.VK_UP:
                dy = 0;
                break;
            case KeyEvent.VK_DOWN:
                dy = 0;
                break;
            case KeyEvent.VK_LEFT:
                dx = 0;
                break;
            case KeyEvent.VK_RIGHT:
                dx = 0;
                break;
        }
        
    }
    
}
