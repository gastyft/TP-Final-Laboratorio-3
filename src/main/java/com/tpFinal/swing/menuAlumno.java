package com.tpFinal.swing;

import com.tpFinal.entidades.Alumno;
import com.tpFinal.entidades.Curso;
import com.tpFinal.entidades.Factura;
import com.tpFinal.entidades.Inscripcion;
import com.tpFinal.enumeraciones.DiaSemana;
import com.tpFinal.excepciones.ExceptionPersonalizada;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class menuAlumno extends JDialog{

    private Alumno alumno;
    private Curso cursonuevo;
    private List<Curso> cursoInscripcion;
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
    private JList<Curso> inscripcionlist1;
    private JButton informacionDelCursoButton;
    private JLabel nombreInscripcion;
    private JLabel profesorInscripcion;
    private JLabel inicioInscripcion;
    private JLabel finInscripcion;
    private JList diaslist2;
    private JButton inscribirseAlCursoButton;
    private JList facturaslist1;
    private JButton detalleFacturaButton;

    public menuAlumno(Alumno alumno, List<Curso> cursoList) {
        this.alumno = alumno;
        this.cursoInscripcion = cursoList;
        setContentPane(panelAlumno);
        setLocation(600,200);
        setSize(500, 500);
        setModal(true);
        nombre.setText(alumno.getNombre());
        apellido.setText(alumno.getApellido());
        mail.setText(alumno.getEmail());
        legajo.setText(alumno.getLegajo());
        inicializarJListCursos();
        inicializarInscripciones();


        logOutButton.addActionListener(e ->{
            {
             setVisible(false);
            }
        });


    }
    private void inicializarInscripciones()
    {
        DefaultListModel<Curso> listModel = new DefaultListModel<>();
        for (Curso curso : cursoInscripcion) {
            listModel.addElement(curso);
        }

        inscripcionlist1.setModel(listModel);
         configurarRendererJListInscripciones();

        informacionDelCursoButton.addActionListener(e -> {

            Curso curso = inscripcionlist1.getSelectedValue();
            this.cursonuevo = curso;
            nombreInscripcion.setText(curso.getCursosNombre().name());
            profesorInscripcion.setText(curso.getProfesor().getNombre());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            String formattedDateTimeinicio = curso.getFecha().getFechaInicio().format(formatter);
            String formattedDateTimefin = curso.getFecha().getFechaFin().format(formatter);
            inicioInscripcion.setText(formattedDateTimeinicio);
            finInscripcion.setText(formattedDateTimefin);

            DefaultListModel<DiaSemana> diaSemanaModel = new DefaultListModel<>();
            for (DiaSemana dia : curso.getFecha().getDiasemana()) {
                diaSemanaModel.addElement(dia);
            }
            diaslist2.setModel(diaSemanaModel);



        });

        inscribirseAlCursoButton.addActionListener(e ->{

            Factura factura = new Factura(alumno,cursonuevo.getCursosNombre());
            Inscripcion inscripcion1 = new Inscripcion(cursonuevo,alumno,factura);
            try {

                alumno.addInscripcion(inscripcion1);
                JOptionPane.showMessageDialog(null, "Inscripción añadida exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cursonuevo.agregarAlumnos(alumno);
                alumno.agregarCurso(cursonuevo);
            }catch (ExceptionPersonalizada er){

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

              Curso cursoseleccionado= cursosList.getSelectedValue();
                nombreCurso.setText(cursoseleccionado.getCursosNombre().name());
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




        });


    }
    private void configurarRendererJListCursos() {
        cursosList.setCellRenderer(new ListCellRenderer<Curso>() {
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
    private void configurarRendererJListInscripciones() {
        inscripcionlist1.setCellRenderer(new ListCellRenderer<Curso>() {
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
