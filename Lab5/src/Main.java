import com.opencsv.*;

import java.io.*;

public class Main {

    //public static final String fileName = "test.csv";

    public Main() throws IOException {
    }

    static File fileeek;
    static File fileek;


    public static void main(String args[]) throws MyException, Exception, IOException {
        /*RouteCollection RC = new RouteCollection();
        RC.Col();
        String csv = "test1.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv));
        String[] record = "1, 2, Help, me, 5".split(",");
        writer.writeNext(record);
        writer.close();*/
        //RouteCreation r = new RouteCreation();
        //r.createRoute();
        //Route myroute = new Route();
        //myroute.Save();



        try {
            String fname = args[0];
            String fnamee = args[1];
            fileek = new File(fname);
            fileeek = new File(fnamee);
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Неверный ввод параметров");
        }
            try {
                Main mm = new Main();

                if (mm.getFFF().toString().equals("test.csv")) {
                    if (mm.getFFF().exists() && (mm.getFFF().isFile())) {
                        InputStreamReader isr = new InputStreamReader(new FileInputStream(mm.getFFF()));
                        BufferedReader reader = new BufferedReader(isr);
                        String line = null;
                        try {
                            line = reader.readLine();
                            while ((line != null) && (line.length() > 0)) {
                                Route r1 = new Route(0);
                                r1.ParseFromCSV1(line);
                                RouteCollection.col.add(r1);
                                line = reader.readLine();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                reader.close();
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    } else System.out.println("Файл не найден");
                } else System.out.println("Файл не найден");
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }


        ReadCommands rc = new ReadCommands();
        rc.CommandsScanner();
    }

    public File getfff() {
        return fileeek;
    }

    public File getFFF() {
        return fileek;
    }




    /*private static LinkedHashSet<Route> readRoutesFromCSV(String fileName) {
        LinkedHashSet<Route> routes = new LinkedHashSet<>();
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                Route route = createRoute(attributes);
                routes.add(route);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return routes;
    }
    private static Route createRoute(String[] metadata) {
        long id = Integer.parseInt(metadata[0]);
        String name = metadata[1];
        Integer distance = Integer.parseInt(metadata[2]);
        return new Route(id, name, distance);
    }*/








    /*public void Save() throws IOException, MyException {
        System.out.println("Введите имя нового файла (Без указания типа файла)");
        Scanner scan = new Scanner(System.in);
        String pathToFile = scan.nextLine();
        File file = new File(pathToFile + ".csv");
        System.out.println("Коллекция успешно сохранена в файл");
        try {
            FileWriter fw = new FileWriter(file, false);
            BufferedWriter writer = new BufferedWriter(fw);
            Iterator<Route> it = RouteCollection.col.iterator();
            while (it.hasNext()) {
                Route route = it.next();
                String line = route.ParseToCSV();
                writer.write(line);
                writer.newLine();
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}