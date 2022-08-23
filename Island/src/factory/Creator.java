package factory;

import com.fasterxml.jackson.databind.json.JsonMapper;
import population.EntityInterface;
import java.io.File;
import java.io.IOException;

public abstract class Creator {

    public abstract EntityInterface createEntity() throws IOException;
}
