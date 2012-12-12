package edu.jbehave.example.spec;

import static org.jbehave.Ensure.ensureThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.Test;

import edu.jbehave.example.MyStack;

public class EmptyStackBehaviour {
	private MyStack<String> stack;
	
	@Before
	public void setupStack() {
		stack = new MyStack<String>(10);
	}
	@Test
	public void shouldBeEmpty() {
		ensureThat(stack.isEmpty(), is(true));
	}
	
	@Test
	public void shouldNotBeFull() {
		ensureThat(stack.isFull(), is(false));
	}
	
	@Test
	public void shouldAddPushedItemToTheTopOfTheStack() throws Exception {
		stack.push("item");
		ensureThat(stack.peek(), is(equalTo("item")));
	}
	
	@Test(expected=EmptyStackException.class)
	public void shouldComplainOnPeek() { 
		stack.peek();
	}
	
	@Test(expected=EmptyStackException.class)
	public void shouldComplainOnPop() {
		stack.pop();
	}
}
