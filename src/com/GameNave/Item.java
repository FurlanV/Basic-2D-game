/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GameNave;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author vinicius
 */
public class Item {
    
    private Image imagem;
    private int x,y;
    private int altura,largura;
    private boolean isVisivel;
    
    public Item(int x,int y)
    {
        ImageIcon referencia;
        referencia = new ImageIcon("res/box.gif");
        imagem = referencia.getImage();
        
        this.x = x;
        this.y = y;
        
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
        
        this.isVisivel = false;

    }
    
    public boolean getVisibilidade()
    {
        return isVisivel;
    }
    
    public void setVisibilidade(boolean isVisivel)
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
    
    public Rectangle getBounds()
    {
        return new Rectangle(x,y,largura,altura);
    }
}
