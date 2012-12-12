package edu.jbehave.example.scenarios;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.jbehave.Ensure.ensureThat;

import org.jbehave.scenario.annotations.Given;
import org.jbehave.scenario.annotations.Then;
import org.jbehave.scenario.annotations.When;

import edu.jbehave.example.FullStackException;
import edu.jbehave.example.MyStack;

public class StackSteps {
	
	private MyStack<String> stack;
	private Throwable exception;
	
	@Given("an empty stack with capacity $capacity")
	public void givenAnEmptyStackWithCapacity(int capacity) {
		stack = new MyStack<String>(capacity);
	}
	
	@When("I push \"$item\" to a stack")
	public void whenIPushItemToAStack(String item) {
		try {
			stack.push(item);
			exception = null;
		} catch (Exception e) {
			exception = e;
		}
	}
	
	@Then("\"$item\" should be on top of the stack")
	public void thenItemShouldBeOnTopOfTheStack(String item) {
		ensureThat(stack.peek(), is(equalTo(item)));
	}
	
	@Given("\"$item\" pushed on the stack")
	public void givenItemPushedOnTheStack(String item) {
		try {
			stack.push(item);
			exception = null;
		} catch (Exception e) {
			exception = e;
		}
	}
	
	@Then("FullStackException should be thrown")
	public void thenFullStackExceptionShouldBeThrown() {
		ensureThat(exception, is(FullStackException.class));
	}
	
	@Then("the stack should be full")
	public void thenTheStackShouldBeFull() {
		ensureThat(stack.isFull(), is(true));
	}
}
