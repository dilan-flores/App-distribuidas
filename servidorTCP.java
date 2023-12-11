import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class servidorTCP {
    public static void main(String[] args){
        try {
            // Crear socket del servidor
            ServerSocket socketServidor = new ServerSocket(5000);

            while (true){
                // Esperar y acetar conexiones clientes
                Socket socket_cliente = socketServidor.accept();
                System.out.println("Cliente conectado: " + socket_cliente.getInetAddress().getHostAddress());

                // Crear un hilo para manejar la conexion con el cliente
                hiloCliente hilo = new hiloCliente(socket_cliente);
                hilo.start();
            }

        }catch (IOException e){
            System.out.println("Error de E/S: " + e.getMessage());
            e.printStackTrace();
        }
    }
}