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
public class Missel {
    
    private Image imagem;
    private int x,y;
    private int largura,altura;
    private boolean isVisivel;
    
    private static final int LARGURA_TELA = 500; // é assim que faz constante
    private static final int VELOCIDADE = 2;
    
    public void atiraMissel()
    {
        this.x += VELOCIDADE; //toda vez que a tela é atualizada o pixel do missel é somaoo
        
        if(this.x > LARGURA_TELA)
            isVisivel = false;
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle(x,y,largura,altura);
    }
    
    public Missel(int x,int y) //recebe coordenadas atuais da nave
    {
        this.x = x;
        this.y = y;
        
        ImageIcon referencia = new ImageIcon("res/missel.png");
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
