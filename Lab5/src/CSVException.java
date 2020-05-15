public class CSVException extends  RuntimeException{
    protected CSVException(String message){
        super("При парсинге возникла ошибка: " + message);
    }
}
