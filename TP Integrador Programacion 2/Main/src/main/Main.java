
package main;

import java.util.Scanner;
public class Main {

    
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
        boolean on = true;
       while (on){
           System.out.println("Bienvenido a Tienda Ficticia");
           System.out.println("Elija una opcion:");
           System.out.println("1- Soy un empleado");
           System.out.println("2- Soy un cliente");
           System.out.println("3 - Apagar");
           String option = scanner.nextLine();
           boolean on2 = true;
           switch (option) {
               case "1" -> {
                   while(on2){
                        System.out.println("Bienvenido empleado, elija una opcion:");
                        System.out.println("1- Crear usuario");
                        System.out.println("2- Agregar producto");
                        System.out.println("3- Eliminar producto");
                        System.out.println("4- Ver inventario");
                        System.out.println("5- Modificar producto");
                        System.out.println("6- Agregar stock de un producto");
                        System.out.println("7- Quitar stock de un producto");
                        System.out.println("8- Salir");
                        option = scanner.nextLine();
                        switch(option){
                            case "1" ->{
                                System.out.println("Ingrese el nombre");
                                String name = scanner.nextLine().toUpperCase();
                                System.out.println("Ingrese el sexo (Masculino/Femenino)");
                                String gender = scanner.nextLine().toUpperCase();
                                System.out.println("Ingrese el email");
                                String email = scanner.nextLine().toUpperCase();
                                System.out.println("Ingrese el documento");
                                int dni = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("Ingrese el ID del empleado");
                                int idEmployee = scanner.nextInt();
                                scanner.nextLine();
                                Employee.createEmployee(name, gender, email, dni, idEmployee);
                            }
                            case "2" -> {
                                System.out.println("Ingresar el nombre del producto");
                                String name = scanner.nextLine().toUpperCase();
                                System.out.println("Ingresar la categoría del producto (frutas, electrodomesticos, etc)");
                                String type = scanner.nextLine().toUpperCase();
                                System.out.println("Ingrese el precio del producto");
                                float price = scanner.nextFloat();
                                scanner.nextLine();
                                System.out.println("Ingrese el stock del producto");
                                int stock = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("Ingrese el ID del producto");
                                int idProduct = scanner.nextInt();
                                scanner.nextLine();
                                Product.createProduct(name, type, price, stock, idProduct);
                            }
                            case "3" ->{
                                System.out.println("Ingresar el ID del producto que desea eliminar");
                                int id = scanner.nextInt();
                                scanner.nextLine();
                                Product.deleteProduct(id);
                            }
                            case "4" ->{
                                Inventory.showInventory();
                            }
                            case "5" ->{
                                System.out.println("Ingrese el ID del producto que quiere modificar");
                                int id  = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("Ingrese el nuevo nombre");
                                String name = scanner.nextLine().toUpperCase();
                                System.out.println("Ingrese la nueva categoria (Carnes, legumbres, etc)");
                                String type = scanner.nextLine().toUpperCase();
                                System.out.println("Ingrese el nuevo precio");
                                float price = scanner.nextFloat();
                                scanner.nextLine();
                                System.out.println("Ingrese el nuevo stock");
                                int stock = scanner.nextInt();
                                scanner.nextLine();
                                Product.updateProduct(id, name, type, price, stock);
                            }
                            case "6" ->{
                                System.out.println("Ingrese el ID del producto");
                                int id = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("Ingrese cuanto stock desea agregar");
                                int increment = scanner.nextInt();
                                scanner.nextLine();
                                Product.addStock(id, increment);
                            }
                            case "7" ->{
                                System.out.println("Ingrese el ID del producto");
                                int id = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("Ingrese cuanto stock desea quitar");
                                int decrement = scanner.nextInt();
                                scanner.nextLine();
                                Product.subStock(id, decrement);
                            }
                            default -> on2 = false;
                        }
                   }
               }
               case "2" -> {
                   while(on2){
                        System.out.println("Bienvenido cliente, elija una opcion:");
                        System.out.println("1- Crear usuario");
                        System.out.println("2- Ver productos");
                        System.out.println("3- Buscar productos");
                        System.out.println("4- Añadir producto al carrito");
                        System.out.println("5- Comprar lo del carrito");
                        System.out.println("6- Salir");
                        option = scanner.nextLine();
                        switch(option){
                            case "1" ->{
                                System.out.println("Ingrese su nombre");
                                String name = scanner.nextLine().toUpperCase();
                                System.out.println("Ingrese su sexo (Masculino/Femenino)");
                                String gender = scanner.nextLine().toUpperCase();
                                System.out.println("Ingrese su email");
                                String email = scanner.nextLine().toUpperCase();
                                System.out.println("Ingrese su documento");
                                int dni = scanner.nextInt();
                                scanner.nextLine();
                                Customer.createCustomer(name, gender, email, dni);
                            }
                            case "2" ->{
                                Inventory.showInventory();
                            }
                            case "3" -> {
                                System.out.println("Que producto quiere buscar?");
                                String name = scanner.nextLine().toUpperCase();
                                Inventory.showSomeProducts(name);
                            }
                            case "4" -> {
                                System.out.println("Mi carrito: ");
                                
                            }
                            case "5" ->{
                                
                            }
                            default -> on2 = false;
                        }
                    }
               }
               default -> on = false;
           
       }
    }

}
}
