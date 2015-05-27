package com.upv.recetario.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Receta {
	
	private StringProperty nombrePlato;
	private StringProperty imagenPlato;
	private StringProperty categoria;
	private IntegerProperty comensales;
	private StringProperty dificultad;
	private IntegerProperty tiempoPreparacion;
	private IntegerProperty tiempoCoccion;
	private StringProperty ingredientes;
	private StringProperty preparacion;
	private StringProperty observaciones;
	
	public Receta(String nombre, String categoria, String dificultad){
		this.nombrePlato = new SimpleStringProperty(nombre);
		this.categoria = new SimpleStringProperty(categoria);
		this.dificultad = new SimpleStringProperty(dificultad);
	}
    public String getNombrePlato() {
        return nombrePlato.get();
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato.set(nombrePlato);
    }
    
    public StringProperty nombrePlatoProperty() {
        return nombrePlato;
    }
    
    public String getImagenPlato() {
        return imagenPlato.get();
    }

    public void setImagenPlato(String imagenPlato) {
        this.imagenPlato.set(imagenPlato);
    }
    
    public StringProperty imagenPlatoProperty() {
        return imagenPlato;
    }
    
    
    public String getCategoria() {
        return categoria.get();
    }

    public void setCategoria(String categoria) {
        this.categoria.set(categoria);
    }
    
    public StringProperty categoriaProperty() {
        return categoria;
    }
    
    
    public Integer getComensales() {
        return comensales.get();
    }
    
   
    public void setComensales(Integer comensales) {
        this.comensales.set(comensales);
    }
    
    public IntegerProperty comensalesProperty() {
        return comensales;
    }
    
    public String getDificultad() {
        return dificultad.get();
    }

    public void setDificultad(String dificultad) {
        this.dificultad.set(dificultad);
    }
    
    public StringProperty dificultadProperty() {
        return dificultad;
    }    
    
    public Integer getTiempoPreparacion() {
        return tiempoPreparacion.get();
    }

    public void settTempoPreparacion(Integer tiempoPreparacion) {
        this.tiempoPreparacion.set(tiempoPreparacion);
    }
    
    public IntegerProperty tiempoPreparacionProperty() {
        return tiempoPreparacion;
    }
    
    public Integer getTiempoCoccion() {
        return tiempoCoccion.get();
    }

    public void setTiempoCoccion(Integer tiempoCoccion) {
        this.tiempoCoccion.set(tiempoCoccion);
    }
    
    public IntegerProperty tiempoCoccionProperty() {
        return tiempoCoccion;
    }
    
    public String getIngredientes() {
        return ingredientes.get();
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes.set(ingredientes);
    }
    
    public StringProperty ingredientesProperty() {
        return ingredientes;
    }    
    
    
    public String getPreparacion() {
        return preparacion.get();
    }

    public void setPreparacion(String preparacion) {
        this.preparacion.set(preparacion);
    }
    
    public StringProperty preparacionProperty() {
        return preparacion;
    }    
            
    
    public String getObservaciones() {
        return observaciones.get();
    }

    public void setObservaciones(String observaciones) {
        this.observaciones.set(observaciones);
    }
    
    public StringProperty observacionesProperty() {
        return observaciones;
    }    
}
