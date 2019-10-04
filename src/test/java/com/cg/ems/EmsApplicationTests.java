package com.cg.ems;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.cg.ems.Exception.EMSNotFoundException;
import com.cg.ems.ctrl.ExpenseCtrl;
import com.cg.ems.dao.Repo;
import com.cg.ems.dto.Expense;



@RunWith(SpringRunner.class)
@SpringBootTest
public class EmsApplicationTests {
	@Test
	public void contextLoads() {

	}
	@Mock
	Repo repo;
	
	@InjectMocks
	ExpenseCtrl expense;
	
	@Spy
	List<Expense> list = new ArrayList<Expense>();

	@Captor
	ArgumentCaptor<Expense> captor;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		list.add(new Expense(1, "Travel", "Imagica"));
		list.add(new Expense(2, "Travel", "Lonavala"));

	}
	@Test
	public void addExpense() {

		// create mock
		Expense exp = new Expense(1, "Travel", "Imagica");
		// define return value for method save()
		when(repo.save(exp)).thenReturn(exp);
		assertEquals(repo.save(exp), exp);
	}
	@Test
	public void updateExpense() throws EMSNotFoundException {

		// create mock
		Expense exp = new Expense(1, "Travel", "Imagica");
		// define return value for method save()
		when(repo.findById(any(Integer.class))).thenReturn(java.util.Optional.of(exp));
		Assert.assertEquals(1, expense.displayById(1).get().getExpenseCode());
		when(repo.save(any(Expense.class))).thenReturn(exp);
		exp.setExpenseDescription("Pune");
		expense.update(1, exp);
		verify(repo, times(1)).save(captor.capture());
		Expense exp1 = expense.displayById(102).get();
		assertEquals(1, exp1.getExpenseCode());
		assertEquals("Travel", exp1.getExpenseType());
		assertEquals("Pune", exp1.getExpenseDescription());
		assertEquals(captor.getValue().getExpenseCode(), 1);
		Assert.assertEquals(2, list.size());
		verify(list, times(2)).add(any(Expense.class));
	}



	@Test
	public void searchExpense() throws EMSNotFoundException {
		
		Expense exp = new Expense(1, "Travel", "Imagica");
		when(repo.findById(any(Integer.class))).thenReturn(java.util.Optional.of(exp));
		Assert.assertEquals(1, expense.displayById(1).get().getExpenseCode());
		Assert.assertEquals(2, list.size());
		verify(list, times(2)).add(any(Expense.class));
	}

	@Test
	public void viewEmployees() throws EMSNotFoundException
	{
		when(repo.findAll()).thenReturn(list);
		ArrayList<Expense> iter = expense.displayall();
		List<Expense> list2 = new ArrayList<Expense>();
		iter.forEach(list2::add);
		Assert.assertEquals(2, list2.size());
		Assert.assertEquals(2, list.size());
		Assert.assertNotNull(iter);
	}

	@Test
	public void deleteEmployee() throws EMSNotFoundException
	{
		Expense exp = new Expense(1, "Travel", "Imagica");
		// define return value for method save()
		when(repo.findById(any(Integer.class))).thenReturn(java.util.Optional.of(exp));
		Assert.assertEquals(1, expense.displayById(1).get().getExpenseCode());
		doNothing().when(repo).deleteById(any(Integer.class));
		Assert.assertEquals(1, expense.delete(1).get().getExpenseCode());

 }



}

