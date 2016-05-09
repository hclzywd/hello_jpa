package ywd.std.spring.jpa.hellojpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import ywd.std.spring.jpa.hellojpa.cfg.AppConfig;
import ywd.std.spring.jpa.hellojpa.entity.User;
import ywd.std.spring.jpa.hellojpa.repositories.UserRepository;

@Component
@ComponentScan
public class HelloSpringJap {

	@Autowired
	UserRepository repository;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		HelloSpringJap hello = ctx.getBean(HelloSpringJap.class) ;
		//hello.sampleTestCase(); 
		//hello.pageQuery();
		hello.commonQuery();
	}

	public void sampleTestCase() {
		User dave = new User("Dave", "Matthews");
		dave = repository.save(dave);
		System.out.println("----------------  save ok..");
		System.out.println(dave.getStr());

		User carter = new User("Carter", "Beauford");
		carter = repository.save(carter);
		System.out.println("----------------  save ok..");
		System.out.println(carter.getStr());
		
		for(int i = 0;i<100;i++){
			User t = new User("BatchAdded", "No."+i);
			carter = repository.save(t);
		}

		List<User> result = repository.findByLastname("Matthews");
		if (result == null || result.isEmpty()) {
			System.out.println("----------------- find not correct ..");
		} else {
			System.out.println("----------------- find ok..");
			for (User user : result) {
				System.out.println(user.getStr());
			}
		}
	}
	
	public void pageQuery() {
		Page<User> users = repository.findAll(new PageRequest(0,20)) ;
		List<User> userList = users.getContent() ;
		System.out.println("======================================>> user page query..");
		for (User user : userList) {
			System.out.println(user.getStr());
		}
	}
	
	public void commonQuery() {
		List<User> users = repository.findByFirstname("Carter") ;
		System.out.println("++++++++++++++++++++++++++++++++++++++  first name query result : ");
		for (User user : users) {
			System.out.println(user.getStr());
		}
		User user = repository.findById(88) ;
		System.out.println("=====================================  find by id :");
		System.out.println(user.getStr());
//		user = repository.findByFirstnameAndLastname("Dave", "Matthews") ;
//		System.out.println("=====================================  find by firstname and lastname :");
//		System.out.println(user.getStr());
	}

}
