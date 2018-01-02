package org.mead.flyweight;

interface IMainFactory<E extends Enum<E>> {
	Object create(E type);
}
