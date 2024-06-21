package com.tpFinal.swing;

import com.tpFinal.entidades.Alumno;
import com.tpFinal.entidades.Curso;
import com.tpFinal.entidades.Factura;
import com.tpFinal.entidades.Inscripcion;
import com.tpFinal.enumeraciones.DiaSemana;
import com.tpFinal.excepciones.ExceptionPersonalizada;
import com.tpFinal.repository.Repository;
import com.tpFinal.sistema.Sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JList<Factura> facturaslist1;
    private JButton detalleFacturaButton;
    private JLabel idfactura;
    private JLabel alumnoFactura;
    private JLabel cursoFactura;
    private JLabel valorFactura;
    private Sistema sistema;

    public menuAlumno(Alumno alumno, List<Curso> cursoList, Sistema sistema) {
        this.alumno = alumno;
        this.cursoInscripcion = cursoList;
        setContentPane(panelAlumno);
        setLocation(600,200);
        setSize(500, 500);
        setModal(true);
        this.sistema = sistema;

        nombre.setText(alumno.getNombre());
        apellido.setText(alumno.getApellido());
        mail.setText(alumno.getEmail());
        legajo.setText(alumno.getLegajo());
        inicializarJListCursos();
        inicializarInscripciones();
        inicializarJListFacturas();


        logOutButton.addActionListener(e ->{
            {
             setVisible(false);

            }
        });



    }
    private void inicializarInscripciones()
    {
        actualizarListaInscripciones();
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
            profesorInscripcion.setText(curso.getProfesor()); // ES EL NOMBRE
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

            if (cursonuevo != null) {
                Factura factura = new Factura( cursonuevo.getCursosNombre());



                try {
                    alumno.addInscripcion(cursonuevo);
                    cursonuevo.agregarAlumnos(alumno);
                    alumno.agregarFactura(factura);

                    JOptionPane.showMessageDialog(null, "Inscripción añadida exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    actualizarListaCursos();
                    actualizarListaInscripciones();
                    actualizarListaFacturas();
                }   catch (ExceptionPersonalizada er) {
                    JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún curso.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

        });



    }
    private void inicializarJListCursos(){
        actualizarListaCursos();
        DefaultListModel<Curso> listModel = new DefaultListModel<>();

        for (Curso curso : alumno.getCursosPagos()) {
            listModel.addElement(curso);
        }
        cursosList.setModel(listModel);
        configurarRendererJListCursos();

        informacionButton.addActionListener(e-> {

                Curso cursoseleccionado= cursosList.getSelectedValue();
                nombreCurso.setText(cursoseleccionado.getCursosNombre().name());
                profesorNombre.setText(cursoseleccionado.getProfesor()); //RECIBE EL NOMBRE
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
    public void inicializarJListFacturas(){
                actualizarListaFacturas();
        DefaultListModel<Factura> listModel = new DefaultListModel<>();

        for (Factura factura: alumno.getListFacturas()) {
            listModel.addElement(factura);
        }

        facturaslist1.setModel(listModel);
        configurarRendererJListFacturas();


           detalleFacturaButton.addActionListener(e -> {
           Factura factura = facturaslist1.getSelectedValue();
           idfactura.setText(String.valueOf(factura.getIdFactura()));
           alumnoFactura.setText(alumno.getApellido());
           cursoFactura.setText(factura.getCurso().name());
           valorFactura.setText(String.valueOf(factura.getValor()));
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
    private void configurarRendererJListFacturas() {
        facturaslist1.setCellRenderer(new ListCellRenderer<Factura>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends Factura> list, Factura value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = new JLabel(value.getCurso().name());
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
//TODO TRATANDO DE QUE ACTUALICE EN SESION SIN HACER LOGOUT Y LOGIN
private void actualizarListaCursos() {
    DefaultListModel<Curso> listModel = new DefaultListModel<>();
    for (Curso curso : alumno.getCursosPagos()) {
        listModel.addElement(curso);
    }
    cursosList.setModel(listModel);
    configurarRendererJListCursos();
}

    private void actualizarListaInscripciones() {
        DefaultListModel<Curso> listModel = new DefaultListModel<>();
        for (Curso curso : cursoInscripcion) {
            listModel.addElement(curso);
        }
        inscripcionlist1.setModel(listModel);
        configurarRendererJListInscripciones();
    }

    private void actualizarListaFacturas() {
        DefaultListModel<Factura> listModel = new DefaultListModel<>();
        for (Factura factura: alumno.getListFacturas()) {
            listModel.addElement(factura);
        }
        facturaslist1.setModel(listModel);
        configurarRendererJListFacturas();
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public Curso getCursonuevo() {
        return cursonuevo;
    }

    public List<Curso> getCursoInscripcion() {
        return cursoInscripcion;
    }
}
