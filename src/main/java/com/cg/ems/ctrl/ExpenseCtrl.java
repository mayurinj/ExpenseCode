/* 								REST CONTROLLER FOR EXPENSE CODE MODULE 
 	
 Expense Code Module has the following available functionalities 
 
 * Add Expense Details
 * Delete an Expense Detail using Expense Code
 * Search an Expense Detail using Expense Code
 * Update an Expense Detail 
 * Display All Expenses Details
 * @author Mayuri Joshi
 * @author Shivansh Gupta
 * @author Vikhyat Bhatnagar
 * @author Akshaya Saminathan
 * @author Parth Dubey
 * @author Naguleshan Sundaresan
 * @author Sai Sailesh
 * @author Rishabh Varma
*/

//

package com.cg.ems.ctrl;

//Required packages imported
import java.util.ArrayList;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ems.Exception.EMSNotFoundException;
import com.cg.ems.dao.Repo;
import com.cg.ems.dto.Expense;

@RestController
@RequestMapping(value = "/ExpenseCode")
@CrossOrigin(origins="http://localhost:4200")		//Angular URL with port number
public class ExpenseCtrl {

	//Logger object used for logging in the entire application 
	private static Logger log = LogManager.getLogger(ExpenseCtrl.class);
	
	//Injecting dependencies 
	@Autowired
	Repo repo;

/*____________________________________________ADDING EXPENSE DETAIL_____________________________________________
 * This function adds the expense code, expense type and expense description into the expensedetails table 
 * @param: A variable proj of user defined type Expense
 * @return: A variable proj of user defined type Expense
 */

	@PostMapping("/add/")
	public Expense add(@RequestBody Expense proj) {
		System.out.println(proj);
		repo.save(proj);
		log.info("Added Expense Code Successfully");
		return proj;

	}


	
/*____________________________________________UPDATE EXPENSE DETAIL_____________________________________________
 * This function updates the expense type and expense description for a particular expense detail in the expensedetails table 
 * @param: A variable id of user defined type integer
 * @return: void
 * */

	@PutMapping("/update/{id}")
	public void update(@PathVariable("id") int code, @RequestBody Expense proj) throws EMSNotFoundException {
		Optional<Expense> exp = repo.findById(code);
		if (exp.isPresent()==false)
		{
			log.error("Expense Details Not Available. Code does not Exist.");
			throw new EMSNotFoundException("Code does not exist");
		}
		else {
			proj.setExpenseCode(code);
			repo.save(proj);
			log.info("Updated Expense Details Successfully");
		}

	}
	
	

/*_______________________________________DISPLAY EXPENSE DETAIL_________________________________________________
 * This function displays the expense code, expense type and expense description from the expense details table based on expense code 
 * @param: A variable code of type integer
 * @return: A variable exp of type Expense
 */

	@GetMapping("/displayExpDet/{code}")
	public Optional<Expense> displayById(@PathVariable("code") int code){
		Optional<Expense>  exp = repo.findById(code);
		if (exp.isPresent()==false)
		{
			exp=null;
			log.error("No Expense Details available . Wrong Expense Code");
		}
		log.info("Displyed Expense Details by expense Code");
		return exp;
	}
	
	
	
/*____________________________________DISPLAY ALL EXPENSE DETAILS______________________________________________
 * This function displays the expense code, expense type and expense description of all the expenses from the expense details table 
 * @return: An Array List of type Expense
 */

	@GetMapping("/displayall/")
	public ArrayList<Expense> displayall(){
		ArrayList<Expense> expl=(ArrayList<Expense>) repo.findAll();
		if(expl.size()==0)
		{
				expl = null;
				log.error("No Expense details Available");
		}
		log.info("Displayed All Expense Details");
		return expl;
	}
	
	
	
/*_______________________________________DELETE EXPENSE DETAIL__________________________________________________
 * This function deletes the expense code, expense type and expense description of a particular expense detail based on expense code
 * @param: A variable id of user defined type Expense
 * @return: A variable proj of user defined type Expense
 */

	@DeleteMapping("/delete/{id}")
	public Optional<Expense> delete(@PathVariable("id") int id){
		Optional<Expense> exp = repo.findById(id);
		if (exp.isPresent()==false)
		{
			log.error("No Expense Details available . Wrong Expense Code");
				exp=null;
		}
		else {
			
		repo.deleteById(id);
		}
		log.info("Expense Details Deleted Successfully");
		return exp;

	}

}
