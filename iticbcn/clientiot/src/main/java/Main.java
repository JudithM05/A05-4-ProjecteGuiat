

public class Main {

    public static void main(String[] args) {
        ClientIot awsIotClient = new ClientIot();
        DatabaseClient databaseClient = new DatabaseClient();

        // Nombre del dispositivo IoT
        String thingName = "myThing";

        // Obtener datos del dispositivo
        String shadowData = awsIotClient.getShadowData(thingName);

        // Guardar datos en la base de datos
        databaseClient.saveData(thingName, shadowData);

        System.out.println("Datos guardados correctamente.");
    }
}
