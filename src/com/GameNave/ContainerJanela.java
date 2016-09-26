/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GameNave;

import javax.swing.JFrame;

/**
 *
 * @author vinicius
 */
public class ContainerJanela extends JFrame {
    
    public ContainerJanela() //construtor
    {        
        add(new Fase());
        setTitle("Meu Jogo");
        setSize(500,400);
        setLocationRelativeTo(null);//Onde a janela vai aparecer null = centro
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Define o método default quando a aplicação é fechada
        setResizable(false);
        setVisible(true);
    }
    
    
}
