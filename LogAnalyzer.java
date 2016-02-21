/**
 * Read web server data and analyse
 * hourly access patterns.
 * 
 * @author David J. Barnes and Michael KÃ¶lling.
 * @version 2011.07.31
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }
    
    /**
     * Segundo constructor de la clase LogAnalyzer
     */
    public LogAnalyzer(String nombreDelArchivo){
        hourCounts = new int[24];
        reader = new LogfileReader(nombreDelArchivo);
    }
    
    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        int hour = 0;
        while( hour < hourCounts.length) {
            System.out.println(hour + ": " + hourCounts[hour]);
            hour++;
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
     * Método que devuelve el número total de accesos al servidor web registrados en el archivo de log
     */
    public int numberOfAccesses()
     {
         int total = 0;
         int hora = 0;
         while(hora < hourCounts.length) {
             total = total + hourCounts[hora];
             hora++;
         }
         return total;
     }
     
     /**
      * Método que devuelve en qué hora el servidor tuvo que responder a más peticiones. 
      * Si hay empate devuelve la última de las horas. Si no ha habido accesos informa del hecho por pantalla y devuelve -1.
      */
     public int busiestHour()
     {
         int hora = -1;
         int numeroDeVisitasPorHora = 0;
         int hour = 0;
         while(hour < hourCounts.length) {
             if(numeroDeVisitasPorHora <= hourCounts[hour] && hourCounts[hour] != 0)
             {
                 hora = hour;
                 numeroDeVisitasPorHora = hourCounts[hour];
             }
             hour++;
         }
         if (hora == -1)
         {
             System.out.println("El servidor no a tenido accesos");
         }
         return hora;
     }
}
