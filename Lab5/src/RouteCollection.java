import java.time.ZonedDateTime;
import java.util.*;

public class RouteCollection {
    public static ZonedDateTime dateTime = ZonedDateTime.now();
    public static LinkedHashSet <Route> col = new LinkedHashSet<Route>();
    public static LinkedHashSet <Route> Col() {
        /*Route route1 = new Route("routeA", new Coordinates(20, 10), new LocationFrom(1, 2f, 3), new LocationTo(50, 60f, 70), 100);
        Route route2 = new Route("routeB", new Coordinates(66, 55), new LocationFrom(9, 8f, 7), new LocationTo(66, 55f, 44), 50);
        col.add(route1);
        col.add(route2);*/
        return col;
    }
    public void Info() {
        System.out.println("Тип коллекции: LinkedHashSet" + "\n" +"Размерность коллекции равна: " +
                RouteCollection.col.size() + "\n" + "Дата инициализации: " + RouteCollection.dateTime);
    }
}
