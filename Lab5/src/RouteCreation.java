import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class RouteCreation {
    public RouteCreation() throws IOException, MyException {
    }

    public static void createRoute() throws IOException, MyException {
        System.out.println("Введите данные для создания маршрута. Если вы допустите ошибку при вводе, то вам будет предложено создать маршрут заново");
        System.out.println("Вещественные числа нужно вводить, используя знак ','!");
        try {
            Scanner scanner = new Scanner(System.in);
            String nameScan = "";
            while (nameScan.equals("")) {
                System.out.println("Введите название маршрута: ");
                nameScan = scanner.nextLine();
            }
            System.out.println("Ввод ваших координат");
            float coorX = -319;
            int coorY;
            while (coorX < -318) {
                System.out.println("Введите координату X (float), значение должно быть больше -318. Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                coorX = scanner.nextFloat();
            }
            System.out.println("Введите координату Y (int). Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
            coorY = scanner.nextInt();
            System.out.println("Ввод координат начала маршрута");
            long coorFromX;
            Float coorFromY = null;
            int coorFromZ;
            System.out.println("Введите координату X (long). Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
            coorFromX = scanner.nextLong();
            while (coorFromY == null) {
                System.out.println("Введите координату Y (Float), значение не должно быть null. Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                coorFromY = scanner.nextFloat();
            }
            System.out.println("Введите координату Z (int). Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
            coorFromZ = scanner.nextInt();
            System.out.println("Ввод координат конца маршрута");
            double coorToX;
            Float coorToY = null;
            double coorToZ;
            System.out.println("Введите координату X (double). Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
            coorToX = scanner.nextDouble();
            while (coorToY == null) {
                System.out.println("Введите координату Y (Float), значение не должно быть null. Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                coorToY = scanner.nextFloat();
            }
            System.out.println("Введите координату Z (double). Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
            coorToZ = scanner.nextDouble();
            Integer dist = 0;
            while (dist < 1) {
                System.out.println("Введите дистанцию маршрута (Integer), значение поля должно быть больше 1. Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                dist = scanner.nextInt();
            }
            Route route = new Route(nameScan, new Coordinates(coorX, coorY), new LocationFrom(coorFromX, coorFromY, coorFromZ), new LocationTo(coorToX, coorToY, coorToZ), dist);
            RouteCollection.col.add(route);
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода. Заполните данные заново");
            createRoute();
        } catch (MyException e) {
            System.out.println("Неверный формат введенных данных. Заполните данные заново");
            createRoute();
        }
    }

    public static long findById() throws IOException, MyException {
        long idScanner = -1;
        int k = 0;
        System.out.println("Введите ID элемента (Long), чтобы обновить его");
        try {
            Scanner scanner2 = new Scanner(System.in);
            while ((idScanner <= 0) || (idScanner > 1000)) {
                System.out.println("ID должен быть положительным и не может превышать 1000!");
                idScanner = scanner2.nextLong();
            }
            Iterator<Route> it = RouteCollection.col.iterator();
            while (it.hasNext()) {
                Route route = it.next();
                if (route.id == idScanner) {
                    k++;
                }
            }
            //if (k == 0) {
            //System.out.println("Элемента с таким id в коллекции нет");
            //}
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода");
            //findById();
        }
        if (k == 0) return k;
        else return idScanner;
    }

    public static void updateId() throws IOException, MyException {
        long i = findById();
        if (i == 0) System.out.println("Элемента с таким ID в коллекции нет");
        else {
            Route route1 = new Route();
            System.out.println("Введите данные для обновления маршрута. Если вы допустите ошибку при вводе, то вам будет предложено создать маршрут заново");
            System.out.println("Введите название маршрута: ");
            try {
                Scanner scanner = new Scanner(System.in);
                String nameScan = scanner.nextLine();
                System.out.println("Ввод ваших координат");
                float coorX = -319;
                int coorY;
                while (coorX < -318) {
                    System.out.println("Введите координату X (float), значение должно быть больше -318. Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                    coorX = scanner.nextFloat();
                }
                System.out.println("Введите координату Y (int). Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                coorY = scanner.nextInt();
                System.out.println("Ввод координат начала маршрута");
                long coorFromX;
                Float coorFromY = null;
                int coorFromZ;
                System.out.println("Введите координату X (long). Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                coorFromX = scanner.nextLong();
                while (coorFromY == null) {
                    System.out.println("Введите координату Y (Float), значение не должно быть null. Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                    coorFromY = scanner.nextFloat();
                }
                System.out.println("Введите координату Z (int). Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                coorFromZ = scanner.nextInt();
                System.out.println("Ввод координат конца маршрута");
                double coorToX;
                Float coorToY = null;
                double coorToZ;
                System.out.println("Введите координату X (double). Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                coorToX = scanner.nextDouble();
                while (coorToY == null) {
                    System.out.println("Введите координату Y (Float), значение не должно быть null. Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                    coorToY = scanner.nextFloat();
                }
                System.out.println("Введите координату Z (double). Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                coorToZ = scanner.nextDouble();
                Integer dist = 0;
                while (dist < 1) {
                    System.out.println("Введите дистанцию маршрута (Integer), значение поля должно быть больше 1. Если после ввода вам будет предложен еще один ввод, то ваше предыдущее значение некорректно");
                    dist = scanner.nextInt();
                }

                Iterator<Route> it = RouteCollection.col.iterator();
                while (it.hasNext()) {
                    Route route = it.next();
                    if (route.id == i) {
                        route.setName(nameScan);
                        route.setCoordinates(new Coordinates(coorX, coorY));
                        route.setLocationFrom(new LocationFrom(coorFromX, coorFromY, coorFromZ));
                        route.setLocationTo(new LocationTo(coorToX, coorToY, coorToZ));
                        route.setDistance(dist);
                    }
                }
            } catch (InputMismatchException | MyException e) {
                System.out.println("Ошибка ввода");
                //updateId();
            }
            System.out.println("Значения элемента изменены");
        }
    }
}

























    /*protected String getWrittenRoute(LinkedHashSet<Route> lhsr){
        String str = "";
        Iterator<Route> iterator = lhsr.iterator();
        for(int i = 0; i < lhsr.size() ; i++){
            str += getRoute(iterator.next()) + "\n";
        }
        return str;
    }

    private String getRoute(Route route) {
        return "\"" + route.getName() + "\"," + route.getCoordinates() +
                ",\"" + route.getLocationFrom() + "\"" + route.getLocationTo() + "\""
                + route.getDistance() + "\"";
    }*/

