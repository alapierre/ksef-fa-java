package io.alapierre.ksef.fa.model.gobl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.alapierre.ksef.fa.model.gobl.exceptions.JsonParserException;
import lombok.NonNull;
import org.gobl.model.Invoice;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2024.01.16
 */
public abstract class GoblSerializer<T> {

    private final Class<T> modelClass;
    private final ObjectMapper mapper = new ObjectMapper();

    public GoblSerializer(@NonNull Class<T> modelClass) {

        this.modelClass = modelClass;

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
    }

    public T fromFile(@NonNull File file) {
        try {
            return mapper.readValue(file, modelClass);
        } catch (IOException e) {
            throw new JsonParserException(e);
        }
    }

    public T fromString(@NonNull String jsonString) {
        try {
            return mapper.readValue(jsonString, modelClass);
        } catch (IOException e) {
            throw new JsonParserException(e);
        }
    }

    public T fromStream(@NonNull InputStream inputStream) {
        try {
            return mapper.readValue(inputStream, modelClass);
        } catch (IOException e) {
            throw new JsonParserException(e);
        }
    }

    public void toFile(@NonNull File file, @NonNull T value) throws IOException {
        try {
            mapper.writeValue(file, value);
        } catch (StreamWriteException | DatabindException e) {
            throw new JsonParserException(e);
        }
    }

    public void toStream(@NonNull OutputStream outputStream, @NonNull T value) throws IOException {
        try {
            mapper.writeValue(outputStream, value);
        } catch (StreamWriteException | DatabindException e) {
            throw new JsonParserException(e);
        }
    }

    public String toString(@NonNull T value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new JsonParserException(e);
        }
    }
}
