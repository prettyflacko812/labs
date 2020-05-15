import com.opencsv.*;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.*;

public class Route {
    public long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    public java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private LocationFrom from; //Поле может быть null
    private LocationTo to; //Поле может быть null
    private Integer distance; //Поле не может быть null, Значение поля должно быть больше 1

    Route(String name, Coordinates coordinates, LocationFrom from, LocationTo to, Integer distance) throws MyException {
        this.id = randomId();
        this.name = name;
        if ((name.trim().length() == 0) || (name == null))
            throw new MyException("Ошибка, строка названия пути не может быть пустой");
        this.coordinates = coordinates;
        if (coordinates == null) throw new MyException("Поле не может быть null");
        this.from = from;
        this.to = to;
        this.distance = distance;
        if (distance == null || distance < 1) throw new MyException("Поле не может быть null");
        this.creationDate = ZonedDateTime.now();
    }

    Route(long id_) throws MyException {
        id = id_;
        name = "";
        coordinates = new Coordinates();
        ZonedDateTime zdt = ZonedDateTime.now();
        creationDate = zdt;
        from = new LocationFrom();
        to = new LocationTo();
        distance = 0;
    }

    /*Route(long id, String name, Integer distance) {
        this.id = id; this.name = name; coordinates = new Coordinates(); this.from = from;
        this.to = to;this.creationDate = ZonedDateTime.now(); this.distance = distance;
    }*/

    public Route() throws IOException {
    }


    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public java.time.ZonedDateTime getCreationDate() {
        return creationDate;
    }
    public LocationFrom getLocationFrom() {
        return from;
    }
    public LocationTo getLocationTo() {
        return to;
    }
    public Integer getDistance() {
        return distance;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setName (String name){
        this.name = name;
    }
    public void setCoordinates (Coordinates coordinates){
        this.coordinates = coordinates;
    }
    public void setLocationFrom (LocationFrom from){
        this.from = from;
    }
    public void setLocationTo (LocationTo to){
        this.to = to;
    }
    public void setDistance (Integer distance){
        this.distance = distance;
    }
    public void setCreationDate (java.time.ZonedDateTime creationDate){
        this.creationDate = creationDate;
    }

    /*public void setCreationDate(java.time.ZonedDateTime creationDate) throws  IOException{
        if(creationDate!=null){
            this.creationDate = creationDate;
        }
        else{
            throw new IOException("incorrect data in file");
        }
    }*/

    long randomId() {
        long id = (long) (Math.random() * (1000+1));
        return id;
    }

    public String toString() {
        return ("ID: " + id + "\n" + "Название маршрута: " + name + "\n" + "Ваши координаты: " + coordinates
                + "\n" + "Дата инициализации: " + creationDate + "\n"+ "Координаты начала маршрута: " + from + "\n"
                + "Координаты конца маршрута: " + to + "\n" + "Длина маршрута: " + distance + "\n" );
    }

    public String makeString() {
        String s = id + "," + name + "," + coordinates.toString() + ","
                + creationDate + "," + from.toString() + "," + to.toString() + "," + distance;
        return s;
    }

    /*public void ParseFromCSV(String s){
        try{
            Check с = new Check(s);
            if (Parse.IsValue(с)){
                id = Long.parseLong(с.val);
                if (Parse.IsValue(с)){
                    name = с.val;
                    coordinates.ParseFromCSV(с);
                    if (Parse.IsValue(с)&&Parse.isDateTime){
                        creationDate = Parse.zdt;
                    }
                    if (Parse.IsValue(с)){
                        from.ParseFromCSV(с);
                        if (Parse.IsValue(с)){
                            to.ParseFromCSV(с);
                            if (Parse.IsValue(с)){
                                distance = Integer.parseInt(с.val);
                            }
                        }
                    }
                }
            }
        } catch(RuntimeException e){
            System.out.println("Ошибка, неверный формат введенных данных");
        }
    }*/

    public void ParseFromCSV1(String s){
        try {
            Check с = new Check(s);
            if (Parse.IsValue(с)) {
                id = Long.parseLong(с.val);
                if (Parse.IsValue(с)) {
                    name = с.val;
                    coordinates.ParseFromCSV(с);
                }
                if (Parse.IsValue(с) && Parse.isDateTime) {
                    creationDate = Parse.zdt;
                }
                from.ParseFromCSV(с);
                to.ParseFromCSV(с);
                if (Parse.IsValue(с)) {
                    distance = Integer.parseInt(с.val);
                }
            }
        } catch(RuntimeException e){
            System.out.println("Ошибка, неверный формат введенных данных, коллеция заполнена некорректно");
        }
    }


    public void Save() throws IOException, MyException {
        //System.out.println("Введите имя нового файла (без указания типа файла)");
        //Scanner scan = new Scanner(System.in);
        //String pathToFile = scan.nextLine();
        String pathToFile = "test";
        File file = new File(pathToFile + ".csv");
        try {
            FileWriter fw = new FileWriter(file, false);
            BufferedWriter writer = new BufferedWriter(fw);
            Iterator<Route> it = RouteCollection.col.iterator();
            while (it.hasNext()) {
                Route route = it.next();
                String line = route.makeString();
                writer.write(line);
                writer.newLine();
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Коллекция успешно сохранена в файл test.csv");
    }

    public void removeById() {
        long idScan = -1;
        int k = 0;
        System.out.println("Введите id элемента (Long), который вы хотите удалить: ");
        try {
            Scanner scan = new Scanner(System.in);
            while ((idScan <= 0) || (idScan > 1000)) {
                System.out.println("ID должен быть положительным и не может превышать 1000!");
                idScan = scan.nextLong();
            }
            Iterator<Route> it = RouteCollection.col.iterator();
            while (it.hasNext()) {
                Route route = it.next();
                if (route.id == idScan) {
                    RouteCollection.col.remove(route);
                    System.out.println("Элемент коллекции удален");
                    k++;
                }
            }
            if (k == 0) {
                System.out.println("Элемента с таким ID в коллекции нет");
            }
        } catch (ConcurrentModificationException e) {
            System.out.println();
        } catch (InputMismatchException  e) {
            System.out.println("Ошибка ввода, попробуйте еще раз");
            removeById();
        }
    }

    public void removeLower() {
        long idScan = -1;
        int k = 0;
        int check = RouteCollection.col.size();
        int compCheck = RouteCollection.col.size();
        long idd = 0;
        ArrayList<Long> removed = new ArrayList<>();
        System.out.println("Введите ID элемента (Long), чтобы удалить из коллекции элементы, которые меньше заданного");
        try {
            Scanner scan = new Scanner(System.in);
            while ((idScan <= 0) || (idScan > 1000)) {
                System.out.println("ID должен быть положительным и не может превышать 1000!");
                idScan = scan.nextLong();
            }
            Iterator<Route> it = RouteCollection.col.iterator();
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
            System.out.println("Ошибка ввода, попробуйте еще раз");
            removeLower();
        }
    }

    public void addIfMax() {
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
                RouteCreation.createRoute();
                Iterator<Route> itt = RouteCollection.col.iterator();
                while (itt.hasNext()) {
                    Route routt = itt.next();
                    count2.add(routt.id);
                }
                newmaxId = Collections.max(count2);
                if (maxId == newmaxId){
                    System.out.println("Элемент не добавлен в коллекцию, так как его ID не привышает максимальный");
                    RouteCollection.col.remove(getLast(RouteCollection.col));
                } else System.out.println("Новый элемент добавлен в коллекцию");
            } else if (k == 0) {
                RouteCreation.createRoute();
                System.out.println("Новый элемент добавлен в коллекцию");
            }
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода, попробуйте еще раз");
            addIfMax();
        } catch (IOException | MyException e) {
            e.printStackTrace();
        } catch (ConcurrentModificationException e) {
            System.out.println();
        }
    }

    public <E> E getLast(Collection<E> c) {
        E last = null;
        for(E e : c) last = e;
        return last;
    }

    public void maxByTo() {
        double maxToX = 0;
        int k = 0;
        ArrayList<Double> count1 = new ArrayList<>();
        try {
            Iterator<Route> it = RouteCollection.col.iterator();
            while (it.hasNext()) {
                Route route = it.next();
                k++;
                count1.add(route.to.getX());
            }
            if (k > 0) {
                maxToX = Collections.max(count1);
                Iterator<Route> itt = RouteCollection.col.iterator();
                while (itt.hasNext()) {
                    Route routt = itt.next();
                    if (maxToX == routt.to.getX())
                        System.out.println("Название маршрута с максимальной координатой X поля to: " + routt.getName());
                }
            } else System.out.println("Коллекция пуста");
        } catch (InputMismatchException | ConcurrentModificationException e) {
            System.out.println();
        }
    }

    public void filterStartsWithName(){
        String nameScan = "" ;
        int k = 0;
        System.out.println("Введите 3 символа, чтобы увидеть все маршруты, которые с них начинаются");
        try {
            Scanner scan = new Scanner(System.in);
            while (nameScan.length()!=3) {
                System.out.println("Строка должна состоять из 3 символо!");
                nameScan = scan.nextLine();
            }
            Iterator<Route> it = RouteCollection.col.iterator();
            while (it.hasNext()) {
                Route route = it.next();
                String s = route.name;
                String n = s.substring(0, 3);
                if (n.equals(nameScan)) {
                    System.out.println(route);
                    k++;
                }
            }
            if (k == 0)
                System.out.println("В коллекции нет маршрута, название которого бы начиналось с данной строки");
        } catch (InputMismatchException e ){
            System.out.println("Ошибка ввода, попробуйте еще раз");
            filterStartsWithName();
        }
    }

    public void filterLessThanDistance() {
        long idScan = -1;
        int k = 0;
        System.out.println("Введите дистанцию маршрута (Integer), чтобы вывести элементы коллекции, значение поля distance которых меньше заданного");
        try {
            Scanner scan = new Scanner(System.in);
            while (idScan <= 1) {
                System.out.println("Дистанция должна быть больше 1!");
                idScan = scan.nextLong();
            }
            Iterator<Route> it = RouteCollection.col.iterator();
            while (it.hasNext()) {
                Route route = it.next();
                if (route.distance == idScan) {
                    k++;
                }
            }
            if (k > 0) {
                Iterator<Route> itt = RouteCollection.col.iterator();
                while (itt.hasNext()) {
                    Route routee = itt.next();
                    if (routee.distance < idScan) {
                        System.out.println(routee);
                    }
                }
            } else System.out.println("В коллекции нет элемента с такой дистанцией");
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода, попробуйте еще раз");
            filterLessThanDistance();
        }
    }





























    /*public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public void givenDataArray_whenConvertToCSV_thenOutputCreated() throws IOException {
        System.out.println("Введите имя нового файла (Без указания типа файла)");
        Scanner scan = new Scanner(System.in);
        String pathToFile = scan.nextLine();
        File file = new File(pathToFile + ".csv");
        System.out.println("Коллекция успешно сохранена в файл");
        try (PrintWriter pw = new PrintWriter(file)) {
            RouteCollection.col.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }*/

    /*public void FileSaving() throws IOException, MyException {
        CsvMapper mapper = new CsvMapper();
        System.out.println("Введите имя нового файла (Без указания типа файла)");
        Scanner scan = new Scanner(System.in);
        String pathToFile = scan.nextLine();
        File file = new File(pathToFile + ".csv");
        System.out.println("Коллекция успешно сохранена в файл");
        try {
            mapper.writeValue(file, RouteCollection.col);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /*private boolean isWork = false;
    public String textOfFile;
    private File sourceFile = null;
    private boolean IsCSV = false;
    private Date date;
    private LinkedHashSet<Route> lhs;

    protected boolean isWork() {
        return isWork;
    }

    public String readCSV(File file) {
        String txt = "";
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                txt += scanner.nextLine();
            }
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    saveAndExit();
                } catch (MyException e) {
                    e.printStackTrace();
                }
            }));
            System.out.println("Файл считан");
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла. Работа невозможна.");
            isWork = false;
        }
        try {
            FileWriter fileWrite = new FileWriter(file);
            fileWrite.write(txt);
        } catch (IOException e) {
            e.getMessage();
        }
        this.textOfFile = txt;
        return txt;
    }

    protected void BeforeSaveDelete(File file) {
        String txt = "";
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                txt += scanner.nextLine();
            }
        } catch (IOException e) {
        }
    }

    public void readFile(File impFile) {
        try {
            if (!(impFile.isFile()))
                throw new FileNotFoundException("Путь ведёт не к файлу.\nРабота невозможна.");
            if (!(impFile.exists()))
                throw new FileNotFoundException("По указанному пути файл не найден.\nДальнейшая работа невозможна.");
            if (!(impFile.canRead()))
                throw new SecurityException("Нет прав на чтение.\nДальнейшая работа невозможна.");
            CSVtoLHS(readCSV(impFile));
        } catch (SecurityException | IOException | MyException e) {
            System.out.println("Ошибка чтения файла.");
            isWork = false;
        }
    }

    public void CSVtoLHS(String infoCSV) throws IOException, MyException {
        RouteCreation rc = new RouteCreation();
        rc.createRoute();
        lhs = RouteCollection.col;
        if (lhs.size() > 0)
            IsCSV = true;
        System.out.println("Элементов было добавлено: " + lhs.size());
        date = new Date();
        isWork = true;
    }

    protected void exit() {
        isWork = false;
        System.out.println("Выход...");
    }

    protected void saveAndExit() throws MyException {
        String format = sourceFile.getName().substring(sourceFile.getName().indexOf(".") + 1);
        if (!format.equals("csv"))
            save();
        assert sourceFile != null;
        if (!format.equals("csv") | !sourceFile.canWrite()) {
            System.out.println("Файл не существует или в него нельзя записать данные или он не соответствует необходимому для записи формату.");
            String newFile = "CSVObject" + Integer.toString((int) (Math.random() * 100));
            String directory = System.getProperty("user.dir");
            String separator = System.getProperty("file.separator");
            sourceFile = new File(directory + separator + newFile + ".csv");
            if (!sourceFile.exists() | !sourceFile.isFile()) {
                try {
                    if (sourceFile.createNewFile()) {
                        System.out.println("Новый файл успешно создан.\nИмя файла:" + newFile);
                        AskSave();
                    }
                } catch (IOException e) {
                    System.out.println("Ошибка при создании файла:" + e.getMessage());
                }
            } else {
                AskSave();
            }
        } else {
            AskSave();
        }
    }

    protected void AskSave() {
        try {
            if (IsCSV)
                save();
            else {
                FileWriter fileWritercsv = new FileWriter(sourceFile);
                try (BufferedWriter bufferedWriter = new BufferedWriter(fileWritercsv)) {
                    bufferedWriter.write("");
                    System.out.println("Коллекция пуста");
                } catch (IOException e) {
                    System.out.println("Ошибка записи");
                }
            }
        } catch (IOException | MyException e) {
            System.out.println("Ошибка записи" + e.getMessage());
        }
    }

    protected void save() throws MyException {
        try {
            BeforeSaveDelete(sourceFile);
            FileWriter fileWriterCSV = new FileWriter(sourceFile);
            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriterCSV)) {
                if (lhs != null) {
                    if (IsCSV) {
                        RouteCreation csvWriter = new RouteCreation();
                        bufferedWriter.write(csvWriter.getWrittenRoute(lhs));
                        System.out.println("Коллекция сохранена в файле: " + sourceFile.getAbsolutePath());
                    } else {
                        bufferedWriter.write(textOfFile);
                        System.out.println("Сохранено изначальное значение файла");
                    }
                } else {
                    bufferedWriter.write(textOfFile);
                }
                System.out.println("Работа над данной коллекцией завершена");
            } catch (IOException e) {
                System.out.println("Ошибка записи файла-" + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи файла" + e.getMessage());
        }
    }*/
}