package cn.quickly.project.utility.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public abstract class DelegateSet<E> implements Set<E> {

	protected abstract Set<E> getDelegate();

	@Override
	public int size() {
		return getDelegate().size();
	}

	@Override
	public boolean isEmpty() {
		return getDelegate().isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return getDelegate().contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		return getDelegate().iterator();
	}

	@Override
	public Object[] toArray() {
		return getDelegate().toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return getDelegate().toArray(a);
	}

	@Override
	public boolean add(E e) {
		return getDelegate().add(e);
	}

	@Override
	public boolean remove(Object o) {
		return getDelegate().remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return getDelegate().containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return getDelegate().addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return getDelegate().retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return getDelegate().removeAll(c);
	}

	@Override
	public void clear() {
		getDelegate().clear();
	}

}
