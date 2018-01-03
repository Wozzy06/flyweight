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
	public final Object create(final E key, final Object... args) {
		Object object = getObject(key);
		if (null != object) return object;
		object = createObject(key, args);
		setObjectToStore(key, object);
		return object;
	}

	public final Object createObject(final E key, final Object... args) {
		return getCustomFactory(key).create(args);
	}

	public final Map<E, ICustomFactory> getCustomFactories() {
		return customFactories;
	}

	public final ICustomFactory getCustomFactory(final E key) {
		return customFactories.get(key);
	}

	public final Object getObject(final E key) {
		return store.get(key);
	}

	public final void removeObjectFromStore(final E key) {
		store.remove(key);
	}

	public final ICustomFactory setCustomFactory(final E key, final ICustomFactory customFactory) {
		return customFactories.put(key, customFactory);
	}

	public final void setObjectToStore(final E key, final Object object) {
		store.put(key, object);
	}

}
