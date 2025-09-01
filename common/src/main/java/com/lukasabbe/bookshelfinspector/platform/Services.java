package com.lukasabbe.bookshelfinspector.platform;

import com.lukasabbe.bookshelfinspector.Constants;
import com.lukasabbe.bookshelfinspector.platform.services.IEventHelper;
import com.lukasabbe.bookshelfinspector.platform.services.INetworkHelper;
import com.lukasabbe.bookshelfinspector.platform.services.IPlatformHelper;

import java.util.ServiceLoader;

public class Services {

    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);
    public static final INetworkHelper NETWORK_HELPER = load(INetworkHelper.class);
    public static final IEventHelper EVENTS_HELPER = load(IEventHelper.class);

    public static <T> T load(Class<T> clazz) {

        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        Constants.LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}