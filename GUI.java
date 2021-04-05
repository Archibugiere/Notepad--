package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Key;
import javax.crypto.*;

public class GUI extends JFrame {
    JTextArea text = new JTextArea();
    public GUI(){
        super("Notepad--");

        //Inizializzazione elementi
        JFrame frame = new JFrame();
        frame.setLocationRelativeTo(null);

        JMenuBar barra = new JMenuBar();
        frame.setJMenuBar(barra);

        JMenu file = new JMenu("File");

        JMenuItem nuovo = new JMenuItem("New");
        nuovo.addActionListener(new AscoltaPulsante());
        JMenuItem salva = new JMenuItem("Save");
        salva.addActionListener(new AscoltaPulsante());
        JMenuItem apri = new JMenuItem("Open...");
        apri.addActionListener(new AscoltaPulsante());
        JMenuItem salva_nome = new JMenuItem("Save As...");
        salva_nome.addActionListener(new AscoltaPulsante());

        //font tendina
        JMenu font = new JMenu("font");

        JMenuItem consolas = new JMenuItem("Consolas");
        consolas.addActionListener(new AscoltaPulsante());
        JMenuItem comicSansMS = new JMenuItem("Comic Sans MS");
        comicSansMS.addActionListener(new AscoltaPulsante());
        JMenuItem timesNewRoman = new JMenuItem("Times New Roman");
        timesNewRoman.addActionListener(new AscoltaPulsante());
        JMenuItem arial = new JMenuItem("Arial");
        arial.addActionListener(new AscoltaPulsante());

        font.add(consolas);
        font.add(comicSansMS);
        font.add(timesNewRoman);
        font.add(arial);

        JMenu dimensione = new JMenu("size");
        JTextField campoTesto = new JTextField("12", 2);


        //Grafica
        /*frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        JFrame.setDefaultLookAndFeelDecorated(true);*/

        //textarea
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setBounds(50,50,100,100);
        text.setBackground(new Color(200,200,200));
        text.setBorder(BorderFactory.createBevelBorder(1));
        JScrollPane scrollPane = new JScrollPane(text,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        //Aggiunta elementi
        frame.add(scrollPane);
        file.add(nuovo);
        file.add(salva);
        file.add(apri);
        file.add(salva_nome);
        barra.add(file);
        barra.add(font);
        barra.add(dimensione);
        campoTesto.addActionListener(new AscoltaPulsante());
        dimensione.add(campoTesto);



        //Finalizzazione
        frame.setLayout(new GridLayout(1,1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(400,400));
        frame.pack();
        frame.setVisible(true);
    }
    String directory = "";
    public void setFont(Font font){
        this.text.setFont(font);
    }
    String style="consolas";
    int dim=12;
    class AscoltaPulsante implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            try{
                if(Integer.parseInt(e.getActionCommand())>0 && Integer.parseInt(e.getActionCommand())<100){
                    dim = Integer.parseInt(e.getActionCommand());
                }
            }catch (NumberFormatException ex){
                if(e.getActionCommand().equals("Consolas") || e.getActionCommand().equals("Comic Sans MS") || e.getActionCommand().equals("Times New Roman") || e.getActionCommand().equals("Arial")){
                    style = e.getActionCommand();
                }
            }
            setFont(new Font(style, Font.PLAIN, dim));
            switch (e.getActionCommand()) {
                case "New" -> {
                    MyWriter script = new MyWriter();
                    directory = script.save().getAbsolutePath();
                    MyWriter.fileMother(directory);
                    // TODO: richiama finestra pass

                    // TODO: cifra

                    text.setText(MyWriter.reader(directory));
                }
                case "Save" -> {
                    MyWriter script = new MyWriter();
                    if(directory==""){
                        directory = script.save().getAbsolutePath();
                    }
                    // TODO: richiama finestra pass

                    // TODO: decifra

                    MyWriter.writer(directory,text.getText());
                }
                case "Open..." -> {
                    MyWriter script = new MyWriter();
                    directory = script.find().getAbsolutePath();
                    // TODO: richiama finestra pass

                    // TODO: decifra

                    text.setText(MyWriter.reader(directory));

                }
                case "Save As..." -> {
                    MyWriter script = new MyWriter();
                    directory = script.save().getAbsolutePath();
                    // TODO: richiama finestra pass

                    // TODO: decifra

                    MyWriter.writer(directory,text.getText());
                }
            }
        }
    }
}