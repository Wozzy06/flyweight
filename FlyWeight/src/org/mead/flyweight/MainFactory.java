package org.mead.flyweight;

import java.util.EnumMap;
import java.util.Map;

public final class MainFactory<E extends Enum<E>> implements IMainFactory<E> {

	private final Map<E, ICustomFactory> customFactories;
	private final Map<E, Object> store;

	public MainFactory(final Class<E> clazz) {
		customFactories = new EnumMap<>(clazz);
		store = new EnumMap<>(clazz);
	}

	@Override
	public final Object create(final E type) {
		Object object = store.get(type);
		if (null != object) return object;
		object = customFactories.get(type).create();
		store.put(type, object);
		return object;
	}

	public final Map<E, ICustomFactory> getCustomFactories() {
		return customFactories;
	}

}
