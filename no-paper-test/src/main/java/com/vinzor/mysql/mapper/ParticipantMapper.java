package com.vinzor.mysql.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.vinzor.mysql.entity.Participant;

public interface ParticipantMapper {

	@Select("SELECT id,name,sex,position,phone_number FROM participant")
	@Results({
		 @Result(property="id",column="id"),
		 @Result(property="name",column="name"),
		 @Result(property="sex",column="sex"),
		 @Result(property="position",column="position"),
		 @Result(property="phoneNumber",column="phone_number")
	})
	List<Participant> findAllParticipant();

	@Select("SELECT id,name,sex,position,phone_number FROM participant WHERE id=#{id}")
	@Results({
		 @Result(property="id",column="id"),
		 @Result(property="name",column="name"),
		 @Result(property="sex",column="sex"),
		 @Result(property="position",column="position"),
		 @Result(property="phoneNumber",column="phone_number")
	})
	Participant findParticipantById(int id);
	
	@Delete("DELETE FROM participant WHERE id=#{id}")
	int deleteParticipantById(int id);
	
	@Insert("INSERT INTO participant (name,sex,position,phone_number)VALUES(#{name},#{sex},#{position},#{phoneNumber})")
	int addParticipant(String name,int sex,String position,String phoneNumber);

	@Update("UPDATE participant SET name=#{name},sex=#{sex},position=#{position},phone_number=#{phoneNumber} WHERE id=#{id}")
	int updateParticipant(String name,int sex,String position,String phoneNumber,int id);

}
