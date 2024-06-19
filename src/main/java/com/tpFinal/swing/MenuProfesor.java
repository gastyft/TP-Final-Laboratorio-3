package com.tpFinal.swing;

import com.tpFinal.entidades.Alumno;
import com.tpFinal.entidades.Curso;
import com.tpFinal.entidades.Profesor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MenuProfesor extends JDialog {

    private Profesor profesor;
    private List<Curso> cursosDisponibles;
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JButton logOutButton;
    private JLabel nombre;
    private JLabel apellido;
    private JLabel mail;
    private JLabel legajo;
    private JList<Curso> cursoslist;
    private JButton buscarButton;
    private JList<Alumno> alumnoslist1;
    private JLabel nombrecurso;
    private JLabel inicioCurso;
    private JLabel finCurso;

    public MenuProfesor(Profesor profesor, List<Curso> cursosDisponibles) {

        this.profesor = profesor;
        this.cursosDisponibles = cursosDisponibles;
        setContentPane(panel1);
        setLocation(600,200);
        setSize(500, 500);
        setModal(true);
        nombre.setText(profesor.getNombre());
        apellido.setText(profesor.getApellido());
        mail.setText(profesor.getEmail());
        legajo.setText(profesor.getLegajo());


        logOutButton.addActionListener(e ->{
            {
                setVisible(false);
            }
        });

    }

    private void inicializarCursos(){

        DefaultListModel<Curso> listModel = new DefaultListModel<>();

        for (Curso curso : profesor.getCursos()) {
            listModel.addElement(curso);
        }
        cursoslist.setModel(listModel);
        configurarRendererJListCursos();

        buscarButton.addActionListener(e -> {

            Curso cursoseleccionado= cursoslist.getSelectedValue();
            nombrecurso.setText(cursoseleccionado.getCursosNombre().name());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            String formattedDateTimeinicio = cursoseleccionado.getFecha().getFechaInicio().format(formatter);
            String formattedDateTimefin = cursoseleccionado.getFecha().getFechaFin().format(formatter);
            inicioCurso.setText(formattedDateTimeinicio);
            finCurso.setText(formattedDateTimefin);


        });



    }
    private void configurarRendererJListCursos() {
        cursoslist.setCellRenderer(new ListCellRenderer<Curso>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends Curso> list, Curso value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = new JLabel(value.getCursosNombre().name());
                if (isSelected) {
                    label.setBackground(list.getSelectionBackground());
                    label.setForeground(list.getSelectionForeground());
                } else {
                    label.setBackground(list.getBackground());
                    label.setForeground(list.getForeground());
                }
                label.setOpaque(true);
                return label;
            }
        });
    }
}
