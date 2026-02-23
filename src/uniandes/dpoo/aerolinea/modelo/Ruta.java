package uniandes.dpoo.aerolinea.modelo;

/**
 * Esta clase tiene la información de una ruta entre dos aeropuertos que cubre una aerolínea.
 */
public class Ruta
{
    // Attributes
    private String horaSalida;
    private String horaLlegada;
    private String codigoRuta;
    private Aeropuerto origen;
    private Aeropuerto destino;

    // Constructor
    /**
     * Construye una nueva ruta con la información de origen, destino, horarios y código.
     */
    public Ruta(Aeropuerto origen, Aeropuerto destino, String horaSalida, String horaLlegada, String codigoRuta)
    {
        this.origen = origen;
        this.destino = destino;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.codigoRuta = codigoRuta;
    }

    // Getters
    public String getCodigoRuta()
    {
        return codigoRuta;
    }

    public Aeropuerto getOrigen()
    {
        return origen;
    }

    public Aeropuerto getDestino()
    {
        return destino;
    }

    public String getHoraSalida()
    {
        return horaSalida;
    }

    public String getHoraLlegada()
    {
        return horaLlegada;
    }

    /**
     * Calcula la duración del vuelo en minutos.
     * Si la llegada es menor a la salida, se asume que llega el día siguiente.
     * @return La duración total en minutos.
     */
    public int getDuracion()
    {
        int salidaTotal = (getHoras(horaSalida) * 60) + getMinutos(horaSalida);
        int llegadaTotal = (getHoras(horaLlegada) * 60) + getMinutos(horaLlegada);

        int duracion = llegadaTotal - salidaTotal;

        // Caso: El vuelo llega al día siguiente
        if (duracion < 0)
        {
            duracion += 1440; // Se suman los minutos de un día completo (24 * 60)
        }

        return duracion;
    }

    /**
     * Dada una cadena con una hora y minutos, retorna los minutos.
     * Por ejemplo, para la cadena '715' retorna 15.
     */
    public static int getMinutos( String horaCompleta )
    {
        int minutos = Integer.parseInt( horaCompleta ) % 100;
        return minutos;
    }

    /**
     * Dada una cadena con una hora y minutos, retorna las horas.
     * Por ejemplo, para la cadena '715' retorna 7.
     */
    public static int getHoras( String horaCompleta )
    {
        int horas = Integer.parseInt( horaCompleta ) / 100;
        return horas;
    }
}
