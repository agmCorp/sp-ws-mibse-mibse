package com.bse.servicios.utilitario.log;

import java.util.ArrayList;

public class Logueo {

    public static final String ENCABEZADOPRES = "Capa presentacion";
    public static final String ENCABEZADOWS = "WebService";
    public static final String ENCABEZADONEG = "Capa negocio";
    public static final String ENCABEZADOEJB = "Negocio EJB";
    public static final String ENCABEZADOPERSIST = "Capa persistencia";
    public static final String ENCABEZADOVAL = "Modulo validaciones";
    public static final String ENCABEZADOPARSEO = "Modulo parseo";
    public static final String TEXTONULL = "null";

    private String encabezado;
    private String error;
    private String clase;
    private String rutaClase;
    private String metodo;
    private final ArrayList<String> parametros;
    private final ArrayList<String> eventos;
    private String exception;
    private String pl;
    private String sql;
    private Double ejecucion;
    private String errorDatos;

    public Logueo() {
        this.parametros = new ArrayList<String>();
        this.eventos = new ArrayList<String>();
        this.encabezado = "Clase de logueo";
        this.clase = null;
        this.metodo = null;
        this.error = null;
        this.exception = null;
        this.pl = null;
        this.sql = null;
        this.ejecucion = null;
    }

    /**
     * Establece un titulo orientativo para mostrar al momento de loguear.
     * 
     * @param encabezado
     */
    public void setEncabezado(final String encabezado) {
        this.encabezado = encabezado;
    }

    /**
     * Establece en que clase se est\u00e1 logueando, al ingresar este dato se
     * guarda el nombre de la clase y su ruta completa.
     * 
     * @param c
     */
    public void setClase(final Class<?> c) {
        this.clase = c.getSimpleName();
        this.rutaClase = c.getName();
    }

    /**
     * Establece el metodo en el cual se esta logueando.
     * 
     * @param metodo
     */
    public void setMetodo(final String metodo) {
        this.metodo = metodo;
    }

    /**
     * Establece el error generado.
     * 
     * @param error
     */
    public void setError(final String error) {
        this.error = error;
    }

    /**
     * Establece un parametro String para loguear.
     * 
     * @param clave
     * @param valor
     */
    public void setParametro(final String clave, final String valor) {
        if (valor == null) {
            this.parametros.add(clave + ": [null]");
        } else {
            this.parametros.add(clave + ": [" + valor + "]");
        }
    }

    /**
     * Establece un parametro Integer para loguear.
     * 
     * @param clave
     * @param valor
     */
    public void setParametro(final String clave, final Integer valor) {
        if (valor == null) {
            this.parametros.add(clave + ": [null]");
        } else {
            this.parametros.add(clave + ": [" + valor.toString() + "]");
        }
    }

    /**
     * Establece un parametro Double para loguear.
     * 
     * @param clave
     * @param valor
     */
    public void setParametro(final String clave, final Double valor) {
        if (valor == null) {
            this.parametros.add(clave + ": [null]");
        } else {
            this.parametros.add(clave + ": [" + valor.toString() + "]");
        }
    }

    /**
     * Establece un parametro Boolean para loguear.
     * 
     * @param clave
     * @param valor
     */
    public void setParametro(final String clave, final Boolean valor) {
        if (valor == null) {
            this.parametros.add(clave + ": [null]");
        } else {
            this.parametros.add(clave + ": [" + valor.toString() + "]");
        }
    }

    /**
     * Establece un parametro Long para loguear.
     * 
     * @param clave
     * @param valor
     */
    public void setParametro(final String clave, final Long valor) {
        if (valor == null) {
            this.parametros.add(clave + ": [null]");
        } else {
            this.parametros.add(clave + ": [" + valor.toString() + "]");
        }
    }

    /**
     * Utilizar este metodo para setear eventos que permitan rastrear posibles
     * problemas.
     * 
     * @param evento
     */
    public void setEvento(final String evento) {
        this.eventos.add(evento);
    }

    /**
     * Establece el nombre del pl que se estaba llamando cuando se logueo.
     * 
     * @param pl
     */
    public void setNombrePl(final String pl) {
        this.pl = pl;
    }

    /**
     * Establece en que excepci\u00f3n se logueo.
     * 
     * @param exception
     */
    public void setException(final String exception) {
        this.exception = exception;
    }

    /**
     * Establece la consulta sql.
     * 
     * @param sql
     */
    public void setSql(final String sql) {
        this.sql = sql;
    }

    /**
     * Establece el tiempo de ejecuci\u00f3n
     * 
     * @param milisegundos
     */
    public void setExecMilis(final Long milisegundos) {
        if (milisegundos != null) {
            this.ejecucion = new Double(milisegundos / 1000);
        }
    }

    /**
     * Se utiliza cuando no se producen excepciones sino que no se cumplen
     * determinadas condiciones. Este error es mostrado cuando se utiliza el
     * metodo getSoloParametros().
     * 
     * @param errorDatos
     */
    public void setErrorDatos(final String errorDatos) {
        this.errorDatos = errorDatos;
    }

    /**
     * Listado de todos los parametros ingresados y PL si fue seteado.
     * 
     * @return String con lista de los datos con salto de linea
     */
    public String getSoloParametros() {
        final StringBuffer resultado = new StringBuffer();

        resultado.append("\n********************************");

        if (this.encabezado != null) {
            resultado.append("\n- ");
            resultado.append(this.encabezado);
            resultado.append(" -");
        }

        if (this.clase != null) {
            resultado.append("\n* Clase: ");
            resultado.append(this.clase);
        }
        
        if (this.metodo != null) {
            resultado.append("\n* Metodo: ");
            resultado.append(this.metodo);
        }

        if (this.pl != null) {
            resultado.append("\n* PL: ");
            resultado.append(this.pl);
        }

        if (this.parametros.size() != 0) {
            resultado.append("\n* Parametros:");

            for (int i = 0; i < this.parametros.size(); i++) {
                resultado.append("\n* - ");
                resultado.append(this.parametros.get(i));
            }
        }

        if (this.ejecucion != null) {
            resultado.append("\n* Ejecucion en segundos: ");
            resultado.append(this.ejecucion.toString());
        }

        if (this.errorDatos != null) {
            resultado.append("\n* Error de datos: ");
            resultado.append(this.errorDatos);
        }

        if (this.eventos.size() != 0) {
            resultado.append("\n* Eventos:");

            for (int i = 0; i < eventos.size(); i++) {
                resultado.append("\n- ");
                resultado.append(this.eventos.get(i));
            }
        }

        if (this.sql != null) {
            resultado.append("\n* Consulta sql: ");
            resultado.append(this.sql);
        }

        resultado.append("\n********************************");

        return resultado.toString();
    }

    /**
     * Listado de todos los datos ingresados.
     * 
     * @return String con lista de los datos con salto de linea
     */
    public String getMensaje() {
        final StringBuffer resultado = new StringBuffer();

        resultado.append("\n********************************\n* Encabezado: ");
        resultado.append(this.encabezado);
        resultado.append("\n* Clase: ");
        resultado.append(this.clase);
        resultado.append("\n* Ubicacion: ");
        resultado.append(this.rutaClase);
        resultado.append("\n* Metodo: ");
        resultado.append(this.metodo);

        if (this.pl != null) {
            resultado.append("\n* PL: ");
            resultado.append(this.pl);
        }

        if (this.parametros.size() != 0) {
            resultado.append("\n* Parametros:");

            for (int i = 0; i < this.parametros.size(); i++) {
                resultado.append("\n* - ");
                resultado.append(this.parametros.get(i));
            }
        }

        if (this.exception != null) {
            resultado.append("\n* exception: ");
            resultado.append(this.exception);
        }

        if (this.ejecucion != null) {
            resultado.append("\n* Ejecucion en segundos: ");
            resultado.append(this.ejecucion.toString());
        }

        if (this.errorDatos != null) {
            resultado.append("\n* Error de datos: ");
            resultado.append(this.errorDatos);
        }

        if (this.eventos.size() != 0) {
            resultado.append("\n* Eventos:");

            for (int i = 0; i < eventos.size(); i++) {
                resultado.append("\n- ");
                resultado.append(this.eventos.get(i));
            }
        }

        if (this.sql != null) {
            resultado.append("\n* Consulta sql: ");
            resultado.append(this.sql);
        }

        resultado.append("\n* Error: ");
        resultado.append(this.error);
        resultado.append("\n********************************");

        return resultado.toString();
    }

    public String getErrorDatos() {
        final StringBuffer resultado = new StringBuffer();

        resultado.append("\n********************************\n* Encabezado: ");
        resultado.append(this.encabezado);
        resultado.append("\n* Clase: ");
        resultado.append(this.clase);
        resultado.append("\n* Ubicacion: ");
        resultado.append(this.rutaClase);
        resultado.append("\n* Metodo: ");
        resultado.append(this.metodo);

        if (this.errorDatos != null) {
            resultado.append("\n* Error de datos: ");
            resultado.append(this.errorDatos);
        }

        return resultado.toString();
    }
}
