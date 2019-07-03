package com.vinzor.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.vinzor.entity.Dog;
/**
 * 
 * @author baoshan
 *	JpaRepository类有简单的默认方法 sava() findAll()等具体看代码
 */
public interface DogReposity extends JpaRepository<Dog, Long>{
	
	Dog findByDogId(long dogId);
	//and or 有错误
	//Dog finByDogIdOrDogAge(long dogId,int dogAge);
	//通过Id删除
	Long deleteByDogId(long dogId);
	//通过年龄统计狗条数
	Long countByDogAge(int dogAge);
	//like操作
	List<Dog> findByDogNameLike(String dogName);
	//Is not 有名字的狗和无名字的狗
	List<Dog> findByDogNameIsNull();
	
	//分页查询多个参数建议Pageable放在最后一个 Service中有具体写法
	Page<Dog> findAll(Pageable pageable);

	//限制查询 未写实现
	Dog findFirstByOrderByDogNameAsc();
	Dog findTopByOrderByDogAgeDesc();
	Page<Dog> queryFirst10ByDogName(String dogName, Pageable pageable);
	List<Dog> findFirst10ByDogName(String dogName, Sort sort);
	List<Dog> findTop10ByDogName(String dogName, Pageable pageable);
	
	//使用@Query注解涉及到删除和修改在需要加上@Modifying.也可以根据需要添加 @Transactional对事物的支持，查询超时的设置等
	@Modifying
	@Query("update Dog d set d.dogName = ?1 where d.dogId = ?2")
	int modifyDogNameByDogId(String  dogName, Long dogId);
	    
	@Transactional
	@Modifying
	@Query("delete from Dog where id = ?1")
	void deleteByDogId(Long id);
	  
	@Transactional(timeout = 10)
	@Query("select d from Dog d where d.dogName = ?1")
	Dog findByDogName(String dogName);

}
