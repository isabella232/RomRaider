package enginuity.logger;

import enginuity.Settings;
import enginuity.logger.definition.EcuParameter;
import enginuity.logger.io.serial.SerialPortDiscoverer;
import enginuity.logger.io.serial.SerialPortDiscovererImpl;
import enginuity.logger.manager.QueryManager;
import enginuity.logger.manager.QueryManagerImpl;
import enginuity.logger.manager.TransmissionManager;
import enginuity.logger.manager.TransmissionManagerImpl;
import enginuity.logger.query.LoggerCallback;
import static enginuity.util.ParamChecker.checkNotNull;
import gnu.io.CommPortIdentifier;

import java.util.ArrayList;
import java.util.List;

public final class LoggerControllerImpl implements LoggerController {
    private final QueryManager queryManager;

    public LoggerControllerImpl(Settings settings) {
        TransmissionManager txManager = new TransmissionManagerImpl(settings);
        queryManager = new QueryManagerImpl(txManager);
    }

    public List<String> listSerialPorts() {
        SerialPortDiscoverer serialPortDiscoverer = new SerialPortDiscovererImpl();
        List<CommPortIdentifier> portIdentifiers = serialPortDiscoverer.listPorts();
        List<String> portNames = new ArrayList<String>(portIdentifiers.size());
        for (CommPortIdentifier portIdentifier : portIdentifiers) {
            portNames.add(portIdentifier.getName());
        }
        return portNames;
    }

    public void addLogger(EcuParameter ecuParam, LoggerCallback callback) {
        checkNotNull(ecuParam, callback);
        System.out.println("Adding logger:   " + ecuParam.getName());
        queryManager.addQuery(ecuParam, callback);
    }

    public void removeLogger(EcuParameter ecuParam) {
        checkNotNull(ecuParam, "ecuParam");
        System.out.println("Removing logger: " + ecuParam.getName());
        queryManager.removeQuery(ecuParam);
    }

    public void start() {
        new Thread(queryManager).start();
    }

    public void stop() {
        queryManager.stop();
    }
}
