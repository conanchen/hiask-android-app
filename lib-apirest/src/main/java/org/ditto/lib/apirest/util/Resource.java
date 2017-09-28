package org.ditto.lib.apirest.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;



/**
 * A generic class that holds a value with its loading loadingStatus.
 * @param <T>
 */
public class Resource<T> {

    @NonNull
    public final LoadingStatus loadingStatus;

    @Nullable
    public final String message;

    @Nullable
    public final T data;

    public Resource(@NonNull LoadingStatus loadingStatus, @Nullable T data, @Nullable String message) {
        this.loadingStatus = loadingStatus;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(LoadingStatus.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(LoadingStatus.ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LoadingStatus.LOADING, data, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Resource<?> resource = (Resource<?>) o;

        if (loadingStatus != resource.loadingStatus) {
            return false;
        }
        if (message != null ? !message.equals(resource.message) : resource.message != null) {
            return false;
        }
        return data != null ? data.equals(resource.data) : resource.data == null;
    }

    @Override
    public int hashCode() {
        int result = loadingStatus.hashCode();
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "loadingStatus=" + loadingStatus +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}