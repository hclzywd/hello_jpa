package ywd.std.spring.jpa.hellojpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import ywd.std.spring.jpa.hellojpa.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
	List<User> findByLastname(String lastName) ; 
	List<User> findByFirstname(String firstName) ;
	User findById(Integer id) ;
	//User findByFirstnameAndLastname(String firstName ,String lastname) ;
	@Query(value = "select * from user where firstname = :firstname" ,nativeQuery = true) 
	List<User> findUserListBySql(@Param("firstname") String firstName) ;
}
