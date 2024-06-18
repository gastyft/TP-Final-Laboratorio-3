package com.tpFinal.swing;

import com.tpFinal.entidades.Alumno;
import com.tpFinal.entidades.Curso;
import com.tpFinal.enumeraciones.DiaSemana;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class menuAlumno extends JDialog{

    Alumno alumno;
    private JPanel panelAlumno;
    private JTabbedPane tabbedPane1;
    private JLabel nombre;
    private JLabel apellido;
    private JLabel mail;
    private JLabel legajo;
    private JButton logOutButton;
    private JList<Curso> cursosList;
    private JButton informacionButton;
    private JList<DiaSemana> diaslist1;
    private JLabel nombreCurso;
    private JLabel profesorNombre;
    private JLabel fechaInicio;
    private JLabel fechaFin;

    public menuAlumno(Alumno alumno) {
        this.alumno = alumno;
        setContentPane(panelAlumno);
        setLocation(600,200);
        setSize(500, 500);
        setModal(true);
        nombre.setText(alumno.getNombre());
        apellido.setText(alumno.getApellido());
        mail.setText(alumno.getEmail());
        legajo.setText(alumno.getLegajo());
        inicializarJListCursos();


        logOutButton.addActionListener(e ->{
            {
             setVisible(false);
            }
        });
    }
    private void inicializarJListCursos(){
        DefaultListModel<Curso> listModel = new DefaultListModel<>();
        for (Curso curso : alumno.getCursosPagos()) {
            listModel.addElement(curso);
        }
        cursosList.setModel(listModel);
        configurarRendererJListCursos();

        informacionButton.addActionListener(e-> {
             {  Curso cursoseleccionado= cursosList.getSelectedValue();
                nombreCurso.setText(cursoseleccionado.getNombre());
                profesorNombre.setText(cursoseleccionado.getProfesor().getNombre());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                String formattedDateTimeinicio = cursoseleccionado.getFecha().getFechaInicio().format(formatter);
                String formattedDateTimefin = cursoseleccionado.getFecha().getFechaFin().format(formatter);
                fechaInicio.setText(formattedDateTimeinicio);
                fechaFin.setText(formattedDateTimefin);

                 DefaultListModel<DiaSemana> diaSemanaModel = new DefaultListModel<>();
                 for (DiaSemana dia : cursoseleccionado.getFecha().getDiasemana()) {
                     diaSemanaModel.addElement(dia);
                 }
                 diaslist1.setModel(diaSemanaModel);



            }
        });


    }
    private void configurarRendererJListCursos() {
        cursosList.setCellRenderer(new ListCellRenderer<Curso>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends Curso> list, Curso value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = new JLabel(value.getNombre());
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
