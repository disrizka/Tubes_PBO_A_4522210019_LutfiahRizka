package com.disrizka.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;



public class MainScreen extends JFrame{
    private JPanel panelMain;
    private JList jListPerpustakaan;
    private JTextField textFieldNo;
    private JTextField textFieldNama;
    private JTextField textFieldJumlah;
    private JButton buttonInsertFirst;
    private JButton buttonInsertLast;
    private JButton buttonClear;
    private DefaultListModel defaultListModel = new DefaultListModel();
    private List<String> arrayListPerpustakaan = new ArrayList<>();
    private LinkedList<Perpustakaan>listPerpustakaan = new LinkedList<>();
    class Perpustakaan {
        private String no;

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getJumlah() {
            return jumlah;
        }

        public void setJumlah(String jumlah) {
            this.jumlah = jumlah;
        }

        private String nama;
        private String jumlah;
    }
    public MainScreen() {
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                   clearForm();
            }
        });
        buttonInsertFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String no=textFieldNo.getText();
                String nama = textFieldNama.getText();
                String jumlah= textFieldJumlah.getText();

                Perpustakaan perpustakaan = new Perpustakaan();
                perpustakaan.setNo(no);
                perpustakaan.setNama(nama);
                perpustakaan.setJumlah(String.valueOf(jumlah));


                insertFirst(perpustakaan);
                clearForm();
                refreshDataModel();
            }
        });
        buttonInsertLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String no=textFieldNo.getText();
                String nama = textFieldNama.getText();
                String jumlah= textFieldJumlah.getText();

                Perpustakaan perpustakaan = new Perpustakaan();
                perpustakaan.setNo(no);
                perpustakaan.setNama(nama);
                perpustakaan.setJumlah(String.valueOf(jumlah));


                insertLast(perpustakaan);
                clearForm();
                refreshDataModel();
            }
        });
        jListPerpustakaan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                   int index= jListPerpustakaan.getSelectedIndex();

                   Perpustakaan hasilSearch=search(arrayListPerpustakaan.get(index));

                   if(hasilSearch !=null){
                       textFieldNo.setText(hasilSearch.getNo());
                       textFieldNama.setText(hasilSearch.getNama());
                       textFieldJumlah.setText(hasilSearch.getJumlah());
                   }
            }
        });
    }
    private Perpustakaan search(String nama){
        for (Perpustakaan perpustakaan : listPerpustakaan) {
            if(perpustakaan == null)
                break;

            if(perpustakaan.getNama().equals(nama))
                return perpustakaan;
        }
        return null;
    }
    private void insertLast(Perpustakaan perpustakaan){
        listPerpustakaan.insertLast(perpustakaan);
    }
    private void insertFirst(Perpustakaan perpustakaan){
        listPerpustakaan.insertFirst(perpustakaan);
    }

    private void refreshDataModel(){
        arrayListPerpustakaan.clear();
        for (Perpustakaan perpustakaan : listPerpustakaan) {
            if(perpustakaan == null)
                break;

            arrayListPerpustakaan.add(perpustakaan.getNama());
        }
        defaultListModel.clear();
        defaultListModel.addAll(arrayListPerpustakaan);
        jListPerpustakaan.setModel(defaultListModel);
    }
    private void clearForm(){
        textFieldNo.setText("");
        textFieldNama.setText("");
        textFieldJumlah.setText("");
    }
    public static void main(String [] args){
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);
    }

}
