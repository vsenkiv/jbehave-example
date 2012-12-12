package edu.jbehave.example;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class MyStack<T> {
	
	private ArrayList<T> list;
	private int capacity;
	
	public MyStack(int capacity) {
		this.capacity = capacity;
		this.list = new ArrayList<T>();
	}

	public Boolean isEmpty() {
		return list.isEmpty();
	}

	public Boolean isFull() {
		return list.size() == capacity;
	}

	public void push(T item) throws FullStackException {
		if (isFull()) {
			throw new FullStackException();
		} else {
			list.add(item);			
		}
	}

	public T peek() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			return list.get(list.size() - 1);
		}
	}

	public T pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			return list.remove(list.size() - 1);
		}
	}
}
