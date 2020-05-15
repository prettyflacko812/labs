public class LocationTo {
    private double x;
    private Float y; //Поле не может быть null
    private double z;

    public LocationTo (double x, Float y, double z) throws MyException {
        this.x = x;
        this.y = y; if (y == null) throw new MyException("Поле не может быть null");
        this.z = z;
    }
    public double getX(){
        return x;
    }
    public Float getY(){
        return y;
    }
    public double getZ() { return z; }
    public void setX(double x){
        this.x=x;
    }
    public void setY(Float y) { this.y=y; }
    public void setZ(double y) { this.z=z; }

    public String toString() {
        return (x + ","+ y + ","+ z);
    }

    LocationTo (){
    }

    public void ParseFromCSV(Check с) {
        try {
            if (Parse.IsValue(с)) {
                x = Double.parseDouble(с.val);
                if (Parse.IsValue(с)) {
                    y = Float.parseFloat(с.val);
                    if (Parse.IsValue(с)) {
                        z = Double.parseDouble(с.val);
                    }
                }
            }
        } catch(RuntimeException e){
            System.out.println("Ошибка, неверный формат введенных данных, коллеция заполнена некорректно");
        }
    }
}
