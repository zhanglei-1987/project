package cn.quickly.project.utility.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public abstract class DelegateList<E> implements List<E> {

	protected abstract List<E> getDelegate();

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
		return getDelegate().contains(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return getDelegate().addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		return getDelegate().addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return getDelegate().removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return getDelegate().retainAll(c);
	}

	@Override
	public void clear() {
		getDelegate().clear();
	}

	@Override
	public E get(int index) {
		return getDelegate().get(index);
	}

	@Override
	public E set(int index, E element) {
		return getDelegate().set(index, element);
	}

	@Override
	public void add(int index, E element) {
		getDelegate().add(index, element);
	}

	@Override
	public E remove(int index) {
		return getDelegate().remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return getDelegate().indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return getDelegate().lastIndexOf(o);
	}

	@Override
	public ListIterator<E> listIterator() {
		return getDelegate().listIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return getDelegate().listIterator(index);
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return getDelegate().subList(fromIndex, toIndex);
	}

}
