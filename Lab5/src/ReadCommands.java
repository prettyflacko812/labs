import java.io.*;
import java.time.ZonedDateTime;
import java.util.*;

public class ReadCommands {

    public ReadCommands() throws IOException {
    }

    Route route = new Route();
    RouteCollection col = new RouteCollection();
    ArrayList<String> historyCom = new ArrayList<>();
    int amount = 0;
    int scriptStuck = 0;

    public void CommandsScanner() throws IOException, MyException {
        System.out.println("Введите команду. Для просмотра возможных команд введите help");
        Scanner cScan = new Scanner(System.in);
        String command = "";
        String[] lastCommand;

        while (!command.equals("exit")) {
            command = cScan.nextLine();
            command = command.trim();
            lastCommand = command.trim().split(" ", 2);
            switch (lastCommand[0]) {
                case "help":
                    System.out.println("help : вывести справку по доступным командам\n" +
                            "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                            "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                            "add {element} : добавить новый элемент в коллекцию\n" +
                            "updateId {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                            "removeById id : удалить элемент из коллекции по его id\n" +
                            "clear : очистить коллекцию\n" +
                            "save : сохранить коллекцию в файл\n" +
                            "executeScript file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                            "exit : завершить программу (без сохранения в файл)\n" +
                            "addIfMax {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                            "removeLower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                            "history : вывести последние команды (без их аргументов)\n" +
                            "maxByTo : вывести название маршрута, значение координаты X поля to которого является максимальным\n" +
                            "filterStartsWithName name : вывести элементы, значение поля name которых начинается с заданной подстроки\n" +
                            "filterLessThanDistance distance : вывести элементы, значение поля distance которых меньше заданного\n");
                    historyCom.add(command);
                    amount++;
                    break;
                case "info":
                    col.Info();
                    historyCom.add(command);
                    amount++;
                    break;
                case "show":
                    System.out.println(RouteCollection.col);
                    historyCom.add(command);
                    amount++;
                    break;
                case "add":
                    int ch = RouteCollection.col.size();
                    RouteCreation.createRoute();
                    if (RouteCollection.col.size() != ch)
                        System.out.println("Новый маршрут добавлен в коллекцию");
                    historyCom.add(command);
                    amount++;
                    break;
                case "updateId":
                    RouteCreation.updateId();
                    historyCom.add(command);
                    amount++;
                    break;
                case "removeById":
                    route.removeById();
                    historyCom.add(command);
                    amount++;
                    break;
                case "clear":
                    RouteCollection.col.clear();
                    System.out.println("Все элементы коллекции удалены");
                    historyCom.add(command);
                    amount++;
                    break;
                case "save":
                    try {
                        route.Save();
                        historyCom.add(command);
                        amount++;
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case "addIfMax":
                    route.addIfMax();
                    historyCom.add(command);
                    amount++;
                    break;
                case "removeLower":
                    route.removeLower();
                    historyCom.add(command);
                    amount++;
                    break;
                case "maxByTo":
                    route.maxByTo();
                    historyCom.add(command);
                    amount++;
                    break;
                case "filterStartsWithName":
                    route.filterStartsWithName();
                    historyCom.add(command);
                    amount++;
                    break;
                case "filterLessThanDistance":
                    route.filterLessThanDistance();
                    historyCom.add(command);
                    amount++;
                    break;
                case "history":
                    System.out.println("Последние команды: ");
                    history();
                    amount++;
                    historyCom.add(command);
                    break;
                case "executeScript":
                    scriptStuck++;
                    executeScript();
                    historyCom.add(command);
                    amount++;
                    break;
                //default: System.out.println("Такой команды не существует");
            }
        }
    }


    public void executeScript() {
        try {
            Main m = new Main();
            if (!(m.getfff().toString().equals("script.txt")))
                System.out.println("Файл не найден");
            else {
                String command = "";
                String[] lastCommand;
                try {
                    m.getfff();
                    FileReader fr = new FileReader(m.getfff());
                    Scanner scrScan = new Scanner(fr);
                    while ((scrScan.hasNextLine()) && (scriptStuck < 5)) {
                        command = scrScan.nextLine();
                        lastCommand = command.trim().split(" ", 2);
                        switch (lastCommand[0]) {
                            case "help":
                                System.out.println("help : вывести справку по доступным командам\n" +
                                        "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                                        "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                                        "add {element} : добавить новый элемент в коллекцию\n" +
                                        "updateId {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                                        "removeById id : удалить элемент из коллекции по его id\n" +
                                        "clear : очистить коллекцию\n" +
                                        "save : сохранить коллекцию в файл\n" +
                                        "executeScript file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                                        "exit : завершить программу (без сохранения в файл)\n" +
                                        "addIfMax {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                                        "removeLower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                                        "history : вывести последние команды (без их аргументов)\n" +
                                        "maxByTo : вывести название маршрута, значение координаты X поля to которого является максимальным\n" +
                                        "filterStartsWithName name : вывести элементы, значение поля name которых начинается с заданной подстроки\n" +
                                        "filterLessThanDistance distance : вывести элементы, значение поля distance которых меньше заданного\n");
                                historyCom.add(command);
                                amount++;
                                break;
                            case "info":
                                col.Info();
                                historyCom.add(command);
                                amount++;
                                break;
                            case "show":
                                System.out.println(RouteCollection.col);
                                historyCom.add(command);
                                amount++;
                                break;
                            case "add":
                                try {
                                int check = RouteCollection.col.size();
                                Route route1 = new Route();
                                route1.setId(route.randomId());
                                route1.setName(scrScan.nextLine());
                                float coorX = Float.parseFloat(scrScan.nextLine());
                                int coorY = Integer.parseInt(scrScan.nextLine());
                                route1.setCoordinates(new Coordinates(coorX, coorY));
                                route1.setCreationDate(ZonedDateTime.now());
                                long fromX = Long.parseLong(scrScan.nextLine());
                                Float fromY = Float.parseFloat(scrScan.nextLine());
                                int fromZ = Integer.parseInt(scrScan.nextLine());
                                route1.setLocationFrom(new LocationFrom(fromX, fromY, fromZ));
                                double toX = Double.parseDouble(scrScan.nextLine());
                                Float toY = Float.parseFloat(scrScan.nextLine());
                                double toZ = Double.parseDouble(scrScan.nextLine());
                                route1.setLocationTo(new LocationTo(toX, toY, toZ));
                                Integer dist = Integer.parseInt(scrScan.nextLine());
                                route1.setDistance(dist);
                                RouteCollection.col.add(route1);
                                if ((RouteCollection.col.size() != check) && (!getLastScr(RouteCollection.col).getName().equals("")))
                                    System.out.println("Новый маршрут добавлен в коллекцию");
                                else {
                                    System.out.println("Ошибка ввода");
                                    RouteCollection.col.remove(getLastScr(RouteCollection.col));
                                }
                                historyCom.add(command);
                                amount++;
                            } catch (MyException | NoSuchElementException | NullPointerException | NumberFormatException e) {
                                System.out.println("Неверный формат введенных данных");
                            }
                                break;
                            case "updateId":
                                try {
                                    long saveId = -1;
                                    java.time.ZonedDateTime dd = null;
                                    long idScanner = -1;
                                    int k = 0;
                                    Long idi = Long.parseLong(scrScan.nextLine());
                                    idScanner = idi;
                                    Iterator<Route> it = RouteCollection.col.iterator();
                                    while (it.hasNext()) {
                                        Route rr = it.next();
                                        if (rr.id == idScanner) {
                                            dd = rr.creationDate;
                                            saveId = rr.id;
                                            it.remove();
                                            k++;
                                        }
                                    }
                                    if (k == 0) {
                                        System.out.println("Элемента c тким ID нет в коллекции");
                                    }
                                    else {
                                        int check = RouteCollection.col.size();
                                        Route route2 = new Route();
                                        route2.setId(saveId);
                                        route2.setName(scrScan.nextLine());
                                        float coorX = Float.parseFloat(scrScan.nextLine());
                                        int coorY = Integer.parseInt(scrScan.nextLine());
                                        route2.setCoordinates(new Coordinates(coorX, coorY));
                                        route2.setCreationDate(dd);
                                        long fromX = Long.parseLong(scrScan.nextLine());
                                        Float fromY = Float.parseFloat(scrScan.nextLine());
                                        int fromZ = Integer.parseInt(scrScan.nextLine());
                                        route2.setLocationFrom(new LocationFrom(fromX, fromY, fromZ));
                                        double toX = Double.parseDouble(scrScan.nextLine());
                                        Float toY = Float.parseFloat(scrScan.nextLine());
                                        double toZ = Double.parseDouble(scrScan.nextLine());
                                        route2.setLocationTo(new LocationTo(toX, toY, toZ));
                                        Integer dist = Integer.parseInt(scrScan.nextLine());
                                        route2.setDistance(dist);
                                        RouteCollection.col.add(route2);
                                        if ((RouteCollection.col.size() != check) && (!getLastScr(RouteCollection.col).getName().equals("")))
                                            System.out.println("Маршрут обновлен");
                                        else {
                                            System.out.println("Ошибка ввода");
                                            RouteCollection.col.remove(getLastScr(RouteCollection.col));
                                        }
                                        historyCom.add(command);
                                        amount++;
                                    }
                                } catch (MyException | NoSuchElementException | NullPointerException | NumberFormatException e) {
                                    System.out.println("Неверный формат введенных данных");
                                }
                                historyCom.add(command);
                                amount++;
                                break;
                            case "clear":
                                RouteCollection.col.clear();
                                System.out.println("Все элементы коллекции удалены");
                                historyCom.add(command);
                                amount++;
                                break;
                            case "save":
                                try {
                                    route.Save();
                                    historyCom.add(command);
                                    amount++;
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "history":
                                System.out.println("Последние команды: ");
                                history();
                                amount++;
                                historyCom.add(command);
                                break;
                            case "maxByTo":
                                route.maxByTo();
                                historyCom.add(command);
                                amount++;
                                break;
                            case "removeById":
                                removeByIdScr(scrScan.nextLine());
                                historyCom.add(command);
                                amount++;
                                break;
                            case "removeLower":
                                removeLowerScr(scrScan.nextLine());
                                historyCom.add(command);
                                amount++;
                                break;
                            case "filterStartsWithName":
                                filterStartsWithNameScr(scrScan.nextLine());
                                historyCom.add(command);
                                amount++;
                                break;
                            case "filterLessThanDistance":
                                filterLessThanDistanceScr(scrScan.nextLine());
                                historyCom.add(command);
                                amount++;
                                break;
                            case "addIfMax":
                                long maxId = 0;
                                long newmaxId = 0;
                                int k = 0;
                                ArrayList<Long> count1 = new ArrayList<>();
                                ArrayList<Long> count2 = new ArrayList<>();
                                try {
                                    Iterator<Route> it = RouteCollection.col.iterator();
                                    while (it.hasNext()) {
                                        Route route = it.next();
                                        k++;
                                        count1.add(route.id);
                                    }
                                    if (k > 0) {
                                        maxId = Collections.max(count1);
                                        int check = RouteCollection.col.size();
                                        Route route2 = new Route();
                                        route2.setId(route.randomId());
                                        route2.setName(scrScan.nextLine());
                                        float coorX = Float.parseFloat(scrScan.nextLine());
                                        int coorY = Integer.parseInt(scrScan.nextLine());
                                        route2.setCoordinates(new Coordinates(coorX, coorY));
                                        route2.setCreationDate(ZonedDateTime.now());
                                        long fromX = Long.parseLong(scrScan.nextLine());
                                        Float fromY = Float.parseFloat(scrScan.nextLine());
                                        int fromZ = Integer.parseInt(scrScan.nextLine());
                                        route2.setLocationFrom(new LocationFrom(fromX, fromY, fromZ));
                                        double toX = Double.parseDouble(scrScan.nextLine());
                                        Float toY = Float.parseFloat(scrScan.nextLine());
                                        double toZ = Double.parseDouble(scrScan.nextLine());
                                        route2.setLocationTo(new LocationTo(toX, toY, toZ));
                                        Integer dist = Integer.parseInt(scrScan.nextLine());
                                        route2.setDistance(dist);
                                        RouteCollection.col.add(route2);
                                        if ((RouteCollection.col.size() != check) && (!getLastScr(RouteCollection.col).getName().equals(""))) {
                                            Iterator<Route> itt = RouteCollection.col.iterator();
                                            while (itt.hasNext()) {
                                                Route routt = itt.next();
                                                count2.add(routt.id);
                                            }
                                            newmaxId = Collections.max(count2);
                                            if (maxId == newmaxId) {
                                                System.out.println("Элемент не добавлен в коллекцию, так как его ID не привышает максимальный");
                                                RouteCollection.col.remove(getLastScr(RouteCollection.col));
                                            } else System.out.println("Новый элемент добавлен в коллекцию");
                                        } else {
                                            System.out.println("Ошибка ввода");
                                            RouteCollection.col.remove(getLastScr(RouteCollection.col));
                                        }
                                    } if (k == 0) {
                                        int check = RouteCollection.col.size();
                                        Route route2 = new Route();
                                        route2.setId(route.randomId());
                                        route2.setName(scrScan.nextLine());
                                        float coorX = Float.parseFloat(scrScan.nextLine());
                                        int coorY = Integer.parseInt(scrScan.nextLine());
                                        route2.setCoordinates(new Coordinates(coorX, coorY));
                                        route2.setCreationDate(ZonedDateTime.now());
                                        long fromX = Long.parseLong(scrScan.nextLine());
                                        Float fromY = Float.parseFloat(scrScan.nextLine());
                                        int fromZ = Integer.parseInt(scrScan.nextLine());
                                        route2.setLocationFrom(new LocationFrom(fromX, fromY, fromZ));
                                        double toX = Double.parseDouble(scrScan.nextLine());
                                        Float toY = Float.parseFloat(scrScan.nextLine());
                                        double toZ = Double.parseDouble(scrScan.nextLine());
                                        route2.setLocationTo(new LocationTo(toX, toY, toZ));
                                        Integer dist = Integer.parseInt(scrScan.nextLine());
                                        route2.setDistance(dist);
                                        RouteCollection.col.add(route2);
                                        if ((RouteCollection.col.size() != check) && (!getLastScr(RouteCollection.col).getName().equals(""))) {
                                            System.out.println("Новый элемент добавлен в коллекцию");
                                        } else {
                                            System.out.println("Ошибка ввода");
                                            RouteCollection.col.remove(getLastScr(RouteCollection.col));
                                        }
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Ошибка ввода");
                                } catch (IOException  e) {
                                    e.printStackTrace();
                                } catch (ConcurrentModificationException e) {
                                    System.out.println();
                                } catch (MyException | NoSuchElementException | NullPointerException | NumberFormatException | StringIndexOutOfBoundsException e) {
                                    System.out.println("Неверный формат введенных данных");
                                }
                                historyCom.add(command);
                                amount++;
                                break;
                            case "executeScript":
                                scriptStuck++;
                                executeScript();
                                historyCom.add(command);
                                amount++;
                                break;
                            case "exit":
                                System.exit(0);
                            default: System.out.println("Такой команды не существует: " + command);
                        }
                    }
                    fr.close();
                } catch (FileNotFoundException e) {
                    System.out.println("Файл не найден");
                }
            }
        } catch (IOException | MyException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Файл не найден");
        }
    }


    public <E> E getLastScr(Collection<E> c) {
        E last = null;
        for(E e : c) last = e;
        return last;
    }
    /*public String fileName = "";
    public Scanner scanner = null;

    public String GetFileName() {
        System.out.print("Введите название файла со скриптом (вместе с расширением):\n");
        fileName = Input();
        while (fileName.length() == 0) {
            System.out.println("Название не может быть пустым. Повторите ввод:\n");
            fileName = Input();
        }
        return fileName;
    }

    public String Input() {
        scanner = new Scanner(System.in);
        fileName = scanner.nextLine();
        return fileName;
    }*/

    public void history() {
        if (amount > 0)
            System.out.println(historyCom);
    }

    public void removeByIdScr(String str) {
        try {
            long idScan = -1;
            int k = 0;
            Long id = Long.parseLong(str);
            idScan = id;
            Iterator<Route> it = RouteCollection.col.iterator();
            try {
                while (it.hasNext()) {
                    Route route = it.next();
                    if (route.id == idScan) {
                        RouteCollection.col.remove(route);
                        System.out.println("Элемент коллекции удален");
                        k++;
                    }
                }
            } catch (ConcurrentModificationException e) {
                System.out.println();
            }
            if (k == 0) {
                System.out.println("Элемента с таким ID в коллекции нет");
            }
        } catch (NumberFormatException e){
            System.out.println("Ошибка, вы должны были указать ID элемента, который вы хотлеи удалить");
        }
    }

    public void removeLowerScr(String str) {
        try {
            long idScan = -1;
            int k = 0;
            int check = RouteCollection.col.size();
            int compCheck = RouteCollection.col.size();
            long idd = 0;
            Long id = Long.parseLong(str);
            idScan = id;
            ArrayList<Long> removed = new ArrayList<>();
            Iterator<Route> it = RouteCollection.col.iterator();
            try {
                while (it.hasNext()) {
                    Route route = it.next();
                    if (route.id == idScan) {
                        k++;
                        idd = route.id;
                    }
                }
                if (k > 0) {
                    Iterator<Route> itt = RouteCollection.col.iterator();
                    while (itt.hasNext()) {
                        Route routee = itt.next();
                        if (routee.id < idScan) {
                            itt.remove();
                            check++;
                            removed.add(routee.id);
                        }
                    }
                    if (check != compCheck)
                        System.out.println("Из коллекции удалены элементы с ID: " + removed);
                    else
                        System.out.println("В коллекции нет элемента, у которого ID меньше " + idd);
                } else System.out.println("В коллекции нет элемента с таким ID");
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода");
            } catch (ConcurrentModificationException e) {
                System.out.println();
            }
        } catch (NumberFormatException e){
            System.out.println("Ошибка, вы должны были указать ID элемента");
        }
    }

    public void filterStartsWithNameScr(String str){
        try {
            String nameScan = "";
            int k = 0;
            nameScan = str;
            Iterator<Route> it = RouteCollection.col.iterator();
            try {
                while (it.hasNext()) {
                    Route route = it.next();
                    String s = route.getName();
                    String n = s.substring(0, 3);
                    if (n.equals(nameScan)) {
                        System.out.println(route);
                        k++;
                    }
                }
                if (k == 0)
                    System.out.println("В коллекции нет маршрута, название которого бы начиналось с данной строки, либо ваша строка не содержит ровно 3 символа");
            } catch (InputMismatchException | StringIndexOutOfBoundsException e) {
                System.out.println("Ошибка ввода");
            } catch (ConcurrentModificationException e) {
                System.out.println();
            }
        } catch (NumberFormatException e){
            System.out.println("Ошибка, вы должны были указать первые 3 символа названия элемента");
        }
    }

    public void filterLessThanDistanceScr(String str) {
        try {
            long idScan = -1;
            int k = 0;
            Integer dist = Integer.parseInt(str);
            idScan = dist;
            Iterator<Route> it = RouteCollection.col.iterator();
            try {
                while (it.hasNext()) {
                    Route route = it.next();
                    if (route.getDistance() == idScan) {
                        k++;
                    }
                }
                if (k > 0) {
                    Iterator<Route> itt = RouteCollection.col.iterator();
                    while (itt.hasNext()) {
                        Route routee = itt.next();
                        if (routee.getDistance() < idScan) {
                            System.out.println(routee);
                        }
                    }
                } else System.out.println("В коллекции нет элемента с такой дистанцией");
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода");
            } catch (ConcurrentModificationException e) {
                System.out.println();
            }
        } catch (NumberFormatException e){
            System.out.println("Ошибка, вы должны были указать дистанцию");
        }
    }























    /*public void addScr(String str) {
        try {
            int check = RouteCollection.col.size();
            Route route1 = new Route();
            route1.setId(route.randomId());
            route1.setName(str);
            float coorX = Float.parseFloat(str);
            int coorY = Integer.parseInt(str);
            route1.setCoordinates(new Coordinates(coorX, coorY));
            route1.setCreationDate(ZonedDateTime.now());
            long fromX = Long.parseLong(str);
            Float fromY = Float.parseFloat(str);
            int fromZ = Integer.parseInt(str);
            route1.setLocationFrom(new LocationFrom(fromX, fromY, fromZ));
            double toX = Double.parseDouble(str);
            Float toY = Float.parseFloat(str);
            double toZ = Double.parseDouble(str);
            route1.setLocationTo(new LocationTo(toX, toY, toZ));
            Integer dist = Integer.parseInt(str);
            route1.setDistance(dist);
            RouteCollection.col.add(route1);
            if ((RouteCollection.col.size() != check) && (!getLastScr(RouteCollection.col).getName().equals("")))
                System.out.println("Новый маршрут добавлен в коллекцию");
            else {
                System.out.println("Ошибка ввода");
                RouteCollection.col.remove(getLastScr(RouteCollection.col));
            }
        } catch (MyException | NoSuchElementException | NullPointerException | NumberFormatException | IOException e) {
            System.out.println("Неверный формат введенных данных");
        }
    }*/
}
