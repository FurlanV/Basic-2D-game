/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GameNave;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

/**
 *
 * @author vinicius
 */
public class Inimigo {
    
    private Image imagem;
    private int x,y;
    private int largura,altura;
    private boolean isVisivel;
    
    private static final int LARGURA_TELA = 500; // é assim que faz constante
    private static final int VELOCIDADE = 1;
    
    public void mexer()
    {
        if(this.x < 0)
            this.x = LARGURA_TELA;
        else{
            this.x -= VELOCIDADE;
            //this.y += VELOCIDADE;
        }
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle(x,y,largura,altura);
    }
    
    private static int contador = 0;
    
    public Inimigo(int x,int y) //recebe coordenadas atuais da nave
    {
        ImageIcon referencia;
        this.x = x;
        this.y = y;
        
        //if(contador++ % 2 == 0)
            //referencia =  new ImageIcon("res/enemy.gif");
        //else
            referencia =  new ImageIcon("res/enemy2.gif");
        
         
        imagem = referencia.getImage();
        
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
        
        isVisivel = true;
    }
    
    public boolean isVisivel()
    {
        return isVisivel;
    }
    
    public void setVisivel(boolean isVisivel)
    {
        this.isVisivel = isVisivel;
    }
    
    public Image getImagem()
    {
        return imagem;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
        
}
