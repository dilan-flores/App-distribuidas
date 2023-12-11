import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class hiloCliente extends Thread{
    private Socket socket_cliente;
    public hiloCliente(Socket socket_cliente){
        this.socket_cliente = socket_cliente;
    }

    public void run(){
        try{
            while(true){
                Scanner escribir = new Scanner(System.in);
                //Crear buffer para recibir y enviar datos cliente
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket_cliente.getInputStream()));
                PrintWriter salida = new PrintWriter(socket_cliente.getOutputStream(),true);
                //Leer datos recibidos desde el cliente
                String datos_recibidos = entrada.readLine();
                System.out.println("Mensaje recibido: "+ datos_recibidos);

                if(datos_recibidos.equalsIgnoreCase("fin")){
                    System.out.println("Conversación terminada");
                    socket_cliente.close();
                    return;
                } else {
                    //Enviar SMS al cliente
                    System.out.print("Servidor: ");
                    String respuesta = escribir.nextLine();
                    salida.println(respuesta);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}