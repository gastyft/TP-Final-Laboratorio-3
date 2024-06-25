package com.tpFinal.swing;

import com.tpFinal.entidades.Curso;
import com.tpFinal.entidades.Fecha;
import com.tpFinal.entidades.Profesor;
import com.tpFinal.enumeraciones.CursosNombre;
import com.tpFinal.enumeraciones.DiaSemana;
import com.tpFinal.excepciones.ExceptionPersonalizada;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MenuProfesor extends JDialog {

    private Profesor profesor;
    private List<Curso> cursosDisponibles;
    private Curso cursoseleccionado;
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JButton logOutButton;
    private JLabel nombre;
    private JLabel apellido;
    private JLabel mail;
    private JLabel legajo;
    private JList<Curso> cursoslist;
    private JButton buscarButton;
    private JList<String> alumnoslist1;
    private JLabel nombrecurso;
    private JLabel inicioCurso;
    private JLabel finCurso;
    private JComboBox<CursosNombre> materiaBox1;
    private JRadioButton lunesRadioButton;
    private JRadioButton martesRadioButton;
    private JRadioButton miercolesRadioButton;
    private JRadioButton juevesRadioButton;
    private JRadioButton viernesRadioButton;
    private JComboBox horaBox;
    private JComboBox mesBox;
    private JButton crearCursoButton;
    private JList<DiaSemana> diasList;
    private JButton eliminarCursoButton;

    public MenuProfesor(Profesor profesor1, List<Curso> cursos) {

        this.profesor = profesor1;
        this.cursosDisponibles = cursos;
        setContentPane(panel1);
        setLocation(600,200);
        setSize(500, 500);
        setModal(true);
        nombre.setText(profesor.getNombre());
        apellido.setText(profesor.getApellido());
        mail.setText(profesor.getEmail());
        legajo.setText(profesor.getLegajo());

            inicializarCursos();
            inicializarCrearCurso();


        logOutButton.addActionListener(e ->{
            {
                setVisible(false);
            }
        });



    }
    private void inicializarCrearCurso(){

        for (CursosNombre curso : CursosNombre.values()) {
            materiaBox1.addItem(curso);
        }

        for (Integer i = 6; i <= 10; i++){
            mesBox.addItem(i);
        }
        for (int i = 8; i <= 22; i += 2) {
            horaBox.addItem(i);
        }

        crearCursoButton.addActionListener( e ->{

            int mesSeleccionado = (int) mesBox.getSelectedItem();
            int horaSeleccionada = (int) horaBox.getSelectedItem();

            LocalDateTime fechaInicio = LocalDateTime.of(2024, mesSeleccionado, 1, horaSeleccionada, 0);

            LocalDateTime fechaFin = fechaInicio.plusMonths(2).plusHours(2);

            List<DiaSemana> diaSemanas = new ArrayList<>();

            if(lunesRadioButton.isSelected()){
                diaSemanas.add(DiaSemana.LUNES);
            }
            if(martesRadioButton.isSelected()){
                diaSemanas.add(DiaSemana.MARTES);
            }
            if(miercolesRadioButton.isSelected()){
                diaSemanas.add(DiaSemana.MIERCOLES);
            }
            if(juevesRadioButton.isSelected()){
                diaSemanas.add(DiaSemana.JUEVES);
            }
            if(viernesRadioButton.isSelected()){
                diaSemanas.add(DiaSemana.VIERNES);
            }
            if (diaSemanas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un día.", "Error", JOptionPane.ERROR_MESSAGE);
            }else{

                Fecha fecha = new Fecha(fechaInicio,fechaFin,diaSemanas);
                String nombreProfesor = profesor.getNombre()+" " + profesor.getApellido();

                Curso curso = new Curso((CursosNombre) materiaBox1.getSelectedItem(),nombreProfesor,fecha);

                try {

                    profesor.addCurso(curso);
                    cursosDisponibles.add(curso);

                    JOptionPane.showMessageDialog(null, "curso creado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    actualizarCursos();

                }catch (ExceptionPersonalizada er){
                    JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }



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
             cursoseleccionado = cursoslist.getSelectedValue();

            nombrecurso.setText(cursoseleccionado.getCursosNombre().name());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            String formattedDateTimeinicio = cursoseleccionado.getFecha().getFechaInicio().format(formatter);
            String formattedDateTimefin = cursoseleccionado.getFecha().getFechaFin().format(formatter);
            inicioCurso.setText(formattedDateTimeinicio);
            finCurso.setText(formattedDateTimefin);



            DefaultListModel<DiaSemana> diaSemanaModel = new DefaultListModel<>();
            for (DiaSemana dia : cursoseleccionado.getFecha().getDiasemana()) {
                diaSemanaModel.addElement(dia);
            }
            diasList.setModel(diaSemanaModel);

            DefaultListModel<String> alumnoModel = new DefaultListModel<>();
            for (String alumno : cursoseleccionado.getAlumnosInscriptos()) {
                alumnoModel.addElement(alumno);
            }
            alumnoslist1.setModel(alumnoModel);



        });
        eliminarCursoButton.addActionListener(es -> {
            try {
                profesor.eliminarCurso(cursoseleccionado);
                JOptionPane.showMessageDialog(null, "curso eliminado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }catch (ExceptionPersonalizada er){
                JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            }

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
    private void actualizarCursos(){
        DefaultListModel<Curso> listModel = new DefaultListModel<>();

        for (Curso curso : profesor.getCursos()) {
            listModel.addElement(curso);
        }
        cursoslist.setModel(listModel);
        configurarRendererJListCursos();

    }

    public Profesor getProfesor() {
        return profesor;
    }
}

