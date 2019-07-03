package com.vinzor.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author baoshandaye
 * 	一种是在只使用@OneToMany来标识，这种方式是通过一张第三方表来保存关系。还有一种是使用@OneToMany和@JoinColumn来标注，这种方式是在多的一方(Employee)的表中增加一个外键列来保存关系。
 *
 */
//小孩 和狗玩具 是 1对多
@Entity
@Table(name="child")
public class Child {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private long childId;
	
	private String childName;
	
	private int childAge;
	
	/**
	 *  CascadeType.REMOVE
	 *	Cascade remove operation，级联删除操作。
	 *	删除当前实体时，与它有映射关系的实体也会跟着被删除。
	 *	CascadeType.MERGE
	 *	Cascade merge operation，级联更新（合并）操作。
	 *	当Student中的数据改变，会相应地更新Course中的数据。
	 *	CascadeType.DETACH
	 *  Cascade detach operation，级联脱管/游离操作。
	 *	如果你要删除一个实体，但是它有外键无法删除，你就需要这个级联权限了。它会撤销所有相关的外键关联。
	 *	CascadeType.REFRESH
	 *	Cascade refresh operation，级联刷新操作。
	 *  假设场景 有一个订单,订单里面关联了许多商品,这个订单可以被很多人操作,那么这个时候A对此订单和关联的商品进行了修改,与此同时,B也进行了相同的操作,但是B先一步比A保存了数据,那么当A保存数据的时候,就需要先刷新订单信息及关联的商品信息后,再将订单及商品保存。(来自良心会痛的评论)
	 *	CascadeType.ALL
	 *	Cascade all operations，清晰明确，拥有以上所有级联操作权限。
	 */
	@OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(
            name = "child_id",
            foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT) //这行配置为了让其不产生外键
    )
	Set<DogToy> dogToys;
	public long getChildId() {
		return childId;
	}
	public void setChildId(long childId) {
		this.childId = childId;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public int getChildAge() {
		return childAge;
	}
	public void setChildAge(int childAge) {
		this.childAge = childAge;
	}
	public Set<DogToy> getDogToys() {
		return dogToys;
	}
	public void setDogToys(Set<DogToy> dogToys) {
		this.dogToys = dogToys;
	}
	
	
}
