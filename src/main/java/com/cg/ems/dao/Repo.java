/* 										  DAO FOR EXPENSE CODE MODULE                                            */

package com.cg.ems.dao;

//Required packages and classes imported
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cg.ems.dto.Expense;


@Repository
@Transactional
public interface Repo extends CrudRepository<Expense, Integer>{


}
