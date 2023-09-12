package pattern.factory.factory_method;

public class FileLoggerFactory implements LoggerFactory{
    @Override
    public Logger createLogger() {
        Logger logger = new FileLogger();
        return logger;
    }
}
