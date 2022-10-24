package com.example.app_phonoaudiology.domain.repository.constants;

public class Constantes {

    public static final String PERSONALIZADO = "Personalizado";
    public static final String NO_PERSONALIZADO = "No Personalizado";
    public static final String SELECCIONE_UNA = "Seleccione una...";

    // CATEGORIAS
    public static final String PALABRA = "Palabra";
    public static final String ORACIONES = "Oraciones";
    public static final String CANCIONES = "Canciones";
    public static final String INSTRUMENTOS = "Instrumentos";
    public static final String ESTILOS_MUSICALES = "Estilos Musicales";
    public static final String VOCES_FAMILIARES = "Voces Familiares";
    public static final String RUIDO = "Ruido";

    //public static final String[] CATEGORIAS = { PALABRA, ORACIONES, CANCIONES, INSTRUMENTOS, ESTILOS_MUSICALES, VOCES_FAMILIARES};
    public static final String[] CATEGORIAS = {SELECCIONE_UNA, PALABRA, ORACIONES};

    // TIPOS DE EJERCICIOS
    public static final String J_DISCRIMINAR = "Discriminar (2 opciones)";
    public static final String J_IDENTIFICAR_TRES_OPCIONES = "Identificar entre 3 opciones";
    public static final String J_IDENTIFICAR_CINCO_OPCIONES = "Identificar entre 5 opciones";
    public static final String J_TODA_LA_CATEGORIA = "Identificar entre toda la categoría";
    public static final String J_ESCRIBIR_LO_QUE_OYO = "Escribir lo que oyó";
    public static final String[] TIPOS_EJERCICIOS_PALABRA = {
            SELECCIONE_UNA,
            J_DISCRIMINAR,
            J_IDENTIFICAR_TRES_OPCIONES,
            J_IDENTIFICAR_CINCO_OPCIONES,
            J_TODA_LA_CATEGORIA,
            J_ESCRIBIR_LO_QUE_OYO};

    // TIPOS DE EJERCICIOS ORACIONES

    public static final String COMPLETAR_ORACION_SIN_OPCIONES = "Completar oración sin opciones";
    public static final String[] TIPOS_EJERCICIOS_ORACIONES =
            {
                    SELECCIONE_UNA,
                    J_IDENTIFICAR_TRES_OPCIONES,
                    J_IDENTIFICAR_CINCO_OPCIONES,
                    J_ESCRIBIR_LO_QUE_OYO,
                    COMPLETAR_ORACION_SIN_OPCIONES
            };

    // SUBCATEGORIA PALABRAS
    public static final String TODAS = "Todo";
    public static final String DIAS_SEMANA = "Días de la Semana";
    public static final String NUMEROS = "Números";
    public static final String COLORES = "Colores";
    public static final String ANIMALES = "Animales";
    public static final String NOMBRES = "Nombres";
    public static final String ROPA = "Ropa";
    public static final String COMIDA = "Comida";
    public static final String LUGARES = "Lugares";
    public static final String MESES = "Meses";
    public static final String VARIOS = "Varios";

    // RUIDO
    public static final String MULTITUD_DE_PERSONAS = "Multitud de personas";
    public static final String RECREO_DE_NINOS = "Recreo de niños";
    public static final String SIRENA_AMBULANCIA = "Sirena ambulancia";
    public static final String TRAFICO_INTENSO = "Tráfico intenso";
    public static final String [] RUIDOS = {
            MULTITUD_DE_PERSONAS,
            RECREO_DE_NINOS,
            SIRENA_AMBULANCIA,
            TRAFICO_INTENSO
    };

    //public static final String [] SUBCATEGORIAS_PALABRAS = {TODAS,DIAS_SEMANA,NUMEROS,COLORES,ANIMALES,NOMBRES,ROPA,COMIDA,LUGARES,MESES};
    public static final String[] SUBCATEGORIAS_PALABRAS = {SELECCIONE_UNA, DIAS_SEMANA, NUMEROS, COLORES, MESES};
    public static final String[] SUBCATEGORIAS_ORACIONES = {SELECCIONE_UNA, DIAS_SEMANA, NUMEROS};

    public static final String[] FILTRO_PRACTICA = {
            TODAS,
            DIAS_SEMANA,
            NUMEROS,
            COLORES,
            ANIMALES,
            NOMBRES,
            ROPA,
            COMIDA,
            LUGARES,
            MESES,
            ORACIONES,
            VOCES_FAMILIARES,
            ESTILOS_MUSICALES,
            CANCIONES,
            INSTRUMENTOS,
            RUIDO};

    public static final String EJERCITACION = "Ejercitación";
    public static final String EVALUACION = "Evaluación";

    public static final String SAME_EXERCISE_ERROR = ",";
    public static final String NEXT_SEPARATOR_ERROR = "-";

}
