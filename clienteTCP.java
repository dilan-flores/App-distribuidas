import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class clienteTCP {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Scanner mensaje = new Scanner(System.in);
        try {
            // Crear un socket para conectarse al servidor
            Socket socket_cliente = new Socket("localhost", 5000);
            System.out.println("Cliente conectado");
            try (BufferedReader entrada = new BufferedReader(
                    (new InputStreamReader(socket_cliente.getInputStream())))) {
                PrintWriter salida = new PrintWriter(socket_cliente.getOutputStream(), true);

                while (true) {
                    // Enviar datos del cliente
                    String mensaje_Cliente = mensaje.nextLine();
                    salida.println(mensaje_Cliente);

                    // Leer datos recibidos desde de el cliente
                    String datos_recibidos = entrada.readLine();
                    System.out.println("Mensaje recibido : " + datos_recibidos);

                    if(datos_recibidos.equalsIgnoreCase("fin")){
                        System.out.println(".......... fin ........");
                        return;
                    }
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}