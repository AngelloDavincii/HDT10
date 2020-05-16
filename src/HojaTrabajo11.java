
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Angel David Cuellar 18382
 * Hoja de trabajo 10
 */

public class HojaTrabajo11 {
    
    public static void main(String[] args) throws IOException {



        File archivo = new File ("guategrafo.txt");
        ArrayList<Ciudades> departamento = new ArrayList<>();
        ArrayList<String> ciudades = new ArrayList<>();
        Ciudades city = new Ciudades();


        FileReader fr = new FileReader(archivo);
        BufferedReader br1 = new BufferedReader(fr);
        String linea = "";
        Scanner scanner = new Scanner(fr);

        String sideA;
        String sideB;
        int distancia;
        int cont = 0;

        while (scanner.hasNextLine()) {
            linea = scanner.nextLine();

            sideA = linea.substring(0, linea.indexOf(" "));
            linea = linea.substring(linea.indexOf(" ") + 1, linea.length());

            sideB = linea.substring(0, linea.indexOf(" "));
            linea = linea.substring(linea.indexOf(" ") + 1, linea.length());

            distancia = Integer.parseInt(linea.substring(0, linea.length()));

            cont++;

            departamento.add(new Ciudades(sideA, sideB, distancia));

            fr.close();
            br1.close();
        }



        Scanner sc = new Scanner(System.in);
        int op = 0;
        int seleccion = 0;

        matrix af = new matrix();
        long matriz[][];

        OUTER:
        System.out.println("");
        while (seleccion != 5) {
            System.out.println("1. Mostrar ruta mas corta entre departamentos.");
            System.out.println("2. Mostrar el departamento el centro del grafo");
            System.out.println("3. Modificar grafo.");
            System.out.println("4. Mostrar matriz de adyacencia.");
            System.out.println("5. Salir");
            seleccion = sc.nextInt();

            switch (seleccion) {
                case 1:
                    System.out.println("\t1. Ingrese el departamento de origen");
                    String o = sc.nextLine();
                    o = sc.nextLine();
                    System.out.println("\t2. Ingrese la ciudad de destino.");
                    String d = sc.nextLine();

                    ciudades.clear();
                    ciudades = city.crearLista(departamento);
                    matriz = af.crearMatriz(ciudades, departamento);

                    if(af.verificarExistencia(o, d, ciudades)){

                        System.out.println(af.algoritmoFloyd(matriz, ciudades, o, d));
                    }else{
                        System.out.println("Dichas ciudades no estan contenidas en GuateGrafo.");
                    }
                    break;
                case 2:
                    ciudades.clear();
                    ciudades = city.crearLista(departamento);
                    matriz = af.crearMatriz(ciudades, departamento);
                    af.centerGraph(matriz);

                    System.out.println("El departamento en el centro es es: " + ciudades.get(af.centerGraph(matriz)));

                    break;
                case 3:
                    String seleccion2 = "";
                    System.out.println("\ta. Hay interrupción de tráfico entre un par de departamentos");
                    System.out.println("\tb. Establecer nueva conexion entre departamentos");
                    seleccion2 = sc.next();

                    if("a".equals(seleccion2.toLowerCase())){
                        System.out.println("Ingrese el departamento de inicio");
                        String ori = sc.next();
                        System.out.println("Ingrese el departaneto de llegada");
                        String dest = sc.next();

                        boolean hubo = false;
                        for(Ciudades c: departamento){
                            if((c.getOrigen().equals(ori)) && (c.getDestino().equals(dest))){
                                departamento.remove(c);
                                hubo = true;
                                System.out.println("Se ha establecido la interrupcion correctamente.");
                                break;
                            }
                        }
                        if(hubo == false){
                            System.out.println("No se pudo encontrar dichas ciudades.");
                        }
                    }else if("b".equals(seleccion2.toLowerCase())){
                        System.out.println("Ingrese el departamento de inicio");
                        String ori = sc.next();
                        System.out.println("Ingrese el departamento de llegada");
                        String dest = sc.next();
                        System.out.println("Ingrese la distancia entre " + ori + " y " + dest);
                        int dist = sc.nextInt();

                        departamento.add(new Ciudades(ori, dest, dist));
                        System.out.println("Se agrego las ciudades y su distancia correctamente.");
                    }

                    break;
                case 4:
                    ciudades.clear();
                    ciudades = city.crearLista(departamento);
                    matriz = af.crearMatriz(ciudades, departamento);
                    System.out.println("La matriz de adyacencia es:");
                    for(String s: ciudades){
                        System.out.print(s + " ");
                    }
                    System.out.println("");
                    System.out.println(af.verMatriz(matriz));
                    break;

            }


            System.out.println("--------------------------------------------------------");
        }
    }
}