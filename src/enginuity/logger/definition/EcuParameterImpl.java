package enginuity.logger.definition;

import static enginuity.util.ParamChecker.checkNotNull;
import static enginuity.util.ParamChecker.checkNotNullOrEmpty;

public final class EcuParameterImpl implements EcuParameter {
    private String name;
    private String description;
    private String[] addresses;
    private EcuParameterConvertor convertor;

    public EcuParameterImpl(String name, String description, String[] address, EcuParameterConvertor convertor) {
        checkNotNullOrEmpty(name, "name");
        checkNotNullOrEmpty(description, "description");
        checkNotNullOrEmpty(address, "addresses");
        checkNotNull(convertor, "convertor");
        this.name = name;
        this.description = description;
        this.addresses = address;
        this.convertor = convertor;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getAddresses() {
        return addresses;
    }

    public EcuParameterConvertor getConvertor() {
        return convertor;
    }
}
